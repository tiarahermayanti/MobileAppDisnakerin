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
import com.tiara.appindustri.adapter.AdapterEnergi;
import com.tiara.appindustri.adapter.AdapterLegalitas;
import com.tiara.appindustri.model.DataEnergi;
import com.tiara.appindustri.model.DataLegalitas;
import com.tiara.appindustri.model.ResponseGetLegalitas;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Legalitas extends AppCompatActivity {

    @BindView(R.id.txtBB)
    TextView txtBB;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    SharedPrefManager sharedPrefManager;
    AdapterLegalitas adapterLegalitas;
    String idIkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahan_baku);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        idIkm = sharedPrefManager.getSP_IDIKM();

        getData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    EditText edtNama, edtInstansi, edtNoIzin;
    String nama, instansi, noizin;
    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.activity_legalitas, null);

        edtNama = (EditText) alertlayout.findViewById(R.id.edtNamaIzin);
        edtInstansi = (EditText) alertlayout.findViewById(R.id.edtInstansi);
        edtNoIzin = (EditText) alertlayout.findViewById(R.id.edtNoIzin);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Input data");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nama = edtNama.getText().toString();
                instansi = edtInstansi.getText().toString();
                noizin = edtNoIzin.getText().toString();

                ConfigRetrofit.getInstance().insertLegalitas(idIkm, nama, instansi, noizin).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int stat = response.body().getStatus();
                        if(stat ==1){
                            Toast.makeText(Legalitas.this, "Insert Data Berhasil", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            recreate();
                        } else{
                            Toast.makeText(Legalitas.this, "Insert Data Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(Legalitas.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
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

    private void getData() {
        ConfigRetrofit.getInstance().getLegalitas(idIkm).enqueue(new Callback<ResponseGetLegalitas>() {
            @Override
            public void onResponse(Call<ResponseGetLegalitas> call, Response<ResponseGetLegalitas> response) {
                int stat = response.body().getStatus();
                if (stat == 1) {
                    txtBB.setVisibility(View.GONE);
                    List<DataLegalitas> data = response.body().getData();
                    adapterLegalitas = new AdapterLegalitas(Legalitas.this, data);
                    recycler.setAdapter(adapterLegalitas);
                    recycler.setLayoutManager(new LinearLayoutManager(Legalitas.this));
                    recycler.addItemDecoration(new DividerItemDecoration(Legalitas.this, DividerItemDecoration.VERTICAL));
                } else{
                    txtBB.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetLegalitas> call, Throwable t) {
                Toast.makeText(Legalitas.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
