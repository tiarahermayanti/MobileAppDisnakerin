package com.tiara.appindustri.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.adapter.AdapterHistory;
import com.tiara.appindustri.adapter.AdapterJoinEvent;
import com.tiara.appindustri.model.DataHistory;
import com.tiara.appindustri.model.DataJoinEvent;
import com.tiara.appindustri.model.ResponseGetHistory;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryEvent extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    AdapterHistory adapterHistory;
    SharedPrefManager sharedPrefManager;
    String idPengguna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_event);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        idPengguna = sharedPrefManager.getSP_IDPENGGUNA();

        ConfigRetrofit.getInstance().getHistoryEvent(idPengguna).enqueue(new Callback<ResponseGetHistory>() {
            @Override
            public void onResponse(Call<ResponseGetHistory> call, Response<ResponseGetHistory> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();

                if (s == 1) {
                    txt.setVisibility(View.GONE);
                    List<DataHistory> data = response.body().getData();
                    adapterHistory = new AdapterHistory(HistoryEvent.this, data);
                    recycler.setAdapter(adapterHistory);
                    recycler.setLayoutManager(new LinearLayoutManager(HistoryEvent.this));
                    recycler.addItemDecoration(new DividerItemDecoration(HistoryEvent.this, DividerItemDecoration.VERTICAL));

                } else {
                    txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetHistory> call, Throwable t) {
                Toast.makeText(HistoryEvent.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
