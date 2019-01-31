package com.acuratechglobal.bulkbilling.api.request;

public class SetPlanApiRequest {

   private Integer memberShipPlanID=0;
   private String userUniqueID= null;

    public Integer getMemberShipPlanID() {
        return memberShipPlanID;
    }

    public void setMemberShipPlanID(Integer memberShipPlanID) {
        this.memberShipPlanID = memberShipPlanID;
    }

    public String getUserUniqueID() {
        return userUniqueID;
    }

    public void setUserUniqueID(String userUniqueID) {
        this.userUniqueID = userUniqueID;
    }
}
