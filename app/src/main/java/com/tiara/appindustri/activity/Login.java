package com.tiara.appindustri.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.model.DataLoginItem;
import com.tiara.appindustri.model.ResponseLogin;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @BindView(R.id.editTextNoHp)
    EditText editTextNoHp;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.cirLoginButton)
    Button cirLoginButton;
    @BindView(R.id.signup)
    TextView signup;

    String nohp, pass;

   SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nohp = editTextNoHp.getText().toString();
                pass = editTextPassword.getText().toString();

                if(TextUtils.isEmpty(nohp) || TextUtils.isEmpty(pass)){
                    Toast.makeText(Login.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else{
                    cekLogin();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ListSearch.class);
                startActivity(intent);
            }
        });


        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(Login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void cekLogin() {
       ConfigRetrofit.getInstance().cek(nohp,pass).enqueue(new Callback<ResponseLogin>() {
           @Override
           public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
               String msg = response.body().getPesan();
               int status = response.body().getStatus();

               if(status == 1){
                   List<DataLoginItem> data = response.body().getDataLogin();
                   String nama = data.get(0).getPenggunaNama();
                   String email = data.get(0).getPenggunaEmail();
                   String idIkm = data.get(0).getIDIKM();
                   String idPengguna = data.get(0).getPenggunaId();
                   String foto = data.get(0).getPenggunaPhoto();

                   sharedPrefManager.setSP_SUDAH_LOGIN(true);
                   sharedPrefManager.setSP_IDIKM(idIkm);
                   sharedPrefManager.setSP_IDPENGGUNA(idPengguna);
                   sharedPrefManager.setSPNama(nama);
                   sharedPrefManager.setSPEmail(email);
                   sharedPrefManager.setSPFoto(foto);
                   Intent i = new Intent(Login.this, MainActivity.class);
                   startActivity(i);
                   finish();
               } else {
                   Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<ResponseLogin> call, Throwable t) {
               Toast.makeText(Login.this, "Tidak Ada jaringan", Toast.LENGTH_SHORT).show();
           }
       });
    }
}
