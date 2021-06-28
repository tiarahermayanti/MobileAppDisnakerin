package com.tiara.appindustri.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.activity.DetailJoinEvent;
import com.tiara.appindustri.model.DataJoinEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tiara.appindustri.BuildConfig.IMAGE_EVENT_URL;

public class AdapterJoinEvent extends RecyclerView.Adapter<AdapterJoinEvent.MyHolder> {

    FragmentActivity context;
    List<DataJoinEvent> dataJoinEvent;
    View view;

    public AdapterJoinEvent(FragmentActivity context, List<DataJoinEvent> dataJoinEvent) {
        this.context = context;
        this.dataJoinEvent = dataJoinEvent;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_join_event, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txtNama.setText(dataJoinEvent.get(position).getEventNama());
        holder.txtAlamat.setText(dataJoinEvent.get(position).getEventAlamat());
        holder.txtTgl.setText(dataJoinEvent.get(position).getEventJadwal());
        Picasso.get().load(IMAGE_EVENT_URL+dataJoinEvent.get(position).getEventGambar()).into(holder.imgEvent);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailJoinEvent.class);
                i.putExtra("nama",dataJoinEvent.get(position).getEventNama() );
                i.putExtra("deskripsi",dataJoinEvent.get(position).getEventDeskripsi() );
                i.putExtra("jadwal",dataJoinEvent.get(position).getEventJadwal());
                i.putExtra("alamat",dataJoinEvent.get(position).getEventAlamat());
                i.putExtra("gambar",dataJoinEvent.get(position).getEventGambar());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (dataJoinEvent == null) {
            return 0;

        } else {
            return dataJoinEvent.size();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgEvent)
        ImageView imgEvent;
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtTgl)
        TextView txtTgl;
        @BindView(R.id.txtAlamat)
        TextView txtAlamat;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }


}
