package com.tiara.appindustri.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.tiara.appindustri.model.DataBahanBaku;
import com.tiara.appindustri.model.ResponseGetBahanBaku;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BahanBaku extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    AdapterBahanBaku adapterBahanBaku;
    SharedPrefManager sharedPrefManager;
    String idIkm;
    @BindView(R.id.txtBB)
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahan_baku);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        idIkm = sharedPrefManager.getSP_IDIKM();

        ConfigRetrofit.getInstance().getBahanBaku(idIkm).enqueue(new Callback<ResponseGetBahanBaku>() {
            @Override
            public void onResponse(Call<ResponseGetBahanBaku> call, Response<ResponseGetBahanBaku> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    txt.setVisibility(View.GONE);
                    List<DataBahanBaku> data = response.body().getData();
                    adapterBahanBaku = new AdapterBahanBaku(BahanBaku.this, data);
                    recycler.setAdapter(adapterBahanBaku);
                    recycler.setLayoutManager(new LinearLayoutManager(BahanBaku.this));
                    recycler.addItemDecoration(new DividerItemDecoration(BahanBaku.this, DividerItemDecoration.VERTICAL));
                } else {
                    txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBahanBaku> call, Throwable t) {
                Toast.makeText(BahanBaku.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    EditText edtNamaBB, edtJmlh, edtNilaiBB, edtTahun;
    Spinner spinJenis, spinSumber;
    String jenisBB, sumberBB, namaBB, jmlh, nilaiBB, tahun;

    String[] jenis = {
            "BAHAN BAKU", "BAHAN PENOLONG"
    };

    String[] sumber = {
            "DALAM NEGERI", "IMPORT"
    };

    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.insert_bahan_baku, null);

        edtNamaBB = (EditText) alertlayout.findViewById(R.id.edtNamaBB);
        spinJenis = (Spinner) alertlayout.findViewById(R.id.spinJenis);
        spinSumber = (Spinner) alertlayout.findViewById(R.id.spinSumber);
        edtJmlh = (EditText) alertlayout.findViewById(R.id.edtJmlh);
        edtNilaiBB = (EditText) alertlayout.findViewById(R.id.edtNilaiBB);
        edtTahun = (EditText) alertlayout.findViewById(R.id.edtTahun);

        final ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jenis);
        spinJenis.setAdapter(adapterJenis);

        spinJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jenisBB = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<String> adapterSumber = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sumber);
        spinSumber.setAdapter(adapterSumber);

        spinSumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sumberBB = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Input data");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                namaBB = edtNamaBB.getText().toString();
                jmlh = edtJmlh.getText().toString();
                nilaiBB = edtNilaiBB.getText().toString();
                tahun = edtTahun.getText().toString();

                ConfigRetrofit.getInstance().insertBahanBaku(idIkm, namaBB, jenisBB, sumberBB, jmlh, nilaiBB, tahun).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int stat = response.body().getStatus();
                        if(stat ==1){
                            Toast.makeText(BahanBaku.this, "Insert Data Berhasil", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        } else{
                            Toast.makeText(BahanBaku.this, "Insert Data Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(BahanBaku.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
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
