package com.tiara.appindustri.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tiara.appindustri.R;
import com.tiara.appindustri.SharedPrefManager;
import com.tiara.appindustri.model.DataPengguna;
import com.tiara.appindustri.model.ResponseGetPengguna;
import com.tiara.appindustri.model.ResponsePost;
import com.tiara.appindustri.restapi.ConfigRetrofit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tiara.appindustri.BuildConfig.IMAGE_PROFILE_URL;

public class UpdateAkun extends AppCompatActivity {

    @BindView(R.id.ic_akun)
    CircularImageView icAkun;
    @BindView(R.id.Foto)
    CircularImageView imgFoto;
    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.spinJenkel)
    Spinner spinJenkel;
    @BindView(R.id.edtNohp)
    EditText edtNohp;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtMoto)
    EditText edtMoto;
    @BindView(R.id.edtTentang)
    EditText edtTentang;
    @BindView(R.id.btnSimpanAkun)
    Button btnRegister;
    @BindView(R.id.btnGantiFoto)
    Button btnGantiFoto;
    @BindView(R.id.fragmentContainer)
    RelativeLayout fragmentContainer;

    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 124;
    private Uri filePath;
    private String path;

    SharedPrefManager sharedPrefManager;
    String idPengguna, jekel, foto;
    String pjekel, pnama, pnohp, pemail, pmoto, ptentang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun);
        ButterKnife.bind(this);

        sharedPrefManager = new SharedPrefManager(this);
        idPengguna = sharedPrefManager.getSP_IDPENGGUNA();

        getData();

        spinJenkel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pjekel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        btnGantiFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar();
            }
        });

    }

    private void pilihGambar() {
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            // do your stuff..
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityIfNeeded(Intent.createChooser(intent, "Pilih Foto"), PICK_IMAGE_REQUEST);
    }

    private void simpanData() {

        pnama = edtNama.getText().toString();
        pemail = edtEmail.getText().toString();
        pnohp = edtNohp.getText().toString();
        pmoto = edtMoto.getText().toString();
        ptentang = edtTentang.getText().toString();

        ConfigRetrofit.getInstance().updatePengguna(idPengguna, pnama, pjekel, pnohp, pemail, pmoto, ptentang).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                int s = response.body().getStatus();
                if (s == 1) {
                    sharedPrefManager.setSPNama(pnama);
                    sharedPrefManager.setSPEmail(pemail);
                    FragmentAkun fragtry = new FragmentAkun();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, fragtry)
                            .commit();
//                    startActivity(new Intent(UpdateAkun.this, FragmentAkun.class));
                    finish();
                } else {
                    Toast.makeText(UpdateAkun.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                Toast.makeText(UpdateAkun.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        ConfigRetrofit.getInstance().getPengguna(idPengguna).enqueue(new Callback<ResponseGetPengguna>() {
            @Override
            public void onResponse(Call<ResponseGetPengguna> call, Response<ResponseGetPengguna> response) {
                int s = response.body().getStatus();
                List<DataPengguna> data = response.body().getData();
                if (s == 1) {
                    for (int i = 0; i < data.size(); i++) {
                        edtNama.setText(data.get(i).getPenggunaNama());
                        edtEmail.setText(data.get(i).getPenggunaEmail());
                        edtNohp.setText(data.get(i).getPenggunaNohp());
                        edtMoto.setText(data.get(i).getPenggunaMoto());
                        edtTentang.setText(data.get(i).getPenggunaTentang());

                        jekel = data.get(i).getPenggunaJenkel();
                        foto = data.get(i).getPenggunaPhoto();
                        sharedPrefManager.setSPFoto(foto);

                        if (foto == null) {
                            imgFoto.setVisibility(View.GONE);
                            icAkun.setVisibility(View.VISIBLE);
                        } else {
                            icAkun.setVisibility(View.GONE);
                            Picasso.get().load(IMAGE_PROFILE_URL + foto).into(imgFoto);
                        }

                    }

                    List<String> listJekel = new ArrayList<String>();
                    listJekel.add("P");
                    listJekel.add("L");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateAkun.this,
                            android.R.layout.simple_spinner_item, listJekel);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinJenkel.setAdapter(adapter);
                 if(jekel == null){
                    spinJenkel.setSelection(adapter.getPosition("L"));
                    pjekel = jekel;
                } else {
                     if (jekel.equals("P")) {
                         spinJenkel.setSelection(adapter.getPosition("P"));
                         pjekel = jekel;
                     } else if (jekel.equals("L")) {
                         spinJenkel.setSelection(adapter.getPosition("L"));
                         pjekel = jekel;
                     } else if(jekel == null){
                         spinJenkel.setSelection(adapter.getPosition("L"));
                         pjekel = jekel;
                     }
                 }


                } else {
                    Toast.makeText(UpdateAkun.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateAkun.this, FragmentAkun.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPengguna> call, Throwable t) {
                Toast.makeText(UpdateAkun.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(filePath, imageprojection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                path = cursor.getString(indexImage);

                dialogImage();
            }
        }
    }

    ImageView imgDialog;

    private void dialogImage() {
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.dialog_image, null);

        imgDialog = alertlayout.findViewById(R.id.imgDialog);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            imgDialog.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(this);
        alert.setView(alertlayout);
        alert.setCancelable(false);
        alert.setTitle("Ubah Foto");
        alert.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String path = getPath(filePath);
                File imagefile = new File(path);
                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
                String imagePost = "uploadImage";
                MultipartBody.Part partImage = MultipartBody.Part.createFormData(imagePost, imagefile.getName(), reqBody);
                RequestBody rbidPengguna = RequestBody.create(MediaType.parse("text/plain"), idPengguna);

                ConfigRetrofit.getInstance().updateFotoPengguna(rbidPengguna, partImage).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        int s = response.body().getStatus();
                        if (s == 1) {
                            Toast.makeText(UpdateAkun.this, "Foto Profil Tersimpan", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());

                        } else {
                            Toast.makeText(UpdateAkun.this, "Gagal Simpan Foto Profil", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        Toast.makeText(UpdateAkun.this, "Tidak Ada Jaringan", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    public String getPath(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "GET_ACCOUNTS Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

}
