package com.tiara.appindustri.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiara.appindustri.R;
import com.tiara.appindustri.activity.UpdateIndustri;
import com.tiara.appindustri.model.DataIndustri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterIndustri extends RecyclerView.Adapter<AdapterIndustri.MyViewHolder> implements Filterable {

    Context context;
    List<DataIndustri> dataIndustri;
    List<DataIndustri> filterData;
    View view;

    public  AdapterIndustri(Context context, List<DataIndustri> dataIndustri){
        this.context = context;
        this.dataIndustri= dataIndustri;
        filterData = new ArrayList<>(dataIndustri);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_industri, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNama.setText(dataIndustri.get(position).getNAMAPERUSAHAAN());
        holder.txtAlamat.setText(dataIndustri.get(position).getALAMAT());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateIndustri.class);
                i.putExtra("id_ikm", dataIndustri.get(position).getIDIKM());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(dataIndustri == null){
            return 0;
        } else {
            return dataIndustri.size();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtAlamat)
        TextView txtAlamat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return industriFilter;
    }

    private Filter industriFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataIndustri> filter = new ArrayList<>();

            if(constraint==null || constraint.length()==0){
//                Collections.sort(filterData, new Comparator<DataIndustri>() {
//                    @Override
//                    public int compare(DataIndustri o1, DataIndustri o2) {
//                        return o1.getNAMAPERUSAHAAN().toLowerCase().compareTo(o2.getNAMAPERUSAHAAN().toLowerCase());
//                    }
//                });

                Collections.sort(filterData, (DataIndustri o1, DataIndustri o2) -> o1.getNAMAPERUSAHAAN().toLowerCase().compareTo(o2.getNAMAPERUSAHAAN().toLowerCase()));
                filter.addAll(filterData);

            } else {
                String filteredPattern = constraint.toString().toLowerCase().trim();
                for(DataIndustri data : filterData){
                    if(data.getNAMAPERUSAHAAN().toLowerCase().contains(filteredPattern)){
                        filter.add(data);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filter;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataIndustri.clear();
            dataIndustri.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };


}