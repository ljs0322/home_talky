package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Dialog {
    String _id;
    String roomId;
    Date regDate;
    String content;
    String fromId;
    ArrayList<String> checkedIds;

    public Dialog(String _id, String roomId, Date regDate, String content, String fromId, ArrayList<String> checkedIds) {
        this._id = _id;
        this.roomId = roomId;
        this.regDate = regDate;
        this.content = content;
        this.fromId = fromId;
        this.checkedIds = checkedIds;
    }

    public Dialog() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Dialog{");
        sb.append("_id='").append(_id).append('\'');
        sb.append(", roomId='").append(roomId).append('\'');
        sb.append(", regDate=").append(regDate);
        sb.append(", content='").append(content).append('\'');
        sb.append(", fromId='").append(fromId).append('\'');
        sb.append(", checkedIds=").append(checkedIds);
        sb.append('}');
        return sb.toString();
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFromId() {
        return fromId;
    }
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    public ArrayList<String> getCheckedIds() {
        return checkedIds;
    }
    public void setCheckedIds(ArrayList<String> checkedIds) {
        this.checkedIds = checkedIds;
    }
}// end of class
