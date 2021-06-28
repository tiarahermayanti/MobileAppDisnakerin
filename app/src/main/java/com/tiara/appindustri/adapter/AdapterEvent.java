package com.tiara.appindustri.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.activity.DetailEvent;
import com.tiara.appindustri.activity.FragmentHome;
import com.tiara.appindustri.model.DataEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tiara.appindustri.BuildConfig.IMAGE_EVENT_URL;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.MyViewHolder> {

    FragmentActivity context;
    List<DataEvent> dataEvent;
    View view;

    public AdapterEvent(FragmentActivity context, List<DataEvent> dataEvent) {
        this.context = context;
        this.dataEvent = dataEvent;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNama.setText(dataEvent.get(position).getEventNama());
        holder.txtAlamat.setText(dataEvent.get(position).getEventAlamat());
//        SimpleDateFormat date = new SimpleDateFormat("d/M/yyyy");
        holder.txtTgl.setText(dataEvent.get(position).getEventJadwal());
        holder.txtKuota.setText(dataEvent.get(position).getEventKuota());
        Picasso.get().load(IMAGE_EVENT_URL+dataEvent.get(position).getEventGambar()).into(holder.imgEvent);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailEvent.class);
                i.putExtra("id_event",dataEvent.get(position).getEventId());
                i.putExtra("nama",dataEvent.get(position).getEventNama() );
                i.putExtra("deskripsi",dataEvent.get(position).getEventDeskripsi() );
                i.putExtra("jadwal",dataEvent.get(position).getEventJadwal());
                i.putExtra("alamat",dataEvent.get(position).getEventAlamat());
                i.putExtra("kota",dataEvent.get(position).getEventKota());
                i.putExtra("kuota",dataEvent.get(position).getEventKuota());
                i.putExtra("gambar",dataEvent.get(position).getEventGambar());
                context.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataEvent == null) {
            return 0;
        } else {
            return dataEvent.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgEvent)
        ImageView imgEvent;
        @BindView(R.id.txtTgl)
        TextView txtTgl;
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtAlamat)
        TextView txtAlamat;
        @BindView(R.id.txtKuota)
        TextView txtKuota;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }


}
