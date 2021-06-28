package com.tiara.appindustri.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseGetPemasaran{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataPemasaran> data;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public List<DataPemasaran> getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}