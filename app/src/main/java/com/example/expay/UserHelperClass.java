package com.example.expay;

public class UserHelperClass {
    String num_plate,Com_Name;

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

    public UserHelperClass(String num_plate, String com_Name) {
        this.num_plate = num_plate;
        Com_Name = com_Name;
    }
}
