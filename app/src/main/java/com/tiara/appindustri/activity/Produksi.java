package com.tiara.appindustri.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.adapter.AdapterLegalitas;
import com.tiara.appindustri.adapter.AdapterPeralatan;
import com.tiara.appindustri.adapter.AdapterProduksi;
import com.tiara.appindustri.model.DataPeralatan;
import com.tiara.appindustri.model.DataProduksi;
import com.tiara.appindustri.model.ResponseGetProduksi;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Produksi extends AppCompatActivity {

    @BindView(R.id.txtBB)
    TextView txtBB;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    SharedPrefManager sharedPrefManager;
    AdapterProduksi adapterProduksi;
    String idIkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahan_baku);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        idIkm = sharedPrefManager.getSP_IDIKM();

        getData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    EditText edtNama, edtKapasitas, edtJumlah, edtSatuan, edtNilaiProduksi, edtNilaiPenjualan, edtTahun;
    String nama, kapasitas, jumlah, satuan, nilaiProduksi, nilaiPenjualan, tahun;

    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.activity_produksi, null);

        edtNama = (EditText) alertlayout.findViewById(R.id.edtNamaProduk);
        edtKapasitas = (EditText) alertlayout.findViewById(R.id.edtKapasitasProduk);
        edtJumlah = (EditText) alertlayout.findViewById(R.id.edtJmlh);
        edtSatuan = (EditText) alertlayout.findViewById(R.id.edtSatuan);
        edtNilaiProduksi = (EditText) alertlayout.findViewById(R.id.edtNilaiProduksi);
        edtNilaiPenjualan = (EditText) alertlayout.findViewById(R.id.edtNilaiPenjualan);
        edtTahun = (EditText) alertlayout.findViewById(R.id.edtTahun);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Input data");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama = edtNama.getText().toString();
                kapasitas = edtKapasitas.getText().toString();
                jumlah = edtJumlah.getText().toString();
                satuan = edtSatuan.getText().toString();
                nilaiProduksi = edtNilaiProduksi.getText().toString();
                nilaiPenjualan = edtNilaiPenjualan.getText().toString();
                tahun = edtTahun.getText().toString();

                ConfigRetrofit.getInstance().insertProduksi(idIkm, nama, kapasitas, jumlah, satuan, nilaiProduksi, nilaiPenjualan, tahun).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int stat = response.body().getStatus();
                        if(stat ==1){
                            Toast.makeText(Produksi.this, "Insert Data Berhasil", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        } else{
                            Toast.makeText(Produksi.this, "Insert Data Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(Produksi.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();

    }

    private void getData() {
        ConfigRetrofit.getInstance().getProduksi(idIkm).enqueue(new Callback<ResponseGetProduksi>() {
            @Override
            public void onResponse(Call<ResponseGetProduksi> call, Response<ResponseGetProduksi> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    txtBB.setVisibility(View.GONE);
                    List<DataProduksi> data = response.body().getData();
                    adapterProduksi = new AdapterProduksi(Produksi.this, data);
                    recycler.setAdapter(adapterProduksi);
                    recycler.setLayoutManager(new LinearLayoutManager(Produksi.this));
                    recycler.addItemDecoration(new DividerItemDecoration(Produksi.this, DividerItemDecoration.VERTICAL));
                } else {
                    txtBB.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetProduksi> call, Throwable t) {
                Toast.makeText(Produksi.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
