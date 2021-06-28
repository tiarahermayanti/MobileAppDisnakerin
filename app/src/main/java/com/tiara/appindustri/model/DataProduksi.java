package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataProduksi {

	@SerializedName("SATUAN")
	private String sATUAN;

	@SerializedName("NAMA_PRODUK")
	private String nAMAPRODUK;

	@SerializedName("JUMLAH_PRODUKSI")
	private String jUMLAHPRODUKSI;

	@SerializedName("KAPASITAS_PRODUKSI")
	private String kAPASITASPRODUKSI;

	@SerializedName("NILAI_PENJUALAN")
	private String nILAIPENJUALAN;

	@SerializedName("TAHUN")
	private String tAHUN;

	@SerializedName("ID")
	private String iD;

	@SerializedName("ID_IKM")
	private String iDIKM;

	@SerializedName("NILAI_PRODUKSI")
	private String nILAIPRODUKSI;

	public String getSATUAN(){
		return sATUAN;
	}

	public String getNAMAPRODUK(){
		return nAMAPRODUK;
	}

	public String getJUMLAHPRODUKSI(){
		return jUMLAHPRODUKSI;
	}

	public String getKAPASITASPRODUKSI(){
		return kAPASITASPRODUKSI;
	}

	public String getNILAIPENJUALAN(){
		return nILAIPENJUALAN;
	}

	public String getTAHUN(){
		return tAHUN;
	}

	public String getID(){
		return iD;
	}

	public String getIDIKM(){
		return iDIKM;
	}

	public String getNILAIPRODUKSI(){
		return nILAIPRODUKSI;
	}
}