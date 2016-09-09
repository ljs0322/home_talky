package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import edu.skku.httphumanict.fcsnsprojectver001.R;
import edu.skku.httphumanict.fcsnsprojectver001.app.FCSNSAppManager;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class FCSNSRoomActivity extends AppCompatActivity {

    public String data1,data2,data3,data4;
    public String am_weather = "날씨가 이상함";
    public String pm_weather = "날씨가 이상함";
    public static int chat_bubble = 1;
    public ListView lvNavList;
    public ListView notice_drawer;
    private RelativeLayout ReContainer;
    private DrawerLayout dlDrawer;
    public AlertDialog.Builder alert;
    public ArrayList<DrawerItem> dataList;
    public ArrayList<noticeItem> noticeList;
    public CustomDrawerAdapter drawerAdapter;
    public CustomDrawerAdapter2 drawerAdapter2;
    public int cnt = 0;
    public int bg_cnt = 0;
    public boolean bg_type = true;
    public ImageView view1;
    public ViewFlipper flipper;

    @Override
    public void onBackPressed() {
        if (dlDrawer.isDrawerOpen(lvNavList)) {
            dlDrawer.closeDrawer(lvNavList);
        }else if(dlDrawer.isDrawerOpen(notice_drawer)){
            dlDrawer.closeDrawer(notice_drawer);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        FCSNSAppManager.getInstance().setRoomAcitivity(this);

        final ListView listView;
        final ListViewAdapter adapter;

        final String state[] ={"운동중","식사중","이동중","회의중"};
        final String state1[] ={"진황이형","지연누나"};
        alert = new AlertDialog.Builder(FCSNSRoomActivity.this);

        adapter = new ListViewAdapter();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        final ImageView img = (ImageView)findViewById(R.id.imageView);
        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
        final Spinner emoticon_sp = (Spinner)findViewById(R.id.emoticon);

        ReContainer = (RelativeLayout)findViewById(R.id.activity_main_container);

        dlDrawer = (DrawerLayout)findViewById(R.id.activity_main_drawer);
        dataList = new ArrayList<DrawerItem>();
        drawerAdapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);
        lvNavList = (ListView)findViewById(R.id.lv_activity_main_nav_list);
        lvNavList.setAdapter(drawerAdapter);

        noticeList = new ArrayList<noticeItem>();
        drawerAdapter2 = new CustomDrawerAdapter2(this, R.layout.custom_drawer_item2, noticeList);
        notice_drawer = (ListView)findViewById(R.id.notice_listview);
        notice_drawer.setAdapter(drawerAdapter2);




        view1 = (ImageView)findViewById(R.id.animation_view);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,state);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(ad);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str_state = spin1.getSelectedItem().toString();
                if (str_state.equals("운동중")) {
                    BitmapDrawable img_state = (BitmapDrawable) getResources().getDrawable(R.drawable.status_dad_head_busy);
                    spin1.getResources().getDrawable(R.drawable.status_panel_busy);
                    img.setImageDrawable(img_state);
                } else if (str_state.equals("회의중")) {
                    BitmapDrawable img_state = (BitmapDrawable) getResources().getDrawable(R.drawable.status_dad_head_busy);
                    img.setImageDrawable(img_state);
                } else if (str_state.equals("이동중")) {
                    BitmapDrawable img_state = (BitmapDrawable) getResources().getDrawable(R.drawable.status_dad_head_busy);
                    img.setImageDrawable(img_state);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> emoticon = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,state1);
        emoticon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emoticon_sp.setAdapter(emoticon);

        emoticon_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str_state = emoticon_sp.getSelectedItem().toString();
                if (str_state.equals("진황이형")) {
                    adapter.add_emoticon(getResources().getDrawable(R.drawable.status_mom_head),getResources().getDrawable(R.drawable.jh_1),5);
                } else if (str_state.equals("지연누나")) {
                    adapter.add_emoticon(getResources().getDrawable(R.drawable.status_dad_head),getResources().getDrawable(R.drawable.jy_1),6);
                }
                str_state = "";

                adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText editText =(EditText)findViewById(R.id.editText);
                if(v.getId() == R.id.button){
                    if (editText.getText().length() != 0) {
                        bg_cnt = 0;
                        if(chat_bubble == 1){// 내가 전송시
                            adapter.addItem(getResources().getDrawable(R.drawable.status_mom_head), editText.getText().toString(), 1);
                            bg_type = true;
                            chat_bubble = 2;
                        }

                        else if (chat_bubble == 2){//상대방이 전송시
                            adapter.addItem(getResources().getDrawable(R.drawable.status_dad_head), editText.getText().toString(), 2);
                            bg_type = true;
                            chat_bubble = 3;
                        }
                        else if (chat_bubble == 3){//밝은 화면 공지
                            adapter.addItem(getResources().getDrawable(R.drawable.icon_512), editText.getText().toString(), 3);
                           // final ListViewAdapter listViewAdapter = FCSNSAppManager.getInstance().getListViewAdapter();

                            chat_bubble = 4;
                        }
                        else if (chat_bubble == 4){//어두운 화면 공지
                            adapter.addItem(getResources().getDrawable(R.drawable.icon_512), editText.getText().toString(), 4);
                            chat_bubble = 1;

                        }
                        adapter.notifyDataSetChanged();
                        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                        editText.setText("");
                    }
                }

            }
        });

        new ReceiveShortWeather().execute();

    }//end of oncreate

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg){
            updateThread();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        Thread bgCntThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        bg_cnt++;
                        if(bg_cnt == 10){
                            bg_type = false;
                            cnt = 0;
                        }
                        Thread.sleep(1000);
                    }catch (Throwable t){

                    }
                }
            }
        });
        bgCntThread.start();

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    }catch (Throwable t){

                    }
                }
            }
        });
        myThread.start();
    }

    private void updateThread() {

        int mod = cnt;
        cnt++;
        if(bg_type) {
            switch (mod) {
                case 0: view1.setBackgroundResource(R.drawable.p1);  break;
                case 1: view1.setBackgroundResource(R.drawable.p2);  break;
                case 2: view1.setBackgroundResource(R.drawable.p3);  break;
                case 3: view1.setBackgroundResource(R.drawable.p4);  break;
                case 4: view1.setBackgroundResource(R.drawable.p5);  break;
                case 5: view1.setBackgroundResource(R.drawable.p6);  break;
                case 6: view1.setBackgroundResource(R.drawable.p7);  break;
                case 7: view1.setBackgroundResource(R.drawable.p8);  break;
                case 8: view1.setBackgroundResource(R.drawable.p9);  break;
                case 9: view1.setBackgroundResource(R.drawable.p10);  cnt = 0;  break;
            }
        }else{
            switch (mod) {
                case 0: view1.setBackgroundResource(R.drawable.n_1);  break;
                case 1: view1.setBackgroundResource(R.drawable.n_2);  break;
                case 2: view1.setBackgroundResource(R.drawable.n_3);  break;
                case 3: view1.setBackgroundResource(R.drawable.n_4);  break;
                case 4: view1.setBackgroundResource(R.drawable.n_5);  cnt = 0;  break;
            }
        }


    }

