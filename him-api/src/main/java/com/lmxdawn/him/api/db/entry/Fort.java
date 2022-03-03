package com.lmxdawn.him.api.db.entry;

import java.io.Serializable;

public class Fort implements Serializable {
    private String Fort_Address;//经纬度
    private String Fort_Name;
    private long Fort_Energy;


    public long getFort_Energy() {
        return Fort_Energy;
    }
    public void setFort_Energy(long fort_Energy) {
        Fort_Energy = fort_Energy;
    }

    public String getFort_Name() {
        return Fort_Name;
    }

    public void setFort_Name(String fort_Name) {
        Fort_Name = fort_Name;
    }

    public String getFort_Address() {
        return Fort_Address;
    }

    public void setFort_Address(String fort_Address) {
        Fort_Address = fort_Address;
    }

    public Fort() {
    }

}
