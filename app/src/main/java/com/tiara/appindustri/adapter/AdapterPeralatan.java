package com.tiara.appindustri.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiara.appindustri.R;
import com.tiara.appindustri.activity.UpdateDeletePeralatan;
import com.tiara.appindustri.model.DataPeralatan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPeralatan extends RecyclerView.Adapter<AdapterPeralatan.MyHolder> {

    Context context;
    List<DataPeralatan> data;
    View view;

    public AdapterPeralatan (Context context, List<DataPeralatan> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_energi, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txtNamaBB.setText(data.get(position).getNAMAMESIN());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateDeletePeralatan.class);
                i.putExtra("nama_mesin", data.get(position).getNAMAMESIN());
                i.putExtra("id", data.get(position).getID());
                i.putExtra("id_ikm", data.get(position).getIDIKM());
                i.putExtra("merek", data.get(position).getMEREK());
                i.putExtra("tahun", data.get(position).getTAHUN());
                i.putExtra("negara", data.get(position).getNEGARAASAL());
                i.putExtra("spesifikasi", data.get(position).getSPESIFIKASI());
                i.putExtra("jumlah", data.get(position).getJUMLAH());
                i.putExtra("satuan", data.get(position).getSATUAN());
                i.putExtra("harga", data.get(position).getHARGA());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNamaBB)
        TextView txtNamaBB;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }
}
