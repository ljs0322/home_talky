package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.skku.httphumanict.fcsnsprojectver001.R;

/**
 * Created by say on 2016-08-11.
 */
public class ListViewAdapter extends BaseAdapter {

    final static FCSNSRoomActivity mainActivity = new FCSNSRoomActivity();

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

        final int pos = position;
        int align = 0;
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
