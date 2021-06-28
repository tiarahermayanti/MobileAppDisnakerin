package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataKbliById {

	@SerializedName("ID_KBLI")
	private String iDKBLI;

	@SerializedName("KBLI")
	private String kBLI;

	@SerializedName("ID_DIREKTORIPOTENSI")
	private String iDDIREKTORIPOTENSI;

	public String getIDKBLI(){
		return iDKBLI;
	}

	public String getKBLI(){
		return kBLI;
	}

	public String getIDDIREKTORIPOTENSI(){
		return iDDIREKTORIPOTENSI;
	}
}