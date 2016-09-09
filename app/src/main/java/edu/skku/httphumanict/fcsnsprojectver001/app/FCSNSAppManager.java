package edu.skku.httphumanict.fcsnsprojectver001.app;

import edu.skku.httphumanict.fcsnsprojectver001.app.activity.FCSNSRoomActivity;
import edu.skku.httphumanict.fcsnsprojectver001.app.activity.ListViewAdapter;

/**
 *
 * Created by ProLab on 2016-07-26.
 */
public class FCSNSAppManager {
    private static FCSNSAppManager instance = new FCSNSAppManager();
    private FCSNSAppManager(){
        // null
    }
    public static FCSNSAppManager getInstance(){
        return  instance;
    }

    private FCSNSRoomActivity roomAcitivity;
    private ListViewAdapter listViewAdapter;


    public void setRoomAcitivity(FCSNSRoomActivity _cRoomActivity){
        this.roomAcitivity = _cRoomActivity;
    }

    public FCSNSRoomActivity getRoomActivity(){
        return roomAcitivity;
    }

    public void setListViewAdapter(ListViewAdapter _listviewAdapter){
        this.listViewAdapter = _listviewAdapter;
    }

    public ListViewAdapter getListViewAdapter(){
        return listViewAdapter;
    }
}// end of class
