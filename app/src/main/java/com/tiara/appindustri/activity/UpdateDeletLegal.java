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

public class UpdateDeletLegal extends AppCompatActivity {

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtInstansi)
    EditText edtInstansi;
    @BindView(R.id.edtNoIzin)
    EditText edtNoIzin;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnHapus)
    Button btnHapus;

    String id, idIkm, nama, instansi, noIzin;
    String  pnama, pinstansi, pNoIzin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delet_legal);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        idIkm = extras.getString("id_ikm");
        nama= extras.getString("nama");
        instansi = extras.getString("instansi");
        noIzin = extras.getString("no_izin");

        edtNama.setText(nama);
        edtInstansi.setText(instansi);
        edtNoIzin.setText(noIzin);

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
        ConfigRetrofit.getInstance().deleteLegalitas(id).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeletLegal.this, Legalitas.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeletLegal.this, "Hapus Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeletLegal.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        pnama = edtNama.getText().toString();
        pinstansi = edtInstansi.getText().toString();
        pNoIzin = edtNoIzin.getText().toString();

        ConfigRetrofit.getInstance().updateLegalitas(id, pnama, pinstansi, pNoIzin).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeletLegal.this, Legalitas.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeletLegal.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeletLegal.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
