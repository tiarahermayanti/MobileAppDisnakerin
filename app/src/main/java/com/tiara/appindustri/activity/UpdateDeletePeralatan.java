package com.tiara.appindustri.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tiara.appindustri.R;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeletePeralatan extends AppCompatActivity {

    @BindView(R.id.edtNamaMesin)
    EditText edtNamaMesin;
    @BindView(R.id.edtMerek)
    EditText edtMerek;
    @BindView(R.id.edtTahun)
    EditText edtTahun;
    @BindView(R.id.edtNegara)
    EditText edtNegara;
    @BindView(R.id.edtSpesifikasi)
    EditText edtSpesifikasi;
    @BindView(R.id.edtJumlah)
    EditText edtJumlah;
    @BindView(R.id.edtSatuan)
    EditText edtSatuan;
    @BindView(R.id.edtHarga)
    EditText edtHarga;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnHapus)
    Button btnHapus;

    String id, idIkm, nama, merek, tahun, negara, spesifikasi, jumlah, satuan, harga;
    String pnama, pmerek, ptahun, pnegara, pspesifikasi, pjumlah, psatuan, pharga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_peralatan);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        idIkm = extras.getString("id_ikm");
        nama = extras.getString("nama_mesin");
        merek = extras.getString("merek");
        tahun = extras.getString("tahun");
        negara = extras.getString("negara");
        spesifikasi = extras.getString("spesifikasi");
        jumlah = extras.getString("jumlah");
        satuan = extras.getString("satuan");
        harga = extras.getString("harga");

       edtNamaMesin.setText(nama);
       edtMerek.setText(merek);
       edtTahun.setText(tahun);
       edtNegara.setText(negara);
       edtSpesifikasi.setText(spesifikasi);
       edtJumlah.setText(jumlah);
       edtSatuan.setText(satuan);
       edtHarga.setText(harga);

       btnUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               updateData();
           }
       });

       btnHapus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDialog();
           }
       });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda Yakin Ingin Menghapus Data?")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        deleteData();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void deleteData() {
        ConfigRetrofit.getInstance().deletePeralatan(id).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeletePeralatan.this, Peralatan.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeletePeralatan.this, "Delete Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeletePeralatan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        pnama = edtNamaMesin.getText().toString();
        pmerek = edtMerek.getText().toString();
        ptahun = edtTahun.getText().toString();
        pnegara = edtNegara.getText().toString();
        pspesifikasi = edtSpesifikasi.getText().toString();
        pjumlah = edtJumlah.getText().toString();
        psatuan = edtSatuan.getText().toString();
        pharga = edtHarga.getText().toString();

        ConfigRetrofit.getInstance().updatePeralatan(id, pnama, pmerek, ptahun, pnegara, pspesifikasi, pjumlah, psatuan, pharga).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeletePeralatan.this, Peralatan.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeletePeralatan.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeletePeralatan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
