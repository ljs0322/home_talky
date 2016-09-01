package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Relation {
    String _id;
    ArrayList<FamilyShip> familyShips;

    class FamilyShip {
        String userId;
        String userPhone;
        Date regDate;

        public FamilyShip(String userId, String userPhone, Date regDate) {
            this.userId = userId;
            this.userPhone = userPhone;
            this.regDate = regDate;
        }

        public FamilyShip() {
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("FamilyShip{");
            sb.append("userId='").append(userId).append('\'');
            sb.append(", userPhone='").append(userPhone).append('\'');
            sb.append(", regDate=").append(regDate);
            sb.append('}');
            return sb.toString();
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public Date getRegDate() {
            return regDate;
        }

        public void setRegDate(Date regDate) {
            this.regDate = regDate;
        }
    }
}// end of class
