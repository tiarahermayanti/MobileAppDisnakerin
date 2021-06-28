package com.tiara.appindustri.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tiara.appindustri.R;
import com.tiara.appindustri.model.DataIkmid;
import com.tiara.appindustri.model.ResponseGetIkmid;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUser extends AppCompatActivity {

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtNohp)
    EditText edtNohp;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.edtKonfPass)
    EditText edtKonfPass;
    @BindView(R.id.btnRegis)
    Button btnRegis;

    String nama, nohp, email, pass, konfPass, idikm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        nama = extras.getString("nama");
        nohp = extras.getString("nohp");

        edtNama.setText(nama);
        edtNohp.setText(nohp);

        ConfigRetrofit.getInstance().getIkmid(nohp).enqueue(new Callback<ResponseGetIkmid>() {
            @Override
            public void onResponse(Call<ResponseGetIkmid> call, Response<ResponseGetIkmid> response) {
                int status = response.body().getStatus();
                if(status == 1){
                    List<DataIkmid> data = response.body().getData();
                    for (int i = 0; i < data.size(); i++) {
                        idikm = data.get(i).getIDIKM();
                    }
                } else {
                    idikm = "";
                    Toast.makeText(RegisterUser.this, "ID IKM tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetIkmid> call, Throwable t) {
                Toast.makeText(RegisterUser.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(RegisterUser.this, idikm, Toast.LENGTH_SHORT).show();

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = edtPass.getText().toString();
                konfPass = edtKonfPass.getText().toString();
                email = edtEmail.getText().toString();


                if(pass.equals(konfPass) ){
                    initRegis();
                } else{
                    Toast.makeText(RegisterUser.this, "Konfirmasi Password Salah", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initRegis() {
        ConfigRetrofit.getInstance().create(idikm,nama, nohp, pass, email).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if(status ==1){
                    showDialog();
                } else {
                    Toast.makeText(RegisterUser.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(RegisterUser.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("REGISTRASI BERHASIL");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Menunggu Konfirmasi Admin Untuk Login")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent i = new Intent(RegisterUser.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                });


        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }
}
