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
import com.tiara.appindustri.adapter.AdapterPeralatan;
import com.tiara.appindustri.model.DataPeralatan;
import com.tiara.appindustri.model.ResponseGetPeralatan;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Peralatan extends AppCompatActivity {

    @BindView(R.id.txtBB)
    TextView txtBB;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    SharedPrefManager sharedPrefManager;
    AdapterPeralatan adapterPeralatan;
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

    EditText edtNamaMesin, edtMerek, edtTahun, edtNegara, edtSpesifikasi, edtJumlah, edtSatuan, edtHarga;
    String nama, merek, tahun, negara, spesifikasi, jumlah, satuan, harga;

    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.activity_peralatan, null);

        edtNamaMesin = (EditText) alertlayout.findViewById(R.id.edtNamaMesin);
        edtMerek = (EditText) alertlayout.findViewById(R.id.edtMerek);
        edtTahun = (EditText) alertlayout.findViewById(R.id.edtTahun);
        edtNegara = (EditText) alertlayout.findViewById(R.id.edtNegara);
        edtSpesifikasi = (EditText) alertlayout.findViewById(R.id.edtSpesifikasi);
        edtJumlah = (EditText) alertlayout.findViewById(R.id.edtJumlah);
        edtSatuan = (EditText) alertlayout.findViewById(R.id.edtSatuan);
        edtHarga = (EditText) alertlayout.findViewById(R.id.edtHarga);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Input data");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama = edtNamaMesin.getText().toString();
                merek = edtMerek.getText().toString();
                tahun = edtTahun.getText().toString();
                negara = edtNegara.getText().toString();
                spesifikasi = edtSpesifikasi.getText().toString();
                jumlah = edtJumlah.getText().toString();
                satuan = edtSatuan.getText().toString();
                harga = edtHarga.getText().toString();

                ConfigRetrofit.getInstance().insertPeralatan(idIkm, nama, merek, tahun, negara, spesifikasi, jumlah, satuan, harga).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int stat = response.body().getStatus();
                        if(stat ==1){
                            Toast.makeText(Peralatan.this, "Insert Data Berhasil", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        } else{
                            Toast.makeText(Peralatan.this, "Insert Data Gagal", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(Peralatan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
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
        ConfigRetrofit.getInstance().getPeralatan(idIkm).enqueue(new Callback<ResponseGetPeralatan>() {
            @Override
            public void onResponse(Call<ResponseGetPeralatan> call, Response<ResponseGetPeralatan> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    txtBB.setVisibility(View.GONE);
                    List<DataPeralatan> data = response.body().getData();
                    adapterPeralatan = new AdapterPeralatan(Peralatan.this, data);
                    recycler.setAdapter(adapterPeralatan);
                    recycler.setLayoutManager(new LinearLayoutManager(Peralatan.this));
                    recycler.addItemDecoration(new DividerItemDecoration(Peralatan.this, DividerItemDecoration.VERTICAL));
                } else {
                    txtBB.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPeralatan> call, Throwable t) {
                Toast.makeText(Peralatan.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
