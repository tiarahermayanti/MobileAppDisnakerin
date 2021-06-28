package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataPemasaran {

    @SerializedName("EKSPOR")
    private String eKSPOR;

    @SerializedName("TENAGA_KERJA_P")
    private String tENAGAKERJAP;

    @SerializedName("PEMASARAN_LUAR_DAERAH")
    private String pEMASARANLUARDAERAH;

    @SerializedName("TENAGA_KERJA_W")
    private String tENAGAKERJAW;

    @SerializedName("PEMASARAN_LOKAL")
    private String pEMASARANLOKAL;

    @SerializedName("TAHUN")
    private String tAHUN;

    @SerializedName("ID")
    private String iD;

    @SerializedName("ID_IKM")
    private String iDIKM;

    @SerializedName("NILAI_INVESTASI")
    private String nILAIINVESTASI;

    public String getEKSPOR(){
        return eKSPOR;
    }

    public String getTENAGAKERJAP(){
        return tENAGAKERJAP;
    }

    public String getPEMASARANLUARDAERAH(){
        return pEMASARANLUARDAERAH;
    }

    public String getTENAGAKERJAW(){
        return tENAGAKERJAW;
    }

    public String getPEMASARANLOKAL(){
        return pEMASARANLOKAL;
    }

    public String getTAHUN(){
        return tAHUN;
    }

    public String getID(){
        return iD;
    }

    public String getIDIKM(){
        return iDIKM;
    }

    public String getNILAIINVESTASI(){
        return nILAIINVESTASI;
    }
}