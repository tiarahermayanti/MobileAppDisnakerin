package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;


public class ResponseUpdate{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public int getStatus(){
		return status;
	}
}