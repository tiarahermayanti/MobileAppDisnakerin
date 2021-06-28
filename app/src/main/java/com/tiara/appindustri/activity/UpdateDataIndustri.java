package com.tiara.appindustri.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.model.DatKelurahanById;
import com.tiara.appindustri.model.DataBentukUsaha;
import com.tiara.appindustri.model.DataDirektoripotensi;
import com.tiara.appindustri.model.DataIkmById;
import com.tiara.appindustri.model.DataKbli;
import com.tiara.appindustri.model.DataKbliById;
import com.tiara.appindustri.model.DataKecamatan;
import com.tiara.appindustri.model.DataKelurahan;
import com.tiara.appindustri.model.ResponseGetBentukUsaha;
import com.tiara.appindustri.model.ResponseGetDirektoripotensi;
import com.tiara.appindustri.model.ResponseGetIkmById;
import com.tiara.appindustri.model.ResponseGetKecamatan;
import com.tiara.appindustri.model.ResponseGetKelurahan;
import com.tiara.appindustri.model.ResponseGetKelurahanById;
import com.tiara.appindustri.model.ResponseGetWhereKbli;
import com.tiara.appindustri.model.ResponseKbliById;
import com.tiara.appindustri.model.ResponseUpdate;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tiara.appindustri.BuildConfig.IMAGE_PROFILE_URL;
import static com.tiara.appindustri.BuildConfig.IMAGE_UPLOAD_URL;

