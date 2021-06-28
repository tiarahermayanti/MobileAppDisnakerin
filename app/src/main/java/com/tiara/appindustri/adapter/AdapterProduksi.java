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
import com.tiara.appindustri.activity.UpdateDeleteProduksi;
import com.tiara.appindustri.model.DataProduksi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterProduksi extends RecyclerView.Adapter<AdapterProduksi.MyHolder> {

    Context context;
    List<DataProduksi> data;
    View view;

    public AdapterProduksi (Context context, List<DataProduksi> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_bahan_baku, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txtNamaBB.setText(data.get(position).getNAMAPRODUK());
        holder.txtTahun.setText(data.get(position).getTAHUN());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateDeleteProduksi.class);
                i.putExtra("id", data.get(position).getID());
                i.putExtra("id_ikm", data.get(position).getIDIKM());
                i.putExtra("nama_produk", data.get(position).getNAMAPRODUK());
                i.putExtra("kapasitas", data.get(position).getKAPASITASPRODUKSI());
                i.putExtra("jumlah", data.get(position).getJUMLAHPRODUKSI());
                i.putExtra("satuan", data.get(position).getSATUAN());
                i.putExtra("nilai_produksi", data.get(position).getNILAIPRODUKSI());
                i.putExtra("nilai_penjualan", data.get(position).getNILAIPENJUALAN());
                i.putExtra("tahun", data.get(position).getTAHUN());
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
        @BindView(R.id.txtTahun)
        TextView txtTahun;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }
}