//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
//            switch (position) {
//                case 0:
//                    ReContainer.setBackgroundColor(Color.parseColor("#A52A2A"));
//                    break;
//                case 1:
//                    ReContainer.setBackgroundColor(Color.parseColor("#5F9EA0"));
//                    break;
//                case 2:
//                    ReContainer.setBackgroundColor(Color.parseColor("#556B2F"));
//                    break;
//                case 3:
//                    ReContainer.setBackgroundColor(Color.parseColor("#FF8C00"));
//                    break;
//                case 4:
//                    ReContainer.setBackgroundColor(Color.parseColor("#DAA520"));
//                    break;
//            }
//
//            dlDrawer.closeDrawer(lvNavList);
//
//        }
//
//    }

    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {
            ArrayList<Weather> shortWeathers = new ArrayList<Weather>();
            protected Long doInBackground(URL... urls) {
                String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159068000";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = null;

                try {
                    response = client.newCall(request).execute();
                    parseXML(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Long result) {

                for (int i=0; i<8; i++){
                        if(shortWeathers.get(i).getHour().equals("9")){
                            data1 = shortWeathers.get(i).getTemp(); // 9시 온도
                            data2 = shortWeathers.get(i).getWfKor();// 9시 날씨
                        }else if(shortWeathers.get(i).getHour().equals("15")){
                            data3 = shortWeathers.get(i).getTemp(); // 15시 온도
                            data4 = shortWeathers.get(i).getWfKor();// 15시 날씨
                        }
                }
                am_weather = data2;
                Log.e("weather_data",am_weather);
                pm_weather = data4;
                Log.e("weather_data",pm_weather);
                invalidateOptionsMenu(); // onPrepareOptionMenu 불러오는 명령어
                Log.e("Data",data1);Log.e("Data",data2);Log.e("Data",data3);Log.e("Data",data4);

            }

            void parseXML(String xml) {
                try {
                    String tagName = "";
                    boolean onHour = false;
                    boolean onDay = false;
                    boolean onTem = false;
                    boolean onWfKor = false;
                    boolean onPop = false;
                    boolean onEnd = false;
                    boolean isItemTag1 = false;
                    int i = 0;

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();

                    parser.setInput(new StringReader(xml));

                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            tagName = parser.getName();
                            if (tagName.equals("data")) {
                                shortWeathers.add(new Weather());
                                onEnd = false;
                                isItemTag1 = true;
                            }
                        } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                            if (tagName.equals("hour") && !onHour) {
                                shortWeathers.get(i).setHour(parser.getText());
                                onHour = true;
                            }
                            if (tagName.equals("day") && !onDay) {
                                shortWeathers.get(i).setDay(parser.getText());
                                onDay = true;
                            }
                            if (tagName.equals("temp") && !onTem) {
                                shortWeathers.get(i).setTemp(parser.getText());
                                onTem = true;
                            }
                            if (tagName.equals("wfKor") && !onWfKor) {
                                shortWeathers.get(i).setWfKor(parser.getText());
                                onWfKor = true;
                            }
                            if (tagName.equals("pop") && !onPop) {
                                shortWeathers.get(i).setPop(parser.getText());
                                onPop = true;
                            }
                        } else if (eventType == XmlPullParser.END_TAG) {
                            if (tagName.equals("s06") && onEnd == false) {
                                i++;
                                onHour = false;
                                onDay = false;
                                onTem = false;
                                onWfKor = false;
                                onPop = false;
                                isItemTag1 = false;
                                onEnd = true;
                            }
                        }

                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 메뉴기능 추가 액티비티 호출
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(am_weather.equals("구름 많음") || am_weather.equals("구름 조금") ){
            menu.findItem(R.id.weather_icon1).setIcon(R.drawable.cloud);
            menu.findItem(R.id.am_weather).setTitle("오전\n"+data1);
        //Toast.makeText(getApplicationContext(),"날씨 흐림",Toast.LENGTH_SHORT).show();
    }
    else if(am_weather.equals("맑음")){
            menu.findItem(R.id.weather_icon1).setIcon(R.drawable.sunny);
            menu.findItem(R.id.am_weather).setTitle("오전\n"+data1);
        //Toast.makeText(getApplicationContext(),"날씨 맑음",Toast.LENGTH_SHORT).show();
    }
    else if(am_weather.equals("비")){
            menu.findItem(R.id.weather_icon1).setIcon(R.drawable.rainy);
            menu.findItem(R.id.am_weather).setTitle("오전\n"+data1);
        //Toast.makeText(getApplicationContext(),"비 개옴",Toast.LENGTH_SHORT).show();
    }
    else if(am_weather.equals("흐림")){
            menu.findItem(R.id.weather_icon1).setIcon(R.drawable.cloudy);
            menu.findItem(R.id.am_weather).setTitle("오전\n"+data1);
        //Toast.makeText(getApplicationContext(),"흐림",Toast.LENGTH_SHORT).show();
    }
        if(pm_weather.equals("구름 많음") || pm_weather.equals("구름 조금")){
            menu.findItem(R.id.weather_icon2).setIcon(R.drawable.cloud);
            menu.findItem(R.id.pm_weather).setTitle("오후\n"+data3);
            //Toast.makeText(getApplicationContext(),"날씨 흐림",Toast.LENGTH_SHORT).show();
        }
        else if(pm_weather.equals("맑음")){
            menu.findItem(R.id.weather_icon2).setIcon(R.drawable.sunny);
            menu.findItem(R.id.pm_weather).setTitle("오후\n"+data3);
            //Toast.makeText(getApplicationContext(),"날씨 맑음",Toast.LENGTH_SHORT).show();
        }
        else if(pm_weather.equals("비")){
            menu.findItem(R.id.weather_icon2).setIcon(R.drawable.rainy);
            menu.findItem(R.id.pm_weather).setTitle("오후\n"+data3);
           // Toast.makeText(getApplicationContext(),"비 개옴",Toast.LENGTH_SHORT).show();
        }
        else if(pm_weather.equals("흐림")){
            menu.findItem(R.id.weather_icon2).setIcon(R.drawable.cloudy);
            menu.findItem(R.id.pm_weather).setTitle("오후\n"+data3);
            //Toast.makeText(getApplicationContext(),"흐림",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {///메뉴선택시 기능 동작
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.Hello_world: // "저장" == item.getTitle()
                Toast.makeText(getApplicationContext(),"추후 개발",Toast.LENGTH_SHORT).show();
                dlDrawer.openDrawer(notice_drawer);
                return true;

            case R.id.file:
                Toast.makeText(getApplicationContext(),"저장",Toast.LENGTH_SHORT).show();
                dlDrawer.openDrawer(lvNavList);
                return true;
        }

        return true;
    }
}// end of class
