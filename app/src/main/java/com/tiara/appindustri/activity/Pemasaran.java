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
import com.tiara.appindustri.adapter.AdapterBahanBaku;
import com.tiara.appindustri.adapter.AdapterPemasaran;
import com.tiara.appindustri.model.DataBahanBaku;
import com.tiara.appindustri.model.DataPemasaran;
import com.tiara.appindustri.model.ResponseGetPemasaran;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pemasaran extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.txtBB)
    TextView txt;

    AdapterPemasaran adapterPemasaran;
    SharedPrefManager sharedPrefManager;
    String idIkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahan_baku);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        idIkm = sharedPrefManager.getSP_IDIKM();


        ConfigRetrofit.getInstance().getPemasaran(idIkm).enqueue(new Callback<ResponseGetPemasaran>() {
            @Override
            public void onResponse(Call<ResponseGetPemasaran> call, Response<ResponseGetPemasaran> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    txt.setVisibility(View.GONE);
                    List<DataPemasaran> data = response.body().getData();
                    adapterPemasaran = new AdapterPemasaran(Pemasaran.this, data);
                    recycler.setAdapter(adapterPemasaran);
                    recycler.setLayoutManager(new LinearLayoutManager(Pemasaran.this));
                    recycler.addItemDecoration(new DividerItemDecoration(Pemasaran.this, DividerItemDecoration.VERTICAL));
                } else {
                    txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPemasaran> call, Throwable t) {
                Toast.makeText(Pemasaran.this, "Tidak Ada Jaringa", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    EditText edtTkp, edtTkw, edtInves, edtLokal, edtLuar, edtEkspor, edtTahun;
    String tkp, tkw, inves, lokal, luar, ekspor, tahun;
    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.insert_pemasaran, null);
        edtTkp = (EditText) alertlayout.findViewById(R.id.edtTkp);
        edtTkw = (EditText) alertlayout.findViewById(R.id.edtTkw);
        edtInves = (EditText) alertlayout.findViewById(R.id.edtInvestasi);
        edtLuar = (EditText) alertlayout.findViewById(R.id.edtLuar);
        edtLokal = (EditText) alertlayout.findViewById(R.id.edtLokal);
        edtEkspor = (EditText) alertlayout.findViewById(R.id.edtEkspor);
        edtTahun = (EditText) alertlayout.findViewById(R.id.edtTahun);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Input data");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tkp = edtTkp.getText().toString();
                tkw = edtTkw.getText().toString();
                inves = edtInves.getText().toString();
                luar = edtLuar.getText().toString();
                lokal = edtLokal.getText().toString();
                ekspor = edtEkspor.getText().toString();
                tahun= edtTahun.getText().toString();

                ConfigRetrofit.getInstance().insertPemasaran(idIkm, tkp, tkw, inves, lokal, luar, ekspor, tahun).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int stat = response.body().getStatus();
                        if(stat ==1){
                            Toast.makeText(Pemasaran.this, "Insert Data Berhasil", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        } else{
                            Toast.makeText(Pemasaran.this, "Insert Data Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(Pemasaran.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
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
}
