package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataLegalitas {

	@SerializedName("NAMA_IZIN")
	private String nAMAIZIN;

	@SerializedName("INSTANSI")
	private String iNSTANSI;

	@SerializedName("NO_IZIN")
	private String nOIZIN;

	@SerializedName("ID")
	private String iD;

	@SerializedName("ID_IKM")
	private String iDIKM;

	public String getNAMAIZIN(){
		return nAMAIZIN;
	}

	public String getINSTANSI(){
		return iNSTANSI;
	}

	public String getNOIZIN(){
		return nOIZIN;
	}

	public String getID(){
		return iD;
	}

	public String getIDIKM(){
		return iDIKM;
	}
}