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

public class UpdateDeleteEnergi extends AppCompatActivity {

    @BindView(R.id.edtJenis)
    EditText edtJenis;
    @BindView(R.id.edtPemakaian)
    EditText edtPemakaian;
    @BindView(R.id.edtKebutuhan)
    EditText edtKebutuhan;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnHapus)
    Button btnHapus;

    String id, idIkm, jenis, kebutuhan, pemakaian;
    String pJenis, pKebutuhan, pPemakaian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_energi);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        idIkm = extras.getString("id_ikm");
        jenis = extras.getString("jenis");
        kebutuhan = extras.getString("kebutuhan");
        pemakaian = extras.getString("pemakaian");

        edtJenis.setText(jenis);
        edtPemakaian.setText(pemakaian);
        edtKebutuhan.setText(kebutuhan);

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
        ConfigRetrofit.getInstance().deleteEnergi(id).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeleteEnergi.this, Energi.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteEnergi.this, "Hapus Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeleteEnergi.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData() {
        pJenis = edtJenis.getText().toString();
        pKebutuhan = edtKebutuhan.getText().toString();
        pPemakaian = edtPemakaian.getText().toString();

        ConfigRetrofit.getInstance().updateEnergi(id, pJenis, pPemakaian, pKebutuhan).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if(s == 1){
                    startActivity(new Intent(UpdateDeleteEnergi.this, Energi.class));
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteEnergi.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateDeleteEnergi.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
