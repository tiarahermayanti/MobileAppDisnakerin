package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataPeralatan {

	@SerializedName("SATUAN")
	private String sATUAN;

	@SerializedName("NAMA_MESIN")
	private String nAMAMESIN;

	@SerializedName("SPESIFIKASI")
	private String sPESIFIKASI;

	@SerializedName("HARGA")
	private String hARGA;

	@SerializedName("NEGARA_ASAL")
	private String nEGARAASAL;

	@SerializedName("JUMLAH")
	private String jUMLAH;

	@SerializedName("TAHUN")
	private String tAHUN;

	@SerializedName("ID")
	private String iD;

	@SerializedName("ID_IKM")
	private String iDIKM;

	@SerializedName("MEREK")
	private String mEREK;

	public String getSATUAN(){
		return sATUAN;
	}

	public String getNAMAMESIN(){
		return nAMAMESIN;
	}

	public String getSPESIFIKASI(){
		return sPESIFIKASI;
	}

	public String getHARGA(){
		return hARGA;
	}

	public String getNEGARAASAL(){
		return nEGARAASAL;
	}

	public String getJUMLAH(){
		return jUMLAH;
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

	public String getMEREK(){
		return mEREK;
	}
}