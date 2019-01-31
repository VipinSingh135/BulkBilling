package com.acuratechglobal.bulkbilling.models;

public class SideMenuListModel {

    private String title="";
    private boolean isSelected= false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isselected) {
        this.isSelected = isselected;
    }
}
