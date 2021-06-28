package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;


public class DataDirektoripotensi {

	@SerializedName("DIREKTORIPOTENSI")
	private String dIREKTORIPOTENSI;

	@SerializedName("ID_DIREKTORIPOTENSI")
	private String iDDIREKTORIPOTENSI;

	public String getDIREKTORIPOTENSI(){
		return dIREKTORIPOTENSI;
	}

	public String getIDDIREKTORIPOTENSI(){
		return iDDIREKTORIPOTENSI;
	}
}