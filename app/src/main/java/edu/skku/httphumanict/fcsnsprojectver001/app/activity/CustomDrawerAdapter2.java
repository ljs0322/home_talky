package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.skku.httphumanict.fcsnsprojectver001.R;

/**
 * Created by say on 2016-09-09.
 */
public class CustomDrawerAdapter2 extends ArrayAdapter<noticeItem> {

    Context context2;
    List<noticeItem> drawerItemList2;
    int layoutResID2;

    public CustomDrawerAdapter2(Context context, int layoutResourceID, ArrayList<noticeItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context2 = context;
        this.drawerItemList2 = listItems;
        this.layoutResID2 = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context2).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID2, parent, false);
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName2);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        noticeItem dItem = (noticeItem) this.drawerItemList2.get(position);
        drawerHolder.ItemName.setText(dItem.getItemName());
        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
    }
}
