package com.tiara.appindustri.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiara.appindustri.R;
import com.tiara.appindustri.adapter.AdapterEvent;
import com.tiara.appindustri.model.DataEvent;
import com.tiara.appindustri.model.ResponseGetEvent;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    @BindView(R.id.recyclerEvent)
    RecyclerView recyclerEvent;
    AdapterEvent adapterEvent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);

        ConfigRetrofit.getInstance().getEvent().enqueue(new Callback<ResponseGetEvent>() {
            @Override
            public void onResponse(Call<ResponseGetEvent> call, Response<ResponseGetEvent> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                if(status ==1){
                    List<DataEvent> data = response.body().getData();
                    adapterEvent = new AdapterEvent(getActivity(),data);
                    recyclerEvent.setAdapter(adapterEvent);
                    recyclerEvent.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerEvent.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                } else{
                    Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetEvent> call, Throwable t) {
                Toast.makeText(getActivity(), "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }





}



