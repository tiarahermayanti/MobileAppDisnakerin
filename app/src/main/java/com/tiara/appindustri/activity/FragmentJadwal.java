package com.tiara.appindustri.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.adapter.AdapterJoinEvent;
import com.tiara.appindustri.model.DataJoinEvent;
import com.tiara.appindustri.model.ResponseGetJoinEvent;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentJadwal extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    AdapterJoinEvent adapterJoinEvent;
    SharedPrefManager sharedPrefManager;
    String idPengguna;
    @BindView(R.id.txt)
    TextView txt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);
        ButterKnife.bind(this, view);
        sharedPrefManager = new SharedPrefManager(getActivity());
        idPengguna = sharedPrefManager.getSP_IDPENGGUNA();
        ConfigRetrofit.getInstance().getJoinEvent(idPengguna).enqueue(new Callback<ResponseGetJoinEvent>() {
            @Override
            public void onResponse(Call<ResponseGetJoinEvent> call, Response<ResponseGetJoinEvent> response) {
                int s = response.body().getStatus();
                String p = response.body().getPesan();

                if (s == 1) {
                    txt.setVisibility(View.GONE);
                    List<DataJoinEvent> data = response.body().getData();
                    adapterJoinEvent = new AdapterJoinEvent(getActivity(), data);
                    recycler.setAdapter(adapterJoinEvent);
                    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                } else {
                   txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetJoinEvent> call, Throwable t) {
                Toast.makeText(getActivity(), "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}
