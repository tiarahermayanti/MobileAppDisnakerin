package com.tiara.appindustri.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tiara.appindustri.BuildConfig.IMAGE_EVENT_URL;

public class DetailEvent extends AppCompatActivity {


    @BindView(R.id.imgBackground)
    ImageView imgBackground;
    @BindView(R.id.imgFoto)
    ImageView imgFoto;
    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.txtDeskrpisi)
    TextView txtDeskrpisi;
    @BindView(R.id.txtWaktu)
    TextView txtWaktu;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.btnDaftar)
    Button btnDaftar;

    SharedPrefManager sharedPrefManager;
    String nama,deskripsi, jadwal, alamat, kuota, gambar, idEvent, idPengguna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        idEvent = extras.getString("id_event");
        nama = extras.getString("nama");
        deskripsi = extras.getString("deskripsi");
        jadwal = extras.getString("jadwal");
        alamat = extras.getString("alamat");
        kuota = extras.getString("kuota");
        gambar = extras.getString("gambar");

        txtNama.setText(nama);
        txtDeskrpisi.setText(deskripsi);

        txtWaktu.setText(jadwal);
        txtAlamat.setText(alamat);
        Picasso.get().load(IMAGE_EVENT_URL+gambar).transform(new BlurTransformation(this, 25, 1)).into(imgBackground);
        Picasso.get().load(IMAGE_EVENT_URL+gambar).into(imgFoto);

        sharedPrefManager = new SharedPrefManager(this);
        idPengguna = sharedPrefManager.getSP_IDPENGGUNA();

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ConfigRetrofit.getInstance().insertJoinEvent(idEvent, idPengguna).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int status = response.body().getStatus();
                        String pesan = response.body().getPesan();

                        if(status ==1){
                            showDialog();
                        }
                        else{
                            Toast.makeText(DetailEvent.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(DetailEvent.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void showDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("DAFTAR EVENT");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Menunggu Konfirmasi Admin Untuk Mendapatkan Tiket")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent i = new Intent(DetailEvent.this, MainActivity.class);
                        startActivity(i);
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

}
