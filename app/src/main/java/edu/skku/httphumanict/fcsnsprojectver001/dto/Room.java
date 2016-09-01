package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Room {
    ArrayList<String> participations;
    Date regDate;
    ArrayList<Notice> notices;
    ArrayList<Dialog> dialogs;
    ArrayList<Dialog> savedDialogs;

    public Room(ArrayList<String> participations, Date regDate, ArrayList<Notice> notices, ArrayList<Dialog> dialogs, ArrayList<Dialog> savedDialogs) {
        this.participations = participations;
        this.regDate = regDate;
        this.notices = notices;
        this.dialogs = dialogs;
        this.savedDialogs = savedDialogs;
    }

    public Room() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Room{");
        sb.append("participations=").append(participations);
        sb.append(", regDate=").append(regDate);
        sb.append(", notices=").append(notices);
        sb.append(", dialogs=").append(dialogs);
        sb.append(", savedDialogs=").append(savedDialogs);
        sb.append('}');
        return sb.toString();
    }

    public ArrayList<String> getParticipations() {
        return participations;
    }
    public void setParticipations(ArrayList<String> participations) {
        this.participations = participations;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public ArrayList<Notice> getNotices() {
        return notices;
    }
    public void setNotices(ArrayList<Notice> notices) {
        this.notices = notices;
    }
    public ArrayList<Dialog> getDialogs() {
        return dialogs;
    }
    public void setDialogs(ArrayList<Dialog> dialogs) {
        this.dialogs = dialogs;
    }
    public ArrayList<Dialog> getSavedDialogs() {
        return savedDialogs;
    }
    public void setSavedDialogs(ArrayList<Dialog> savedDialogs) {
        this.savedDialogs = savedDialogs;
    }
}// end of class
