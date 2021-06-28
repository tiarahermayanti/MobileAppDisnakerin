package com.tiara.appindustri.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tiara.appindustri.R;
import com.tiara.appindustri.adapter.AdapterIndustri;
import com.tiara.appindustri.model.DataIndustri;
import com.tiara.appindustri.model.ResponseGetDetailIkm;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSearch extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;


    AdapterIndustri adapterIndustri;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_search);
        ButterKnife.bind(this);


        ConfigRetrofit.getInstance().getDetailIkm().enqueue(new Callback<ResponseGetDetailIkm>() {
            @Override
            public void onResponse(Call<ResponseGetDetailIkm> call, Response<ResponseGetDetailIkm> response) {
                if (response.isSuccessful()) {
                    String msg = response.body().getPesan();
                    int status = response.body().getStatus();
                    if (status == 1) {
                        //onSuccess((ArrayList<DataIndustri>) response.body().getData());
                        List<DataIndustri> dataind = response.body().getData();
                        adapterIndustri = new AdapterIndustri(ListSearch.this, dataind);
                        recycler.setAdapter(adapterIndustri);
                        recycler.setLayoutManager(new LinearLayoutManager(ListSearch.this));
                        recycler.addItemDecoration(new DividerItemDecoration(ListSearch.this, DividerItemDecoration.VERTICAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetDetailIkm> call, Throwable t) {
                Toast.makeText(ListSearch.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListSearch.this, Register.class);
                startActivity(i);
            }
        });

        //SearchIndustri();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        filterFunction(menu);
        return true;
    }

    private void filterFunction(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.showSearchTitle);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adapterIndustri.getFilter().filter(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterIndustri.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.showSearchTitle) {


        }
        return super.onOptionsItemSelected(item);
    }


}
