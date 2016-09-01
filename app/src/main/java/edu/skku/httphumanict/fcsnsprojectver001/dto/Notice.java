package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Notice {
    String _id;
    String content;
    Date startDate;
    Date endDate;

    String fromId;
    ArrayList<String> toIds;
    ArrayList<String> checkedId;

    public Notice(String _id, String content, Date startDate, Date endDate, String fromId, ArrayList<String> toIds, ArrayList<String> checkedId) {
        this._id = _id;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fromId = fromId;
        this.toIds = toIds;
        this.checkedId = checkedId;
    }

    public Notice() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Notice{");
        sb.append("_id='").append(_id).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", fromId='").append(fromId).append('\'');
        sb.append(", toIds=").append(toIds);
        sb.append(", checkedId=").append(checkedId);
        sb.append('}');
        return sb.toString();
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getFromId() {
        return fromId;
    }
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    public ArrayList<String> getToIds() {
        return toIds;
    }
    public void setToIds(ArrayList<String> toIds) {
        this.toIds = toIds;
    }
    public ArrayList<String> getCheckedId() {
        return checkedId;
    }
    public void setCheckedId(ArrayList<String> checkedId) {
        this.checkedId = checkedId;
    }
}// end of class
