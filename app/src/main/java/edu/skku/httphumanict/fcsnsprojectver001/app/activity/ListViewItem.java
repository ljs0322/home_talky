package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.graphics.drawable.Drawable;

/**
 *
 * Created by say on 2016-08-11.
 */
public class ListViewItem {

    private Drawable iconDrawable;
    private String titleStr;
    private int isMyMsg;


    public void setIcon(Drawable icon){
        iconDrawable =icon;
    }

    public void setTitle(String title){
        titleStr =title;
    }

    public Drawable getIcon(){
        return  this.iconDrawable;
    }
    public String getTitle(){
        return this.titleStr;
    }

    public void setIsMyMsg(int isMyMsg) {
        this.isMyMsg = isMyMsg;
    }

    public int isMyMsg() {
        return isMyMsg;
    }
}
