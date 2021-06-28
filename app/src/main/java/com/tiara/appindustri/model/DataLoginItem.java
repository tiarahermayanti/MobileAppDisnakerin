package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;


public class DataLoginItem{

	@SerializedName("pengguna_id")
	private String penggunaId;

	@SerializedName("pengguna_nama")
	private String penggunaNama;

	@SerializedName("ID_IKM")
	private String iDIKM;

	@SerializedName("pengguna_email")
	private String penggunaEmail;

	@SerializedName("pengguna_photo")
	private String penggunaPhoto;

	public String getPenggunaId(){
		return penggunaId;
	}

	public String getPenggunaNama(){
		return penggunaNama;
	}

	public String getIDIKM(){
		return iDIKM;
	}

	public String getPenggunaEmail(){
		return penggunaEmail;
	}

	public String getPenggunaPhoto(){
		return penggunaPhoto;
	}
}