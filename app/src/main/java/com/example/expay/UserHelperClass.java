package com.example.expay;

public class UserHelperClass {
    String num_plate,Com_Name, rfid;
    int  credit, state;

    public String getNum_plate() {
        return num_plate;
    }

    public void setNum_plate(String num_plate) {
        this.num_plate = num_plate;
    }

    public String getCom_Name() {
        return Com_Name;
    }

    public void setCom_Name(String com_Name) {
        Com_Name = com_Name;
    }

    public String getRfid() {
        return rfid;
    }
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public UserHelperClass(String num_plate, String com_Name, String rfid, int credit, int state) {
        this.num_plate = num_plate;
        Com_Name = com_Name;
        this.rfid = rfid;
        this.credit = credit;
        this.state = state;

    }
}
