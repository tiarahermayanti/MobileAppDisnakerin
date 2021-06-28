package com.tiara.appindustri;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {


    public static final String SP_INDUSTRI_APP = "SIMPAN";
    public static final String SP_IDIKM = "spIdIkm";
    public static final String SP_IDPENGGUNA = "spIdpengguna";
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_FOTO = "spFoto";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_INDUSTRI_APP, Context.MODE_PRIVATE);

    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public void setSP_SUDAH_LOGIN (Boolean value) {
        spEditor = sp.edit();
        spEditor.putBoolean(SP_SUDAH_LOGIN, value).apply();
    }

    public void setSP_IDIKM(String id) {
        spEditor = sp.edit();
        spEditor.putString(SP_IDIKM, id).apply();
    }

    public void setSP_IDPENGGUNA(String id) {
        spEditor = sp.edit();
        spEditor.putString(SP_IDPENGGUNA, id).apply();
    }

    public void setSPNama(String id) {
        spEditor = sp.edit();
        spEditor.putString(SP_NAMA, id).apply();
    }

    public void setSPEmail(String id) {
        spEditor = sp.edit();
        spEditor.putString(SP_EMAIL, id).apply();
    }

    public void setSPFoto(String id) {
        spEditor = sp.edit();
        spEditor.putString(SP_FOTO, id).apply();
    }

    public String getSP_IDIKM(){
        return sp.getString(SP_IDIKM, "");
    }

    public String getSP_IDPENGGUNA(){
        return sp.getString(SP_IDPENGGUNA, "");
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPFoto(){
        return sp.getString(SP_FOTO, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }


}
