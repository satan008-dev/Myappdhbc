package com.example.myappdhbc.model;

import com.example.myappdhbc.DATA;
import com.example.myappdhbc.choiGameActivity;
import com.example.myappdhbc.object.Cauhoi;
import com.example.myappdhbc.object.NguoiDung;

import java.util.ArrayList;

public class choiGameModels {
    choiGameActivity c;
    ArrayList<Cauhoi> arr;
    int cauSo=-1;
    public NguoiDung nguoiDung;

    public choiGameModels(choiGameActivity c) {
        this.c = c;
        nguoiDung =new NguoiDung();
        taoData();
    }
    private void taoData(){
        arr = new ArrayList<>(DATA.getData().arrCauHoi);
        //arr.add(new Cauhoi("Câu 1","baocao","https://3.bp.blogspot.com/-pzQILmYu4Jw/U8ePEjoEW2I/AAAAAAAACq8/QN8KosNpR70/s1600/2014-07-17+00.43.58-1.png"));
       // arr.add(new Cauhoi("Câu 2","hoga","https://1.bp.blogspot.com/-gyz54ig8HzY/U825SHd7yyI/AAAAAAAADWU/eScqetwzDVE/s1600/2014-07-22+01.35.15-1.png"));
       // arr.add(new Cauhoi("Câu 3","thienvi","https://1.bp.blogspot.com/-PXBUroFnK8I/VG7oouc7r4I/AAAAAAAAGsQ/sbX4kaiQ0bo/s1600/2014-11-21%2B00.10.28-1.png"));
      //  arr.add(new Cauhoi("Câu 4","kedon","https://4.bp.blogspot.com/-5EyA3VQaWYc/VG7odP-7_eI/AAAAAAAAGr4/zIUsaJ_ijik/s1600/2014-11-21%2B00.09.32-1.png"));
       // arr.add(new Cauhoi("Câu 5","traicoc","https://4.bp.blogspot.com/-_JpYB9uhhMk/VG7pjCXaTFI/AAAAAAAAGtA/mMkNcdfjvl0/s1600/2014-11-21%2B00.12.59-1.png"));

    }
    public Cauhoi layCauHoi(){
        cauSo++;
        if (cauSo>=arr.size()){
            cauSo=arr.size()-1;
        }
        return arr.get(cauSo);
    }
    public void layTT(){
        nguoiDung.getTT(c);

    }
    public void luuTT(){
        nguoiDung.saveTT(c);
    }
}
