package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobaa.R;
import com.example.cobaa.models.BannerModel;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

public class AddBannerActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    String foto_dari;

    private Uri resultUri;
    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;
    private final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private final StorageReference storageReference = firebaseStorage.getReference();
    Bitmap bitmap;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_banner);
        checkAndRequestPermissions();
        Button choose_photo = findViewById(R.id.bt_foto);
        choose_photo.setOnClickListener(v -> getImage());

        EditText name_banner = findViewById(R.id.name_banner);
        imageView = findViewById(R.id.image);

        Button save = findViewById(R.id.save);
        save.setOnClickListener(v -> cekInputan(name_banner));

        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());



    }

    private void cekInputan(EditText name_banner) {
        if (name_banner.getText().toString().trim().isEmpty()){
            name_banner.setError("Nama Banner Tidak boleh kosong");
            name_banner.requestFocus();
            return;
        }
        if (foto_dari.equals("gallery")){
            saving(name_banner);
        }else{
            savingFromCamera(name_banner);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                }
                break;

            case REQUEST_CODE_GALLERY:
                if (resultCode == RESULT_OK) {
                    resultUri = data.getData();
                    imageView.setImageURI(resultUri);
                }
                break;
        }

    }

    private void getImage(){
        //Method ini digunakan untuk mengambil gambar dari Kamera
        CharSequence[] menu = {"Kamera", "Galeri"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Pilih Gambar")
                .setItems(menu, (dialog1, which) -> {
                    switch (which){
                        case 0:
                            //Mengambil gambar dari Kemara ponsel
                            Intent imageIntentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityIfNeeded(imageIntentCamera, REQUEST_CODE_CAMERA);
                            foto_dari = "camera";
                            break;

                        case 1:
                            //Mengambil gambar dari galeri
                            Intent imageIntentGallery = new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityIfNeeded(imageIntentGallery, REQUEST_CODE_GALLERY);
                            foto_dari = "gallery";

                            break;
                    }
                });
        dialog.create();
        dialog.show();
    }

    private String getFileExtension(Uri filePath) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(filePath));
    }


    private void savingFromCamera(EditText name_banner) {
        if (resultUri != null || bitmap != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Menyimpan Data");
            progressDialog.show();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] b = stream.toByteArray();
            final StorageReference storageReferences = storageReference.child("banner/"+ name_banner.getText().toString().trim());
            storageReferences.putBytes(b)
                    .addOnSuccessListener(taskSnapshot -> storageReferences.getDownloadUrl().addOnSuccessListener(uri -> {
                        progressDialog.dismiss();

                        String id = FirebaseDatabase.getInstance().getReference("banner").push().getKey();
                        BannerModel upload = new BannerModel(id,
                                String.valueOf(uri), name_banner.getText().toString().trim());
                        FirebaseDatabase.getInstance().getReference("banner").child(id).setValue(upload);
                        Toast.makeText(AddBannerActivity.this,
                                "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        finish();
                    })).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(AddBannerActivity.this, "Gagal Menyimpan Data",
                                Toast.LENGTH_SHORT).show();
                    }).addOnProgressListener(taskSnapshot -> {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) /
                        taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Proses " + ((int) progress) + "%...");
            });
        } else {
            Toast.makeText(this,"Pastikan semua data sudah benar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void saving(EditText name_banner){
        if (resultUri != null || bitmap != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Menyimpan Data");
            progressDialog.show();
            final StorageReference storageReferences = storageReference.child("banner/"+ name_banner.getText().toString().trim()+ "." +getFileExtension(resultUri));
            storageReferences.putFile(resultUri)
                    .addOnSuccessListener(taskSnapshot -> storageReferences.getDownloadUrl().addOnSuccessListener(uri -> {
                        progressDialog.dismiss();
                        String id = FirebaseDatabase.getInstance().getReference("banner").push().getKey();
                        BannerModel upload = new BannerModel(id,
                                String.valueOf(uri), name_banner.getText().toString().trim());
                        FirebaseDatabase.getInstance().getReference("banner").push().setValue(upload);
                        Toast.makeText(AddBannerActivity.this,
                                "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        finish();
                    })).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(AddBannerActivity.this, "Gagal Menyimpan Data",
                                Toast.LENGTH_SHORT).show();
                    }).addOnProgressListener(taskSnapshot -> {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) /
                                taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Proses " + ((int) progress) + "%...");
                    });
        } else {
            Toast.makeText(this,"Pastikan semua data sudah benar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAndRequestPermissions() {
        int cam = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (cam != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray
                    (new String[0]),1);
        }

    }

}