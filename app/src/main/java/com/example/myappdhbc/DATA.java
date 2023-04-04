package com.example.myappdhbc;

import com.example.myappdhbc.object.Cauhoi;

import java.util.ArrayList;

public class DATA {
    private static DATA data;
    static {
        data = new DATA();
    }
    public static DATA getData(){

        return data;
    }
   public  ArrayList<Cauhoi> arrCauHoi=new ArrayList<>();
}
