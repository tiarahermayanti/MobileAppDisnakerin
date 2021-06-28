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
import com.tiara.appindustri.activity.UpdateDeletLegal;
import com.tiara.appindustri.model.DataLegalitas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLegalitas extends RecyclerView.Adapter<AdapterLegalitas.MyHolder> {

    Context context;
    List<DataLegalitas> data;
    View view;

    public AdapterLegalitas (Context context, List<DataLegalitas> data){
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
        holder.txtNamaBB.setText(data.get(position).getNAMAIZIN());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateDeletLegal.class);
                i.putExtra("id", data.get(position).getID());
                i.putExtra("id_ikm", data.get(position).getIDIKM());
                i.putExtra("nama", data.get(position).getNAMAIZIN());
                i.putExtra("instansi", data.get(position).getINSTANSI());
                i.putExtra("no_izin", data.get(position).getNOIZIN());
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
