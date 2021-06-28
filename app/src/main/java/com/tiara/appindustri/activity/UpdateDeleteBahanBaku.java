package com.tiara.appindustri.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tiara.appindustri.R;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteBahanBaku extends AppCompatActivity {

    @BindView(R.id.edtNamaBB)
    EditText edtNamaBB;
    @BindView(R.id.spinJenis)
    Spinner spinJenis;
    @BindView(R.id.spinSumber)
    Spinner spinSumber;
    @BindView(R.id.edtJmlh)
    EditText edtJmlh;
    @BindView(R.id.edtNilaiBB)
    EditText edtNilaiBB;
    @BindView(R.id.edtTahun)
    EditText edtTahun;
    @BindView(R.id.btnHapus)
    Button btnHapus;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    String idBB, idIKM, namaBB, jenisBB, sumberBB, jmlhBB, nilaiBB, tahun;
    String paramNama, paramJenis, paramSumber, paramJmlh, paramNilai, paramTahun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_bahan_baku);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        idBB = extras.getString("idBB");
        idIKM = extras.getString("idIKM");
        namaBB = extras.getString("namaBB");
        jenisBB = extras.getString("jenisBB");
        sumberBB = extras.getString("sumberBB");
        jmlhBB = extras.getString("jmlhBB");
        nilaiBB = extras.getString("nilaiBB");
        tahun = extras.getString("tahun");

        getData();

        spinJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paramJenis = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinSumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paramSumber = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        hapusData();
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

    private void hapusData() {
        ConfigRetrofit.getInstance().deleteBahanBaku(idBB).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if (s == 1) {
                    startActivity(new Intent(UpdateDeleteBahanBaku.this, BahanBaku.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeleteBahanBaku.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateData() {

        paramNama = edtNamaBB.getText().toString();
        paramJmlh = edtJmlh.getText().toString();
        paramNilai = edtNilaiBB.getText().toString();
        paramTahun = edtTahun.getText().toString();

        ConfigRetrofit.getInstance().updateBahanBaku(idBB, paramNama, paramJenis, paramSumber, paramJmlh, paramNilai, paramTahun).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if (s == 1) {
                    startActivity(new Intent(UpdateDeleteBahanBaku.this, BahanBaku.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteBahanBaku.this, "Upadate Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeleteBahanBaku.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        edtNamaBB.setText(namaBB);
        edtJmlh.setText(jmlhBB);
        edtNilaiBB.setText(nilaiBB);
        edtTahun.setText(tahun);

        List<String> listJenis = new ArrayList<String>();
        listJenis.add("BAHAN BAKU");
        listJenis.add("BAHAN PENOLONG");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateDeleteBahanBaku.this,
                android.R.layout.simple_spinner_item, listJenis);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenis.setAdapter(adapter);
        if (jenisBB.equals("BAHAN BAKU")) {
            spinJenis.setSelection(adapter.getPosition("BAHAN BAKU"));
            paramJenis = jenisBB;
        } else if (jenisBB.equals("BAHAN PENOLONG")) {
            spinJenis.setSelection(adapter.getPosition("BAHAN PENOLONG"));
            paramJenis = jenisBB;
        }


        List<String> listSumber = new ArrayList<String>();
        listSumber.add("DALAM NEGERI");
        listSumber.add("IMPORT");

        ArrayAdapter<String> adapterSumber = new ArrayAdapter<String>(UpdateDeleteBahanBaku.this,
                android.R.layout.simple_spinner_item, listSumber);
        adapterSumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSumber.setAdapter(adapterSumber);
        if (sumberBB.equals("DALAM NEGERI")) {
            spinSumber.setSelection(adapterSumber.getPosition("DALAM NEGERI"));
            paramSumber = sumberBB;
        } else if (sumberBB.equals("IMPORT")) {
            spinSumber.setSelection(adapterSumber.getPosition("IMPORT"));
            paramSumber = sumberBB;
        }
    }
}
