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
import com.tiara.appindustri.activity.UpdateDeleteBahanBaku;
import com.tiara.appindustri.activity.UpdateDeletePemasaran;
import com.tiara.appindustri.model.DataBahanBaku;
import com.tiara.appindustri.model.DataPemasaran;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPemasaran extends RecyclerView.Adapter<AdapterPemasaran.MyHolder> {

    Context context;
    List<DataPemasaran> list;
    View view;

    public AdapterPemasaran(Context context, List<DataPemasaran> list) {
        this.context = context;
        this.list = list;
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
        holder.txtNamaBB.setVisibility(View.GONE);
        holder.txtTahun.setText(list.get(position).getTAHUN());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context, UpdateDeletePemasaran.class);
                i.putExtra("id", list.get(position).getID());
                i.putExtra("idIKM", list.get(position).getIDIKM());
                i.putExtra("tkp", list.get(position).getTENAGAKERJAP());
                i.putExtra("tkw", list.get(position).getTENAGAKERJAW());
                i.putExtra("investasi", list.get(position).getNILAIINVESTASI());
                i.putExtra("lokal", list.get(position).getPEMASARANLOKAL());
                i.putExtra("luar", list.get(position).getPEMASARANLUARDAERAH());
                i.putExtra("ekspor", list.get(position).getEKSPOR());
                i.putExtra("tahun", list.get(position).getTAHUN());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
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
