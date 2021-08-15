package com.example.cobaa.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaa.R;
import com.example.cobaa.models.SoalModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.net.URISyntaxException;

public class EditSoalMapActivity extends AppCompatActivity {

    TextView name_file;
    TextView ket;

    String path = null;

    private final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private final StorageReference storageReference = firebaseStorage.getReference();
    private ProgressDialog progressDialog;

    EditText txt_soal, txt_jawaban1, txt_jawaban2, txt_jawaban3, txt_jawaban4, txt_jawaban_benar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal_random);
        name_file = findViewById(R.id.name_file);
        if (name_file.getText().toString().trim().equalsIgnoreCase("")) {
            name_file.setVisibility(View.GONE);
        } else {
            name_file.setVisibility(View.VISIBLE);
        }
        LinearLayout klik = findViewById(R.id.ln_add);
        klik.setOnClickListener(v -> showFileChooser());
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());

        ket = findViewById(R.id.ket);

        txt_soal = findViewById(R.id.txt_soal);
        txt_jawaban1 = findViewById(R.id.txt_jawaban1);
        txt_jawaban2 = findViewById(R.id.txt_jawaban2);
        txt_jawaban3 = findViewById(R.id.txt_jawaban3);
        txt_jawaban4 = findViewById(R.id.txt_jawaban4);
        txt_jawaban_benar = findViewById(R.id.txt_jawaban_benar);

        Button btn_save = findViewById(R.id.btn_add);
        btn_save.setOnClickListener(v -> validasi());

        Button btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(v -> clear());

        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> batal());
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mp3");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityIfNeeded(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    0);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.e("", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        path = getPath(this, uri);
                        Log.e("onActivityResult", "File Path: " + path);
                        String filename = path.substring(path.lastIndexOf("/") + 1);
                        String extension = path.substring(path.lastIndexOf(".") + 1);
                        if (!extension.equalsIgnoreCase("mp3")) {
                            ket.setText("File tidak didukung");
                            ket.setTextColor(getResources().getColor(R.color.colorPrimary));
                            name_file.setText("FIle Name: " + filename);
                            name_file.setVisibility(View.VISIBLE);
                        } else {
                            ket.setText("Input audio");
                            ket.setTextColor(getResources().getColor(R.color.abu));
                            name_file.setText("FIle Name: " + filename);
                            name_file.setVisibility(View.VISIBLE);
                        }

                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private void clear() {
        txt_soal.setText("");
        txt_jawaban_benar.setText("");
        txt_jawaban1.setText("");
        txt_jawaban2.setText("");
        txt_jawaban3.setText("");
        txt_jawaban4.setText("");
        path = null;
    }

    private void batal() {
        txt_soal.setText("");
        txt_jawaban_benar.setText("");
        txt_jawaban1.setText("");
        txt_jawaban2.setText("");
        txt_jawaban3.setText("");
        txt_jawaban4.setText("");
        path = null;
        finish();
    }

    private void validasi() {
        String soal = txt_soal.getText().toString().trim();
        String jawaban_benar = txt_jawaban_benar.getText().toString().trim();
        String jawaban1 = txt_jawaban1.getText().toString().trim();
        String jawaban2 = txt_jawaban2.getText().toString().trim();
        String jawaban3 = txt_jawaban3.getText().toString().trim();
        String jawaban4 = txt_jawaban4.getText().toString().trim();

        if(soal.isEmpty() && jawaban_benar.isEmpty() && jawaban1.isEmpty() && jawaban2.isEmpty() && jawaban3.isEmpty()
                    && jawaban4.isEmpty()){
            Snackbar.make(txt_soal, "Data belum lengkap", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(soal.isEmpty()){
            txt_soal.setError( "Soal tidak boleh kosong");
            return;
        }

        if(jawaban_benar.isEmpty()){
            txt_jawaban_benar.setError( "Kunci Jawaban tidak boleh kosong");
            return;
        }

        if(jawaban1.isEmpty()){
            txt_jawaban1.setError( "Pilihan Jawaban 1 tidak boleh kosong");
            return;
        }
        if(jawaban2.isEmpty()){
            txt_jawaban2.setError( "Pilihan Jawaban 2 tidak boleh kosong");
            return;
        }
        if(jawaban3.isEmpty()){
            txt_jawaban3.setError( "Pilihan Jawaban 3 tidak boleh kosong");
            return;
        }
        if(jawaban4.isEmpty()){
            txt_jawaban4.setError( "Pilihan Jawaban 4 tidak boleh kosong");
            return;
        }

        saving(soal, jawaban_benar, jawaban1, jawaban2, jawaban3, jawaban4);
    }

    private void saving( String soal, String jawaban_benar, String jawaban1, String jawaban2, String jawaban3, String jawaban4) {
        if (path != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Saving Data");
            progressDialog.show();
            Uri file = Uri.fromFile(new File(path));
            final StorageReference storageReferences = storageReference.child("lagu/" + file.getLastPathSegment());
            storageReferences.putFile(file)
                    .addOnSuccessListener(taskSnapshot -> storageReferences.getDownloadUrl().addOnSuccessListener(uri -> {
                        progressDialog.dismiss();
                        String id = FirebaseDatabase.getInstance().getReference("soal").push().getKey();
                        SoalModel upload = new SoalModel(id, jawaban_benar, "random", String.valueOf(uri),
                                "", jawaban1, jawaban2, jawaban3, jawaban4, soal);
                        FirebaseDatabase.getInstance().getReference("soal").push().setValue(upload);
                        Toast.makeText(EditSoalMapActivity.this,
                                "Saving Data successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    })).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(EditSoalMapActivity.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }).addOnProgressListener(taskSnapshot -> {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) /
                        taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
            });
        } else {
            Toast.makeText(this, "Make sure all data is correct",
                    Toast.LENGTH_SHORT).show();
        }

    }


}