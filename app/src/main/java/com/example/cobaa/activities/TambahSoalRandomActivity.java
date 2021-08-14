package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobaa.R;

import java.net.URISyntaxException;

public class TambahSoalRandomActivity extends AppCompatActivity {

    TextView name_file;
    TextView ket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal_random);
        name_file = findViewById(R.id.name_file);
        if (name_file.getText().toString().trim().equalsIgnoreCase("")){
            name_file.setVisibility(View.GONE);
        }else {
            name_file.setVisibility(View.VISIBLE);
        }
        LinearLayout klik = findViewById(R.id.ln_add);
        klik.setOnClickListener(v -> showFileChooser());
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());

        ket = findViewById(R.id.ket);


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
                    String path = null;
                    try {
                        path = getPath(this, uri);
                        Log.e("onActivityResult", "File Path: " + path);
                        String filename=path.substring(path.lastIndexOf("/")+1);
                        String extension = path.substring(path.lastIndexOf(".")+1);
                        if (!extension.equalsIgnoreCase("mp3")){
                            ket.setText("File tidak didukung");
                            ket.setTextColor(getResources().getColor(R.color.colorPrimary));
                            name_file.setText("FIle Name: "+filename);
                            name_file.setVisibility(View.VISIBLE);
                        }else {
                            ket.setText("Input audio");
                            ket.setTextColor(getResources().getColor(R.color.abu));
                            name_file.setText("FIle Name: "+filename);
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
            String[] projection = { "_data" };
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
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

}