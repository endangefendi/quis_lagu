package com.example.cobaa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cobaa.models.QuestionMap;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity {
    @BindView(R.id.carouselView)
    CarouselView carouselView;
    @BindView(R.id.btnSumatera)
    LinearLayout btnSumatera;
    @BindView(R.id.btnJawa)
    LinearLayout btnJawa;
    @BindView(R.id.btnKalimantan)
    LinearLayout btnKalimantan;
    @BindView(R.id.btnSulawesi)
    LinearLayout btnSulawesi;
    @BindView(R.id.btnMalukuIrian)
    LinearLayout btnMalukuIrian;

    int[] sampleImages = {R.drawable.ic_slide1, R.drawable.ic_slide2, R.drawable.ic_slide3};
    public static final int PERMISSION_MEDIA = 1;
    String daerah = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setup();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_MEDIA && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startgame();
        } else {
            Toast.makeText(MapActivity.this, "App cannot run without permission", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.btnSumatera, R.id.btnJawa, R.id.btnKalimantan, R.id.btnSulawesi, R.id.btnMalukuIrian})
    void onClick(View v) {
        if (v == btnSumatera) {
            daerah = QuestionMap.SUMATERA;
            startgame();
        }
        if (v == btnJawa) {
            daerah = QuestionMap.JAWA;
            startgame();
        }
        if (v == btnKalimantan) {
            daerah = QuestionMap.KALIMANTAN;
            startgame();
        }
        if (v == btnSulawesi) {
            daerah = QuestionMap.SULAWESI;
            startgame();
        }
        if (v == btnMalukuIrian) {
            daerah = QuestionMap.MALUKU_IRIANJAYA;
            startgame();
        }
    }

    void setup() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    private void startgame() {
        if (isStoragePermissionGrantedMedia()) {
            Intent intent = new Intent(this, TestActivity.class);
            intent.putExtra("Daerah", daerah);
            startActivity(intent);
        }
    }

    public boolean isStoragePermissionGrantedMedia() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MapActivity.this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission", "Is granted");
                return true;
            } else {
                Log.e("Permission", "Is revoked");
                ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_MEDIA);
                return false;
            }
        } else {
            Log.e("Permission", "Is granted");
            return false;
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}