package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;


public class DataIkmById {

	@SerializedName("TGL_INPUT")
	private String tGLINPUT;

	@SerializedName("FOTO")
	private String fOTO;

	@SerializedName("ID_BENTUK_USAHA")
	private String iDBENTUKUSAHA;

	@SerializedName("LEGALITAS")
	private String lEGALITAS;

	@SerializedName("JENIS_PRODUK")
	private String jENISPRODUK;

	@SerializedName("ID_KELURAHAN")
	private String iDKELURAHAN;

	@SerializedName("ALAMAT")
	private String aLAMAT;

	@SerializedName("TELP")
	private String tELP;

	@SerializedName("STATUS")
	private String sTATUS;

	@SerializedName("ID_KBLI")
	private String iDKBLI;

	@SerializedName("KOORDINAT")
	private String kOORDINAT;

	@SerializedName("TAHUN_IZIN")
	private String tAHUNIZIN;

	@SerializedName("NAMA_PERUSAHAAN")
	private String nAMAPERUSAHAAN;

	@SerializedName("ID_IKM")
	private String iDIKM;

	@SerializedName("PIMPINAN")
	private String pIMPINAN;


	public String getTGLINPUT(){
		return tGLINPUT;
	}

	public String getFOTO(){
		return fOTO;
	}

	public String getIDBENTUKUSAHA(){
		return iDBENTUKUSAHA;
	}

	public String getLEGALITAS(){
		return lEGALITAS;
	}

	public String getJENISPRODUK(){
		return jENISPRODUK;
	}

	public String getIDKELURAHAN(){
		return iDKELURAHAN;
	}

	public String getALAMAT(){
		return aLAMAT;
	}

	public String getTELP(){
		return tELP;
	}

	public String getSTATUS(){
		return sTATUS;
	}

	public String getIDKBLI(){
		return iDKBLI;
	}

	public String getKOORDINAT(){
		return kOORDINAT;
	}

	public String getTAHUNIZIN(){
		return tAHUNIZIN;
	}

	public String getNAMAPERUSAHAAN(){
		return nAMAPERUSAHAAN;
	}

	public String getIDIKM(){
		return iDIKM;
	}

	public String getPIMPINAN(){
		return pIMPINAN;
	}
}