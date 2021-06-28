package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataEnergi {

	@SerializedName("JENIS_ENERGI")
	private String jENISENERGI;

	@SerializedName("PEMAKAIAN")
	private String pEMAKAIAN;

	@SerializedName("ID")
	private String iD;

	@SerializedName("ID_IKM")
	private String iDIKM;

	@SerializedName("KEBUTUHAN")
	private String kEBUTUHAN;

	public String getJENISENERGI(){
		return jENISENERGI;
	}

	public String getPEMAKAIAN(){
		return pEMAKAIAN;
	}

	public String getID(){
		return iD;
	}

	public String getIDIKM(){
		return iDIKM;
	}

	public String getKEBUTUHAN(){
		return kEBUTUHAN;
	}
}