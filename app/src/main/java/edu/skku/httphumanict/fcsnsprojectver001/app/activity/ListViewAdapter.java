package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.skku.httphumanict.fcsnsprojectver001.R;
import edu.skku.httphumanict.fcsnsprojectver001.app.FCSNSAppManager;

/**
 * Created by say on 2016-08-11.
 */
public class ListViewAdapter extends BaseAdapter {

    //final static FCSNSRoomActivity mainActivity = new FCSNSRoomActivity();

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
        }

        final ImageView iconImageView = (ImageView) convertView.findViewById(R.id.chat_imamge1);
        final TextView msgTextView = (TextView) convertView.findViewById(R.id.chat_text1);
        final ImageView iconImageView2 = (ImageView) convertView.findViewById(R.id.chat_imamge2);
        final TextView msgTextView2 = (TextView) convertView.findViewById(R.id.chat_text2);
        final ListViewItem listViewItem = listViewItemList.get(position);

        iconImageView.setImageDrawable(listViewItem.getIcon());
        iconImageView2.setImageDrawable(listViewItem.getIcon());
        msgTextView.setText(listViewItem.getTitle());
        msgTextView2.setText(listViewItem.getTitle());

        msgTextView.setBackgroundResource(R.drawable.l_talk);
        msgTextView2.setBackgroundResource(R.drawable.r_talk);

        LinearLayout chatMessageContainer = (LinearLayout) convertView.findViewById(R.id.list_layout1);
        LinearLayout chatMessageContainer2 = (LinearLayout) convertView.findViewById(R.id.list_layout2);
        if(listViewItem.isMyMsg() == 1){
            // 내 메세지인 경우
            chatMessageContainer.setVisibility(View.VISIBLE);
            chatMessageContainer2.setVisibility(View.INVISIBLE);
        } else if(listViewItem.isMyMsg() == 2) {
            // 상대방 메세지인 경우
            chatMessageContainer.setVisibility(View.INVISIBLE);
            chatMessageContainer2.setVisibility(View.VISIBLE);
        }else if(listViewItem.isMyMsg() == 3){
            msgTextView2.setBackgroundResource(R.drawable.notice); // 공지
            chatMessageContainer.setVisibility(View.INVISIBLE);
            chatMessageContainer2.setVisibility(View.VISIBLE);
        }
        else if(listViewItem.isMyMsg() == 4){
            msgTextView2.setBackgroundResource(R.drawable.n_talk); // 어두운 배경 공지
            chatMessageContainer.setVisibility(View.INVISIBLE);
            chatMessageContainer2.setVisibility(View.VISIBLE);
        }


        msgTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(msgTextView.getClass() == v.getClass()){
                        final FCSNSRoomActivity roomActivity = FCSNSAppManager.getInstance().getRoomActivity();

                        roomActivity.alert.setTitle("대화를 저장하시겠습니까?");
                        roomActivity.alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(roomActivity.getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                                roomActivity.dataList.add(new DrawerItem(msgTextView.getText().toString()));
                                roomActivity.drawerAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });

                        roomActivity.alert.setMessage(msgTextView.getText().toString());
                        roomActivity.alert.show();

                    }
                }

                return true;
            }
        });

        msgTextView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(msgTextView2.getClass() == v.getClass()){
                        final FCSNSRoomActivity roomActivity = FCSNSAppManager.getInstance().getRoomActivity();

                        roomActivity.alert.setTitle("아래 대화를 저장하시겠습니까?");
                        roomActivity.alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(roomActivity.getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                                roomActivity.dataList.add(new DrawerItem(msgTextView2.getText().toString()));
                                roomActivity.drawerAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });

                        roomActivity.alert.setMessage(msgTextView2.getText().toString());
                        roomActivity.alert.show();
                    }
                }
                return true;
            }
        });







        return convertView;
    }








    public void addItem(Drawable icon, String title, int _bIsMyMsg){
        ListViewItem item =new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setIsMyMsg(_bIsMyMsg);

        listViewItemList.add(item);
    }


}
