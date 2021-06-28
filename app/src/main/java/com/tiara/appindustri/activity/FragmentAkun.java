package com.tiara.appindustri.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tiara.appindustri.BuildConfig.IMAGE_PROFILE_URL;

public class FragmentAkun extends Fragment {

    @BindView(R.id.rlProfile)
    RelativeLayout rlProfile;
    @BindView(R.id.ic_akun)
    CircularImageView icAkun;
    @BindView(R.id.foto)
    ImageView imgProfile;
    @BindView(R.id.rlIndustri)
    RelativeLayout rlIndustri;
    @BindView(R.id.rlBahanBaku)
    RelativeLayout rlBahanBaku;
    @BindView(R.id.rlEnergi)
    RelativeLayout rlEnergi;
    @BindView(R.id.rlLegalitas)
    RelativeLayout rlLegalitas;
    @BindView(R.id.rlPeralatan)
    RelativeLayout rlPeralatan;
    @BindView(R.id.rlProduksi)
    RelativeLayout rlProduksi;
    @BindView(R.id.rlHistory)
    RelativeLayout rlHistory;
    @BindView(R.id.rlLogout)
    RelativeLayout rlLogout;
    @BindView(R.id.rlFoto)
    RelativeLayout rlFoto;
    @BindView(R.id.rlPemasaran)
    RelativeLayout rlPemasaran;

    SharedPrefManager sharedPrefManager;
    String foto, name;
    @BindView(R.id.txtnama)
    TextView txtnama;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akun, container, false);
        ButterKnife.bind(this, view);
        sharedPrefManager = new SharedPrefManager(getActivity());
        foto = sharedPrefManager.getSPFoto();
        name = sharedPrefManager.getSPNama();
        txtnama.setText(name);
        if (foto == null) {
            imgProfile.setVisibility(View.GONE);
        } else {
            icAkun.setVisibility(View.GONE);
            Picasso.get().load(IMAGE_PROFILE_URL + foto).into(imgProfile);
        }

        rlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateAkun.class));
            }
        });

        rlIndustri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateDataIndustri.class));
            }
        });

        rlBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BahanBaku.class));
            }
        });

        rlPemasaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Pemasaran.class));
            }
        });

        rlEnergi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Energi.class));
            }
        });

        rlLegalitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Legalitas.class));
            }
        });

        rlPeralatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Peralatan.class));
            }
        });

        rlProduksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Produksi.class));
            }
        });

        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                sharedPrefManager.setSP_SUDAH_LOGIN(false);
                startActivity(new Intent(getActivity(), Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });

        rlHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryEvent.class));
            }
        });

        return view;

    }
}
