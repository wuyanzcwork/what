package com.zc.model;


/**
 * @author miracle
 * @data 2017/12/18
 */
public class SignalBaseInfo {

    private String checkinDateStr;

    private String checkoutDateStr;

    public int numberOfAdults = 2;

    public int numberOfChildren = 0;

    public String childAges;

    public int roomNumber = 1;

    public String getCheckinDateStr() {
        return checkinDateStr;
    }

    public void setCheckinDateStr(String checkinDateStr) {
        this.checkinDateStr = checkinDateStr;
    }

    public String getCheckoutDateStr() {
        return checkoutDateStr;
    }

    public void setCheckoutDateStr(String checkoutDateStr) {
        this.checkoutDateStr = checkoutDateStr;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getChildAges() {
        return childAges;
    }

    public void setChildAges(String childAges) {
        this.childAges = childAges;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
