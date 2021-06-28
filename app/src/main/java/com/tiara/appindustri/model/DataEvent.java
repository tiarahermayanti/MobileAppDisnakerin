package com.tiara.appindustri.model;


import com.google.gson.annotations.SerializedName;


public class DataEvent {

	@SerializedName("event_deskripsi")
	private String eventDeskripsi;

	@SerializedName("event_kuota")
	private String eventKuota;

	@SerializedName("event_gambar")
	private String eventGambar;

	@SerializedName("tgl_post")
	private String tglPost;

	@SerializedName("event_id")
	private String eventId;

	@SerializedName("event_nama")
	private String eventNama;

	@SerializedName("event_jadwal")
	private String eventJadwal;

	@SerializedName("event_kota")
	private String eventKota;

	@SerializedName("event_alamat")
	private String eventAlamat;

	public String getEventDeskripsi(){
		return eventDeskripsi;
	}

	public String getEventKuota(){
		return eventKuota;
	}

	public String getEventGambar(){
		return eventGambar;
	}

	public String getTglPost(){
		return tglPost;
	}

	public String getEventId(){
		return eventId;
	}

	public String getEventNama(){
		return eventNama;
	}

	public String getEventJadwal(){
		return eventJadwal;
	}

	public String getEventKota(){
		return eventKota;
	}

	public String getEventAlamat(){
		return eventAlamat;
	}
}