public class UpdateDataIndustri extends AppCompatActivity {

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.spinBnetukUsaha)
    Spinner spinBnetukUsaha;
    @BindView(R.id.spinDirektoripotensi)
    Spinner spinDirektorip;
    @BindView(R.id.Spinkbli)
    Spinner spinkbli;
    @BindView(R.id.edtTahun)
    EditText edtTahun;
    @BindView(R.id.edtJenisProduk)
    EditText edtJenisProduk;
    @BindView(R.id.spinLegalitas)
    Spinner spinLegalitas;
    @BindView(R.id.edtPimpinan)
    EditText edtPimpinan;
    @BindView(R.id.btnPilihFoto)
    Button btnPilihFoto;
    @BindView(R.id.imgFoto)
    ImageView imgFoto;
    @BindView(R.id.edtTelp)
    EditText edtTelp;
    @BindView(R.id.btnKoor)
    Button btnMaps;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.spinKecamatan)
    Spinner spinKecamatan;
    @BindView(R.id.spinKelurahan)
    Spinner spinKelurahan;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    String idIkm, gIdKbli, gIdKlrh, gIdBentukUsaha, gLegal;
    String nama, pimpinan, telp, jenisProduk, tahun, foto;
    String setKlrh, setIdKcmt, setKcmt, setBentukUsaha, setKbli, setIdRepositorp, setRepositorip;
    String idBentukUsaha, idRepositorip, idkbli, legalitas, idKcmt, idKlrh, namaPlace, alamat, koordinat;


    HashMap<String, String> hmBentukUsaha = new HashMap<String, String>();
    HashMap<String, String> hmDirektorip = new HashMap<String, String>();
    HashMap<String, String> hmKbli = new HashMap<String, String>();
    HashMap<String, String> hmLegal = new HashMap<String, String>();
    HashMap<String, String> hmKcmt = new HashMap<String, String>();
    HashMap<String, String> hmKlrh = new HashMap<String, String>();

    private int PICK_IMAGE_REQUEST = 1;
    private int PLACE_PICKER_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Uri filePath;
    private String path;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        getDataIkm();
        spinBnetukUsaha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcBentukUsaha = parent.getItemAtPosition(position).toString();
                idBentukUsaha = hmBentukUsaha.get(slcBentukUsaha);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinDirektorip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcRepostirip = parent.getItemAtPosition(position).toString();
                idRepositorip = hmDirektorip.get(slcRepostirip);
                initKbli();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinkbli.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcKbli = parent.getItemAtPosition(position).toString();
                idkbli = hmKbli.get(slcKbli);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinLegalitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcLegal = parent.getItemAtPosition(position).toString();
                legalitas = hmLegal.get(slcLegal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcKcmt = parent.getItemAtPosition(position).toString();
                idKcmt = hmKcmt.get(slcKcmt);
                initKlurahan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String slcKlrh = parent.getItemAtPosition(position).toString();
                idKlrh = hmKlrh.get(slcKlrh);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnPilihFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    //menjalankan place picker
                    startActivityForResult(builder.build(UpdateDataIndustri.this), PLACE_PICKER_REQUEST);

                    // check apabila <a title="Solusi Tidak Bisa Download Google Play Services di Android" href="http://www.twoh.co/2014/11/solusi-tidak-bisa-download-google-play-services-di-android/" target="_blank">Google Play Services tidak terinstall</a> di HP
                } catch (GooglePlayServicesRepairableException e) {
                    Toast.makeText(UpdateDataIndustri.this, "catch1", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(UpdateDataIndustri.this, "catch2", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
    }

    private void initRegister() {
        nama = edtNama.getText().toString();
        tahun = edtTahun.getText().toString();
        alamat = edtAlamat.getText().toString();
        pimpinan = edtPimpinan.getText().toString();
        telp = edtTelp.getText().toString();
        jenisProduk = edtJenisProduk.getText().toString();


        String path = getPath(filePath);

        File imagefile = new File(path);
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);

        String imagePost = "uploadImage";

        MultipartBody.Part partImage = MultipartBody.Part.createFormData(imagePost, imagefile.getName(), reqBody);
        RequestBody rbidikm = RequestBody.create(MediaType.parse("text/plain"), idIkm);
        RequestBody rbidKlrh = RequestBody.create(MediaType.parse("text/plain"), idKlrh);
        RequestBody rbidBentukUsaha = RequestBody.create(MediaType.parse("text/plain"), idBentukUsaha);
        RequestBody rbidkbli = RequestBody.create(MediaType.parse("text/plain"), idkbli);
        RequestBody rbnama = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody rbpimpinan = RequestBody.create(MediaType.parse("text/plain"), pimpinan);
        RequestBody rbalamat = RequestBody.create(MediaType.parse("text/plain"), alamat);
        RequestBody rbkoordinat = RequestBody.create(MediaType.parse("text/plain"), koordinat);
        RequestBody rbtelp = RequestBody.create(MediaType.parse("text/plain"), telp);
        RequestBody rbtahun = RequestBody.create(MediaType.parse("text/plain"), tahun);
        RequestBody rbjenisProduk = RequestBody.create(MediaType.parse("text/plain"), jenisProduk);
        RequestBody rblegalitas = RequestBody.create(MediaType.parse("text/plain"), legalitas);

        ConfigRetrofit.getInstance().updateDetailIkm(partImage, rbidikm, rbidkbli, rbidKlrh, rbidBentukUsaha, rbnama, rbpimpinan, rbalamat,
                rbkoordinat, rbtelp, rbtahun, rbjenisProduk, rblegalitas).enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1) {
                    Toast.makeText(UpdateDataIndustri.this, pesan, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateDataIndustri.this, RegisterUser.class);
                    i.putExtra("nohp", telp);
                    i.putExtra("nama", pimpinan);
                    startActivity(i);

                } else {
                    Toast.makeText(UpdateDataIndustri.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                Toast.makeText(UpdateDataIndustri.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getDataIkm() {
        sharedPrefManager = new SharedPrefManager(this);
        idIkm = sharedPrefManager.getSP_IDIKM();

        ConfigRetrofit.getInstance().getIkmById(idIkm).enqueue(new Callback<ResponseGetIkmById>() {
            @Override
            public void onResponse(Call<ResponseGetIkmById> call, Response<ResponseGetIkmById> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1) {
                    List<DataIkmById> data = response.body().getData();
                    for (int i = 0; i < data.size(); i++) {
                        edtNama.setText(data.get(i).getNAMAPERUSAHAAN());
                        edtAlamat.setText(data.get(i).getALAMAT());
                        edtPimpinan.setText(data.get(i).getPIMPINAN());
                        edtTelp.setText(data.get(i).getTELP());
                        edtTahun.setText(data.get(i).getTAHUNIZIN());
                        edtJenisProduk.setText(data.get(i).getJENISPRODUK());
                        gIdBentukUsaha = data.get(i).getIDBENTUKUSAHA();
                        gIdKbli = data.get(i).getIDKBLI();
                        gIdKlrh = data.get(i).getIDKELURAHAN();
                        gLegal = data.get(i).getLEGALITAS();
                        foto = data.get(i).getFOTO();
                        koordinat = data.get(i).getKOORDINAT();
                        idBentukUsaha = gIdBentukUsaha;
                        idkbli = gIdKbli;
                        idKlrh = gIdKlrh;
                    }

                    Picasso.get().load(IMAGE_UPLOAD_URL + foto).into(imgFoto);

                    initKelurahanById();
                    initBentukUsaha();
                    initLegalitas();
                    initKbliById();

                    btnRegister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initRegister();
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<ResponseGetIkmById> call, Throwable t) {
                Toast.makeText(UpdateDataIndustri.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initLegalitas() {
        List<String> listSpinner = new ArrayList<String>();
        listSpinner.add("Memiliki Surat Izin");
        listSpinner.add("Tidak Memiliki Surat Izin");
        hmLegal.put("Memiliki Surat Izin", "FORMAL");
        hmLegal.put("Tidak Memiliki Surat Izin", "NON FORMAL");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDataIndustri.this,
                android.R.layout.simple_spinner_item, listSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLegalitas.setAdapter(adapter);
        if (gLegal.equals("FORMAL")) {
            spinLegalitas.setSelection(adapter.getPosition("Memiliki Surat Izin"));
            legalitas = "FORMAL";
        } else if (gLegal.equals("NON FORMAL")) {
            spinLegalitas.setSelection(adapter.getPosition("Tidak Memiliki Surat Izin"));
            legalitas = "NON FORMAL";
        }

    }

    private void initKelurahanById() {
        ConfigRetrofit.getInstance().getKelurahanById(gIdKlrh).enqueue(new Callback<ResponseGetKelurahanById>() {
            @Override
            public void onResponse(Call<ResponseGetKelurahanById> call, Response<ResponseGetKelurahanById> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                if (status == 1) {
                    List<DatKelurahanById> data = response.body().getData();
                    for (int i = 0; i < data.size(); i++) {
                        setKlrh = data.get(i).getKELURAHAN();
                        setIdKcmt = data.get(i).getIDKECAMATAN();

                    }

                    initKecamatan();
                    initKlurahan();
                } else {
                    Toast.makeText(UpdateDataIndustri.this, pesan, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseGetKelurahanById> call, Throwable t) {

            }
        });

    }

    private void initKecamatan() {
        ConfigRetrofit.getInstance().getKecamatan().enqueue(new Callback<ResponseGetKecamatan>() {
            @Override
            public void onResponse(Call<ResponseGetKecamatan> call, Response<ResponseGetKecamatan> response) {
                int status = response.body().getStatus();
                if (status == 1) {
                    List<DataKecamatan> data = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.size(); i++) {
                        listSpinner.add(data.get(i).getKECAMATAN());
                        hmKcmt.put(data.get(i).getKECAMATAN(), data.get(i).getIDKECAMATAN());

                        if (data.get(i).getIDKECAMATAN().equals(setIdKcmt)) {
                            setKcmt = data.get(i).getKECAMATAN();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDataIndustri.this, android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKecamatan.setAdapter(adapter);
                    spinKecamatan.setSelection(adapter.getPosition(setKcmt));

                }

            }

            @Override
            public void onFailure(Call<ResponseGetKecamatan> call, Throwable t) {

            }
        });
    }

    private void initKlurahan() {
        ConfigRetrofit.getInstance().getKelurahan(idKcmt).enqueue(new Callback<ResponseGetKelurahan>() {
            @Override
            public void onResponse(Call<ResponseGetKelurahan> call, Response<ResponseGetKelurahan> response) {
                int sts = response.body().getStatus();
                if (sts == 1) {
                    List<DataKelurahan> data = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.size(); i++) {
                        listSpinner.add(data.get(i).getKELURAHAN());
                        hmKlrh.put(data.get(i).getKELURAHAN(), data.get(i).getIDKELURAHAN());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDataIndustri.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinKelurahan.setAdapter(adapter);
                    spinKelurahan.setSelection(adapter.getPosition(setKlrh));
                    idKlrh = gIdKlrh;

                }
            }

            @Override
            public void onFailure(Call<ResponseGetKelurahan> call, Throwable t) {

            }
        });
    }

    private void initKbliById() {
        ConfigRetrofit.getInstance().getKbliById(gIdKbli).enqueue(new Callback<ResponseKbliById>() {
            @Override
            public void onResponse(Call<ResponseKbliById> call, Response<ResponseKbliById> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                if (status == 1) {
                    List<DataKbliById> data = response.body().getData();
                    for (int i = 0; i < data.size(); i++) {
                        setKbli = data.get(i).getKBLI();
                        setIdRepositorp = data.get(i).getIDDIREKTORIPOTENSI();
                    }
                    initDirektorip();
                    initKbli();
                } else {
                    Toast.makeText(UpdateDataIndustri.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKbliById> call, Throwable t) {

            }
        });
    }

    private void initDirektorip() {
        ConfigRetrofit.getInstance().getDirektoripotensi().enqueue(new Callback<ResponseGetDirektoripotensi>() {
            @Override
            public void onResponse(Call<ResponseGetDirektoripotensi> call, Response<ResponseGetDirektoripotensi> response) {
                int status = response.body().getStatus();
                if (status == 1) {
                    List<DataDirektoripotensi> dataDP = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < dataDP.size(); i++) {
                        listSpinner.add(dataDP.get(i).getDIREKTORIPOTENSI());
                        hmDirektorip.put(dataDP.get(i).getDIREKTORIPOTENSI(), dataDP.get(i).getIDDIREKTORIPOTENSI());

                        if (dataDP.get(i).getIDDIREKTORIPOTENSI().equals(setIdRepositorp)) {
                            setRepositorip = dataDP.get(i).getDIREKTORIPOTENSI();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDataIndustri.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinDirektorip.setAdapter(adapter);
                    spinDirektorip.setSelection(adapter.getPosition(setRepositorip));
                } else {
                    Toast.makeText(UpdateDataIndustri.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetDirektoripotensi> call, Throwable t) {
                Toast.makeText(UpdateDataIndustri.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initKbli() {
        ConfigRetrofit.getInstance().getkbli(idRepositorip).enqueue(new Callback<ResponseGetWhereKbli>() {
            @Override
            public void onResponse(Call<ResponseGetWhereKbli> call, Response<ResponseGetWhereKbli> response) {
                int status = response.body().getStatus();
                if (status == 1) {
                    List<DataKbli> data = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < data.size(); i++) {
                        listSpinner.add(data.get(i).getKBLI());
                        hmKbli.put(data.get(i).getKBLI(), data.get(i).getIDKBLI());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDataIndustri.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinkbli.setAdapter(adapter);
                    spinkbli.setSelection(adapter.getPosition(setKbli));
                    idkbli = gIdKbli;

                }
            }

            @Override
            public void onFailure(Call<ResponseGetWhereKbli> call, Throwable t) {

            }
        });
    }

    private void initBentukUsaha() {
        ConfigRetrofit.getInstance().getBentukUsaha().enqueue(new Callback<ResponseGetBentukUsaha>() {
            @Override
            public void onResponse(Call<ResponseGetBentukUsaha> call, Response<ResponseGetBentukUsaha> response) {
                int status = response.body().getStatus();
                if (status == 1) {
                    List<DataBentukUsaha> data = response.body().getData();
                    List<String> listSpinner = new ArrayList<String>();

                    for (int i = 0; i < data.size(); i++) {
                        listSpinner.add(data.get(i).getBENTUKUSAHA());
                        hmBentukUsaha.put(data.get(i).getBENTUKUSAHA(), data.get(i).getIDBENTUKUSAHA());

                        if (data.get(i).getIDBENTUKUSAHA().equals(gIdBentukUsaha)) {
                            setBentukUsaha = data.get(i).getBENTUKUSAHA();
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDataIndustri.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinBnetukUsaha.setAdapter(adapter);
                    spinBnetukUsaha.setSelection(adapter.getPosition(setBentukUsaha));
                    idBentukUsaha = gIdBentukUsaha;
                } else {
                    Toast.makeText(UpdateDataIndustri.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBentukUsaha> call, Throwable t) {
                Toast.makeText(UpdateDataIndustri.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //UPLOAD GAMBAR

    private void PilihGambar() {
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            // do your stuff..
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityIfNeeded(Intent.createChooser(intent, "Pilih Foto"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(filePath, imageprojection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                path = cursor.getString(indexImage);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imgFoto.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //         menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                namaPlace = place.getName().toString();
                alamat = place.getAddress().toString();
                koordinat = place.getLatLng().latitude + " " + place.getLatLng().longitude;
                edtAlamat.setText(alamat);

            }
        }
    }


    public String getPath(Uri uri) {

//        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
//            // do your stuff..
//        }

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;

    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "GET_ACCOUNTS Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
