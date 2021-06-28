package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataPengguna {

	@SerializedName("pengguna_moto")
	private String penggunaMoto;

	@SerializedName("pengguna_id")
	private String penggunaId;

	@SerializedName("pengguna_jenkel")
	private String penggunaJenkel;

	@SerializedName("pengguna_email")
	private String penggunaEmail;

	@SerializedName("pengguna_register")
	private String penggunaRegister;

	@SerializedName("pengguna_tentang")
	private String penggunaTentang;

	@SerializedName("pengguna_status")
	private String penggunaStatus;

	@SerializedName("pengguna_level")
	private String penggunaLevel;

	@SerializedName("pengguna_username")
	private String penggunaUsername;

	@SerializedName("pengguna_nohp")
	private String penggunaNohp;

	@SerializedName("pengguna_nama")
	private String penggunaNama;

	@SerializedName("pengguna_photo")
	private String penggunaPhoto;

	@SerializedName("pengguna_password")
	private String penggunaPassword;

	@SerializedName("ID_IKM")
	private String iDIKM;

	public String getPenggunaMoto(){
		return penggunaMoto;
	}

	public String getPenggunaId(){
		return penggunaId;
	}

	public String getPenggunaJenkel(){
		return penggunaJenkel;
	}

	public String getPenggunaEmail(){
		return penggunaEmail;
	}

	public String getPenggunaRegister(){
		return penggunaRegister;
	}

	public String getPenggunaTentang(){
		return penggunaTentang;
	}

	public String getPenggunaStatus(){
		return penggunaStatus;
	}

	public String getPenggunaLevel(){
		return penggunaLevel;
	}

	public String getPenggunaUsername(){
		return penggunaUsername;
	}

	public String getPenggunaNohp(){
		return penggunaNohp;
	}

	public String getPenggunaNama(){
		return penggunaNama;
	}

	public String getPenggunaPhoto(){
		return penggunaPhoto;
	}

	public String getPenggunaPassword(){
		return penggunaPassword;
	}

	public String getIDIKM(){
		return iDIKM;
	}
}