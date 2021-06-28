package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;


public class DatKelurahanById {

	@SerializedName("ID_KELURAHAN")
	private String iDKELURAHAN;

	@SerializedName("KELURAHAN")
	private String kELURAHAN;

	@SerializedName("ID_KECAMATAN")
	private String iDKECAMATAN;

	public String getIDKELURAHAN(){
		return iDKELURAHAN;
	}

	public String getKELURAHAN(){
		return kELURAHAN;
	}

	public String getIDKECAMATAN(){
		return iDKECAMATAN;
	}
}