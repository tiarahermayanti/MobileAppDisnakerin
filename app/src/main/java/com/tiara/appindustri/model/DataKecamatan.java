package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;


public class DataKecamatan {

	@SerializedName("KECAMATAN")
	private String kECAMATAN;

	@SerializedName("ID_KECAMATAN")
	private String iDKECAMATAN;

	public String getKECAMATAN(){
		return kECAMATAN;
	}

	public String getIDKECAMATAN(){
		return iDKECAMATAN;
	}
}