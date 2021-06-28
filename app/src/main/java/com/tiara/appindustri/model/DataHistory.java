package com.tiara.appindustri.model;

import com.google.gson.annotations.SerializedName;

public class DataHistory {

	@SerializedName("event_gambar")
	private String eventGambar;

	@SerializedName("tgl_post")
	private String tglPost;

	@SerializedName("event_nama")
	private String eventNama;

	@SerializedName("id_join_event")
	private String idJoinEvent;

	@SerializedName("status_join_event")
	private String statusJoinEvent;

	@SerializedName("event_alamat")
	private String eventAlamat;

	@SerializedName("event_deskripsi")
	private String eventDeskripsi;

	@SerializedName("event_kuota")
	private String eventKuota;

	@SerializedName("event_id")
	private String eventId;

	@SerializedName("event_jadwal")
	private String eventJadwal;

	@SerializedName("event_tiket")
	private String eventTiket;

	@SerializedName("id_event")
	private String idEvent;

	@SerializedName("event_kota")
	private String eventKota;

	@SerializedName("id_pengguna")
	private String idPengguna;

	public String getEventGambar(){
		return eventGambar;
	}

	public String getTglPost(){
		return tglPost;
	}

	public String getEventNama(){
		return eventNama;
	}

	public String getIdJoinEvent(){
		return idJoinEvent;
	}

	public String getStatusJoinEvent(){
		return statusJoinEvent;
	}

	public String getEventAlamat(){
		return eventAlamat;
	}

	public String getEventDeskripsi(){
		return eventDeskripsi;
	}

	public String getEventKuota(){
		return eventKuota;
	}

	public String getEventId(){
		return eventId;
	}

	public String getEventJadwal(){
		return eventJadwal;
	}

	public String getEventTiket(){
		return eventTiket;
	}

	public String getIdEvent(){
		return idEvent;
	}

	public String getEventKota(){
		return eventKota;
	}

	public String getIdPengguna(){
		return idPengguna;
	}
}