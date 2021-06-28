package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;

public class DataBahanBaku {

	@SerializedName("SUMBER")
	private String sUMBER;

	@SerializedName("NAMA_BAHAN_BAKU")
	private String nAMABAHANBAKU;

	@SerializedName("JUMLAH_KEBUTUHAN")
	private String jUMLAHKEBUTUHAN;

	@SerializedName("JENIS_BAHAN_BAKU")
	private String jENISBAHANBAKU;

	@SerializedName("NILAI_BAHAN_BAKU")
	private String nILAIBAHANBAKU;

	@SerializedName("TAHUN")
	private String tAHUN;

	@SerializedName("ID")
	private String iD;

	@SerializedName("ID_IKM")
	private String iDIKM;

	public String getSUMBER(){
		return sUMBER;
	}

	public String getNAMABAHANBAKU(){
		return nAMABAHANBAKU;
	}

	public String getJUMLAHKEBUTUHAN(){
		return jUMLAHKEBUTUHAN;
	}

	public String getJENISBAHANBAKU(){
		return jENISBAHANBAKU;
	}

	public String getNILAIBAHANBAKU(){
		return nILAIBAHANBAKU;
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
}