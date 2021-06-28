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

public class UpdateDeleteProduksi extends AppCompatActivity {

    @BindView(R.id.edtNamaProduk)
    EditText edtNamaProduk;
    @BindView(R.id.edtKapasitas)
    EditText edtKapasitas;
    @BindView(R.id.edtJmlh)
    EditText edtJmlh;
    @BindView(R.id.edtSatuan)
    EditText edtSatuan;
    @BindView(R.id.edtNilaiProduksi)
    EditText edtNilaiProduksi;
    @BindView(R.id.edtNilaiPenjualan)
    EditText edtNilaiPenjualan;
    @BindView(R.id.edtTahun)
    EditText edtTahun;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnHapus)
    Button btnHapus;

    String id, idIkm, nama, kapasitas, jmlh, satuan, nilaiProduksi, nilaiPenjualan, tahun;
    String pid, pidIkm, pnama, pkapasitas, pjmlh, psatuan, pnilaiProduksi, pnilaiPenjualan, ptahun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_produksi);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        idIkm = extras.getString("id_ikm");
        nama = extras.getString("nama_produk");
        kapasitas = extras.getString("kapasitas");
        jmlh = extras.getString("jumlah");
        satuan = extras.getString("satuan");
        nilaiProduksi = extras.getString("nilai_produksi");
        nilaiPenjualan = extras.getString("nilai_penjualan");
        tahun = extras.getString("tahun");

       edtNamaProduk.setText(nama);
       edtKapasitas.setText(kapasitas);
       edtJmlh.setText(jmlh);
       edtSatuan.setText(satuan);
       edtNilaiProduksi.setText(nilaiProduksi);
       edtNilaiPenjualan.setText(nilaiPenjualan);
       edtTahun.setText(tahun);

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
        ConfigRetrofit.getInstance().deleteProduksi(id).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeleteProduksi.this, Produksi.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteProduksi.this, "Delete Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeleteProduksi.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        pnama = edtNamaProduk.getText().toString();
        pkapasitas = edtKapasitas.getText().toString();
        pjmlh = edtJmlh.getText().toString();
        psatuan = edtSatuan.getText().toString();
        pnilaiProduksi = edtNilaiProduksi.getText().toString();
        pnilaiPenjualan = edtNilaiPenjualan.getText().toString();
        ptahun = edtTahun.getText().toString();

        ConfigRetrofit.getInstance().updateProduksi(id, pnama, pkapasitas, pjmlh, psatuan, pnilaiProduksi, pnilaiPenjualan, tahun).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeleteProduksi.this, Produksi.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteProduksi.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeleteProduksi.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
