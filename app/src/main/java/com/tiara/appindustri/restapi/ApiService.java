package com.tiara.appindustri.restapi;

import com.tiara.appindustri.model.ResponseGetBahanBaku;
import com.tiara.appindustri.model.ResponseGetBentukUsaha;
import com.tiara.appindustri.model.ResponseGetDetailIkm;
import com.tiara.appindustri.model.ResponseGetDirektoripotensi;
import com.tiara.appindustri.model.ResponseGetEnergi;
import com.tiara.appindustri.model.ResponseGetEvent;
import com.tiara.appindustri.model.ResponseGetHistory;
import com.tiara.appindustri.model.ResponseGetIkmById;
import com.tiara.appindustri.model.ResponseGetIkmid;
import com.tiara.appindustri.model.ResponseGetJoinEvent;
import com.tiara.appindustri.model.ResponseGetKecamatan;
import com.tiara.appindustri.model.ResponseGetKelurahan;
import com.tiara.appindustri.model.ResponseGetKelurahanById;
import com.tiara.appindustri.model.ResponseGetLegalitas;
import com.tiara.appindustri.model.ResponseGetPemasaran;
import com.tiara.appindustri.model.ResponseGetPengguna;
import com.tiara.appindustri.model.ResponseGetPeralatan;
import com.tiara.appindustri.model.ResponseGetProduksi;
import com.tiara.appindustri.model.ResponseGetWhereKbli;
import com.tiara.appindustri.model.ResponseKbliById;
import com.tiara.appindustri.model.ResponseLogin;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.model.ResponseSearch;
import com.tiara.appindustri.model.ResponseUpdate;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @FormUrlEncoded
    @POST("registerUser")
    Call<ResponsePost> create(
            @Field("id_ikm")String idIkm,
            @Field("nama")String nama,
            @Field("nohp")String nohp,
            @Field("password")String password,
            @Field("email")String email);

    @FormUrlEncoded
    @POST("getIdikm")
    Call<ResponseGetIkmid> getIkmid(@Field("nohp")String nohp);


    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> cek(
            @Field("nohp")String nohp,
            @Field("password")String password);

    @FormUrlEncoded
    @POST("search")
    Call<ResponseSearch> search(
            @Field("perusahaan")String nama_industri);

    @FormUrlEncoded
    @POST("getIkmById")
    Call<ResponseGetIkmById> getIkmById(@Field("id_ikm")String id_ikm);


    @GET("getDetailIkm")
    Call<ResponseGetDetailIkm> getDetailIkm();

    @GET("getBentukUsaha")
    Call<ResponseGetBentukUsaha> getBentukUsaha();

    @GET("getDirektoripotensi")
    Call<ResponseGetDirektoripotensi> getDirektoripotensi();

    @FormUrlEncoded
    @POST("getKbli")
    Call<ResponseGetWhereKbli> getkbli(@Field("id_direktoripotensi")String id_direktorip);

    @FormUrlEncoded
    @POST("getKbliById")
    Call<ResponseKbliById> getKbliById(@Field("id_kbli")String id_kbli);

    @GET("getKecamatan")
    Call<ResponseGetKecamatan> getKecamatan();

    @FormUrlEncoded
    @POST("getKelurahanById")
    Call<ResponseGetKelurahanById> getKelurahanById(@Field("id_kelurahan")String id_kelurahan);

    @FormUrlEncoded
    @POST("getKelurahan")
    Call<ResponseGetKelurahan> getKelurahan(@Field("id_kecamatan")String id_kecamatan);

    @Multipart
    @POST("insertDetailIkm")
    Call<ResponsePost> insertDetailIkm(
                        @Part MultipartBody.Part uploadImage,
                        @Part("idKbli") RequestBody idKbli,
                        @Part("idKlrh")RequestBody idKlrh,
                        @Part("idBentukUsaha")RequestBody idBentukUsaha ,
                        @Part("namaPerusahaan")RequestBody namaPerusahaan,
                        @Part("pimpinan")RequestBody pimpinan,
                        @Part("alamat")RequestBody alamat,
                        @Part("koordinat")RequestBody koordinat,
                        @Part("telp")RequestBody telp,
                        @Part("tahun")RequestBody tahun,
                        @Part("jenisProduk")RequestBody jenisProduk,
                        @Part("legal")RequestBody  legal);

    @Multipart
    @POST("updateDetailIkm")
    Call<ResponseUpdate> updateDetailIkm(
            @Part MultipartBody.Part uploadImage,
            @Part("idIkm") RequestBody idIkm,
            @Part("idKbli") RequestBody idKbli,
            @Part("idKlrh")RequestBody idKlrh,
            @Part("idBentukUsaha")RequestBody idBentukUsaha ,
            @Part("namaPerusahaan")RequestBody namaPerusahaan,
            @Part("pimpinan")RequestBody pimpinan,
            @Part("alamat")RequestBody alamat,
            @Part("koordinat")RequestBody koordinat,
            @Part("telp")RequestBody telp,
            @Part("tahun")RequestBody tahun,
            @Part("jenisProduk")RequestBody jenisProduk,
            @Part("legal")RequestBody  legal);


    @GET("getEvent")
    Call<ResponseGetEvent> getEvent();

    @FormUrlEncoded
    @POST("insertJoinEvent")
    Call<ResponsePost> insertJoinEvent(
            @Field("idEvent")String idEvent,
            @Field("idPengguna")String idPengguna);

    @FormUrlEncoded
    @POST("getJoinEvent")
    Call<ResponseGetJoinEvent> getJoinEvent(
            @Field("id_pengguna")String idPengguna);

    @FormUrlEncoded
    @POST("getHistoryEvent")
    Call<ResponseGetHistory> getHistoryEvent(
            @Field("id_pengguna")String idPengguna);


    @FormUrlEncoded
    @POST("getByIdBahanBaku")
    Call<ResponseGetBahanBaku> getBahanBaku(
            @Field("id_ikm")String id);

    @FormUrlEncoded
    @POST("insertBahanBaku")
    Call<ResponsePost> insertBahanBaku(
            @Field("id_ikm")String id_ikm,
            @Field("namabb")String namabb,
            @Field("jenisbb")String jenisbb,
            @Field("sumber")String sumber,
            @Field("jmlhkebutuhan")String jmlhkebutuhan,
            @Field("nilaibb")String nilaibb,
            @Field("tahun")String tahun);

    @FormUrlEncoded
    @POST("updateBahanBaku")
    Call<ResponsePost> updateBahanBaku(
            @Field("id")String id,
            @Field("namabb")String namabb,
            @Field("jenisbb")String jenisbb,
            @Field("sumber")String sumber,
            @Field("jmlhkebutuhan")String jmlhkebutuhan,
            @Field("nilaibb")String nilaibb,
            @Field("tahun")String tahun);

    @FormUrlEncoded
    @POST("deleteBahanBaku")
    Call<ResponsePost> deleteBahanBaku(
            @Field("id")String id);

    @FormUrlEncoded
    @POST("getByIdEnergi")
    Call<ResponseGetEnergi> getEnergi(
            @Field("id_ikm")String id_ikm);

    @FormUrlEncoded
    @POST("insertEnergi")
    Call<ResponsePost> insertEnergi(
            @Field("id_ikm")String id_ikm,
            @Field("jenis")String jenis,
            @Field("pemakaian")String pemakaian,
            @Field("kebutuhan")String kebutuhan );

    @FormUrlEncoded
    @POST("updateEnergi")
    Call<ResponsePost> updateEnergi(
            @Field("id")String id,
            @Field("jenis")String jenis,
            @Field("pemakaian")String pemakaian,
            @Field("kebutuhan")String kebutuhan );

    @FormUrlEncoded
    @POST("deleteEnergi")
    Call<ResponsePost> deleteEnergi(
            @Field("id")String id);

    @FormUrlEncoded
    @POST("getByIdLegalitas")
    Call<ResponseGetLegalitas> getLegalitas(
            @Field("id_ikm")String id_ikm);

    @FormUrlEncoded
    @POST("insertLegalitas")
    Call<ResponsePost> insertLegalitas(
            @Field("id_ikm")String id_ikm,
            @Field("namaIzin")String namaIzin,
            @Field("instansi")String instansi,
            @Field("noIzin")String noIzin );

    @FormUrlEncoded
    @POST("updateLegalitas")
    Call<ResponsePost> updateLegalitas(
            @Field("id")String id,
            @Field("namaIzin")String namaIzin,
            @Field("instansi")String instansi,
            @Field("noIzin")String noIzin );

    @FormUrlEncoded
    @POST("deleteLegalitas")
    Call<ResponsePost> deleteLegalitas(
            @Field("id")String id);

    @FormUrlEncoded
    @POST("getByIdPeralatan")
    Call<ResponseGetPeralatan> getPeralatan(
            @Field("id_ikm")String id_ikm);

    @FormUrlEncoded
    @POST("insertPeralatan")
    Call<ResponsePost> insertPeralatan(
                    @Field("id_ikm")String id_ikm,
                    @Field("nama_mesin")String nama_mesin,
                    @Field("merek")String merek,
                    @Field("tahun")String tahun,
                    @Field("negara")String negara,
                    @Field("spesifikasi")String spesifikasi,
                    @Field("jumlah")String jumlah,
                    @Field("satuan")String satuan,
                    @Field("harga")String harga  );

    @FormUrlEncoded
    @POST("updatePeralatan")
    Call<ResponsePost> updatePeralatan(
            @Field("id")String id,
            @Field("nama_mesin")String nama_mesin,
            @Field("merek")String merek,
            @Field("tahun")String tahun,
            @Field("negara")String negara,
            @Field("spesifikasi")String spesifikasi,
            @Field("jumlah")String jumlah,
            @Field("satuan")String satuan,
            @Field("harga")String harga  );

    @FormUrlEncoded
    @POST("deletePeralatan")
    Call<ResponsePost> deletePeralatan(
            @Field("id")String id);

    @FormUrlEncoded
    @POST("getByIdProduksi")
    Call<ResponseGetProduksi> getProduksi(
            @Field("id_ikm")String id_ikm);

    @FormUrlEncoded
    @POST("insertProduksi")
    Call<ResponsePost> insertProduksi(
            @Field("id_ikm")String id_ikm,
            @Field("nama")String nama,
            @Field("kapasitas")String kapasitas,
            @Field("jumlah")String jumlah,
            @Field("satuan")String satuan,
            @Field("nilai_produksi")String nilai_produksi,
            @Field("nilai_penjualan")String nilai_penjualan,
            @Field("tahun")String tahun);

    @FormUrlEncoded
    @POST("updateProduksi")
    Call<ResponsePost> updateProduksi(
            @Field("id")String id,
            @Field("nama")String nama,
            @Field("kapasitas")String kapasitas,
            @Field("jumlah")String jumlah,
            @Field("satuan")String satuan,
            @Field("nilai_produksi")String nilai_produksi,
            @Field("nilai_penjualan")String nilai_penjualan,
            @Field("tahun")String tahun);

    @FormUrlEncoded
    @POST("deleteProduksi")
    Call<ResponsePost> deleteProduksi(
            @Field("id")String id);


    @FormUrlEncoded
    @POST("getByIdPemasaran")
    Call<ResponseGetPemasaran> getPemasaran(
            @Field("id_ikm")String id_ikm);

    @FormUrlEncoded
    @POST("insertPemasaran")
    Call<ResponsePost> insertPemasaran(
            @Field("id_ikm")String id_ikm,
            @Field("tkp")String tkp,
            @Field("tkw")String tkw,
            @Field("investasi")String investasi,
            @Field("lokal")String lokal,
            @Field("luar")String luar,
            @Field("ekspor")String ekspor,
            @Field("tahun")String tahun);

    @FormUrlEncoded
    @POST("updatePemasaran")
    Call<ResponsePost> updatePemasaran(
            @Field("id")String id,
            @Field("tkp")String tkp,
            @Field("tkw")String tkw,
            @Field("investasi")String investasi,
            @Field("lokal")String lokal,
            @Field("luar")String luar,
            @Field("ekspor")String ekspor,
            @Field("tahun")String tahun);

    @FormUrlEncoded
    @POST("deletePemasaran")
    Call<ResponsePost> deletePemasaran(
            @Field("id")String id);

    @FormUrlEncoded
    @POST("getByIdPengguna")
    Call<ResponseGetPengguna> getPengguna(
            @Field("pengguna_id")String pengguna_id);

    @FormUrlEncoded
    @POST("updatePengguna")
    Call<ResponsePost> updatePengguna(
            @Field("id")String id,
            @Field("nama")String nama,
            @Field("jenkel")String jenkel,
            @Field("no_hp")String no_hp,
            @Field("email")String email,
            @Field("moto")String moto,
            @Field("tentang")String tentang);

    @Multipart
    @POST("updateFotoPengguna")
    Call<ResponsePost> updateFotoPengguna(
            @Part("id") RequestBody id,
            @Part MultipartBody.Part uploadImage);

}


