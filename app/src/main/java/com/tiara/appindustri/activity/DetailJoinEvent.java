package com.tiara.appindustri.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tiara.appindustri.BuildConfig.IMAGE_EVENT_URL;

public class DetailJoinEvent extends AppCompatActivity {

    @BindView(R.id.imgFoto)
    ImageView imgFoto;
    @BindView(R.id.txtNamaP)
    TextView txtNamaP;
    @BindView(R.id.txtNamaE)
    TextView txtNamaE;
    @BindView(R.id.txtWaktu)
    TextView txtWaktu;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtDeskrpisi)
    TextView txtDeskrpisi;

    String namaE, namaP, jadwal, alamat, deskrpsi, img;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_join_event);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        namaE = extras.getString("nama");
        jadwal = extras.getString("jadwal");
        alamat = extras.getString("alamat");
        deskrpsi = extras.getString("deskripsi");
        img = extras.getString("gambar");

        sharedPrefManager = new SharedPrefManager(this);

        namaP = sharedPrefManager.getSPNama();

        txtNamaP.setText(namaP);
        txtNamaE.setText(namaE);
        txtAlamat.setText(alamat);
        txtWaktu.setText(jadwal);
        txtDeskrpisi.setText(deskrpsi);
        Picasso.get().load(IMAGE_EVENT_URL+img).into(imgFoto);

    }
}
