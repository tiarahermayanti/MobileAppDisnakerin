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
import com.tiara.appindustri.model.DataBahanBaku;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBahanBaku extends RecyclerView.Adapter<AdapterBahanBaku.MyHolder> {

    Context context;
    List<DataBahanBaku> list;
    View view;

    public AdapterBahanBaku(Context context, List<DataBahanBaku> list) {
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
        holder.txtNamaBB.setText(list.get(position).getNAMABAHANBAKU());
        holder.txtTahun.setText(list.get(position).getTAHUN());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context, UpdateDeleteBahanBaku.class);
                i.putExtra("idBB", list.get(position).getID());
                i.putExtra("idIKM", list.get(position).getIDIKM());
                i.putExtra("namaBB", list.get(position).getNAMABAHANBAKU());
                i.putExtra("jenisBB", list.get(position).getJENISBAHANBAKU());
                i.putExtra("sumberBB", list.get(position).getSUMBER());
                i.putExtra("jmlhBB", list.get(position).getJUMLAHKEBUTUHAN());
                i.putExtra("nilaiBB", list.get(position).getNILAIBAHANBAKU());
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
