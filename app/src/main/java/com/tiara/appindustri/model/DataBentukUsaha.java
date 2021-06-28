package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;

public class DataBentukUsaha {

	@SerializedName("BENTUK_USAHA")
	private String bENTUKUSAHA;

	@SerializedName("ID_BENTUK_USAHA")
	private String iDBENTUKUSAHA;

	public String getBENTUKUSAHA(){
		return bENTUKUSAHA;
	}

	public String getIDBENTUKUSAHA(){
		return iDBENTUKUSAHA;
	}
}