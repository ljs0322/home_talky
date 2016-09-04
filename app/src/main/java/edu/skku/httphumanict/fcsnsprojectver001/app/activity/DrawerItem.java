package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

/**
 * Created by say on 2016-09-04.
 */
public class DrawerItem {

    String ItemName;
    int imgResID;

    public DrawerItem(String itemName) {
        super();
        ItemName = itemName;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

}

