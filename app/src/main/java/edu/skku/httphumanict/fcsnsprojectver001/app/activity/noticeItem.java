package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

/**
 * Created by say on 2016-09-09.
 */
public class noticeItem {

    String ItemName;
    int imgResID;

    public noticeItem(String itemName) {
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
