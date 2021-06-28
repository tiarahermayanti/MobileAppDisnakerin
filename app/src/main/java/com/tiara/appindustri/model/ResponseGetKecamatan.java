package com.tiara.appindustri.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponseGetKecamatan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataKecamatan> data;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public List<DataKecamatan> getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}