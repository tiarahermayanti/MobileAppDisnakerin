package com.tiara.appindustri.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseLogin{

	@SerializedName("data_login")
	private List<DataLoginItem> dataLogin;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private int status;

	public List<DataLoginItem> getDataLogin(){
		return dataLogin;
	}

	public String getPesan(){
		return pesan;
	}

	public int getStatus(){
		return status;
	}
}