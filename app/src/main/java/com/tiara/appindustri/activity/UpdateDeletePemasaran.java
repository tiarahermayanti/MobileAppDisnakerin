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

public class UpdateDeletePemasaran extends AppCompatActivity {

    @BindView(R.id.edtTkp)
    EditText edtTkp;
    @BindView(R.id.edtTkw)
    EditText edtTkw;
    @BindView(R.id.edtInvestasi)
    EditText edtInvestasi;
    @BindView(R.id.edtLokal)
    EditText edtLokal;
    @BindView(R.id.edtLuar)
    EditText edtLuar;
    @BindView(R.id.edtEkspor)
    EditText edtEkspor;
    @BindView(R.id.edtTahun)
    EditText edtTahun;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnHapus)
    Button btnHapus;

    String id, idIkm, tkp, tkw, inves, lokal, luar, ekspor, tahun;
    String pid, pidIkm, ptkp, ptkw, pinves, plokal, pluar, pekspor, ptahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_pemasaran);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        idIkm = extras.getString("id_ikm");
        tkp = extras.getString("tkp");
        tkw = extras.getString("tkw");
        inves = extras.getString("investasi");
        lokal = extras.getString("lokal");
        luar = extras.getString("luar");
        ekspor = extras.getString("ekspor");
        tahun = extras.getString("tahun");

        edtTkp.setText(tkp);
        edtTkw.setText(tkw);
        edtInvestasi.setText(inves);
        edtLokal.setText(lokal);
        edtLuar.setText(luar);
        edtEkspor.setText(ekspor);
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
        ConfigRetrofit.getInstance().deletePemasaran(id).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeletePemasaran.this, Pemasaran.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeletePemasaran.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeletePemasaran.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        ptkw = edtTkw.getText().toString();
        ptkp = edtTkp.getText().toString();
        pinves = edtInvestasi.getText().toString();
        plokal = edtLokal.getText().toString();
        pluar = edtLuar.getText().toString();
        pekspor = edtEkspor.getText().toString();
        ptahun = edtTahun.getText().toString();

        ConfigRetrofit.getInstance().updatePemasaran(id, ptkp, ptkw, pinves, plokal, pluar, pekspor, ptahun).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeletePemasaran.this, Pemasaran.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeletePemasaran.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeletePemasaran.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
