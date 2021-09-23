package com.example.cobaa.activities.admin;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cobaa.R;
import com.example.cobaa.adapter.AdapterLagu;
import com.example.cobaa.adapter.DaerahAdapter;
import com.example.cobaa.adapter.TipeAdapter;
import com.example.cobaa.models.LaguModel;
import com.example.cobaa.models.PulauModel;
import com.example.cobaa.models.SoalModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TambahSoalMapActivity extends AppCompatActivity {
    private final String TAG= "TambahSoalMapActivity";

    TextView ket;
    String idLagu = "";
    String tipe = "";
    String daerah = "";

    private ProgressDialog progressDialog;

    EditText txt_soal, txt_jawaban1, txt_jawaban2, txt_jawaban3, txt_jawaban4, txt_jawaban_benar, txt_daerah, txt_lagu, txt_tipe;
    ImageView _daerah, _tipe, _lagu;

    LinearLayout frame_map, frame_lagu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal_map);
        checkAndRequestPermissions();

        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());

        ket = findViewById(R.id.ket);

        txt_soal = findViewById(R.id.txt_soal);
        txt_jawaban1 = findViewById(R.id.txt_jawaban1);
        txt_jawaban2 = findViewById(R.id.txt_jawaban2);
        txt_jawaban3 = findViewById(R.id.txt_jawaban3);
        txt_jawaban4 = findViewById(R.id.txt_jawaban4);
        txt_jawaban_benar = findViewById(R.id.txt_jawaban_benar);
        txt_daerah = findViewById(R.id.txt_daerah);
        txt_tipe = findViewById(R.id.txt_tipe);
        txt_lagu = findViewById(R.id.txt_lagu);
        frame_map = findViewById(R.id.frame_map);
        frame_lagu = findViewById(R.id.frame_lagu);
        _tipe = findViewById(R.id._tipe);
        _lagu = findViewById(R.id._lagu);
        _daerah = findViewById(R.id._daerah);
        _daerah.setOnClickListener(v -> popupdaerah(txt_daerah));
        _tipe.setOnClickListener(v -> popuptipe(txt_tipe));
        _lagu.setOnClickListener(v -> popLagu(txt_lagu));


        Button btn_save = findViewById(R.id.btn_add);
        btn_save.setOnClickListener(v -> validasi());

        Button btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(v -> clear());

        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> batal());
    }


    private final List<String> listTipe = new ArrayList<>();
    TipeAdapter adapter_tipe;

    private void popuptipe (final EditText editText) {
        AlertDialog.Builder alert;
        AlertDialog ad;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View alertLayout = inflater.inflate(R.layout.dialog_daerah, null);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Pilih Tipe Soal ");
        alert.setIcon(R.drawable.ic_logo);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        ListView mListViewnya = alertLayout.findViewById(R.id.listItem);

        listTipe.clear();

        mListViewnya.setClickable(true);

        mListViewnya.setOnItemClickListener((adapterView, view, i, l) -> {
            ad.dismiss();
            editText.setError(null);
            TextView textView = view.findViewById(R.id.tv_daerah);
            tipe = textView.getText().toString().trim();
            editText.setText(textView.getText());
            if (editText.getText().toString().trim().equalsIgnoreCase("Tidak Acak")){
                frame_map.setVisibility(View.VISIBLE);
            }else {
                frame_map.setVisibility(View.GONE);
                daerah = "";
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang menyiapkan..");
        progressDialog.show();
        progressDialog.dismiss();

        listTipe.add("Tidak Acak");
        listTipe.add("Acak");
        adapter_tipe = new TipeAdapter(this, listTipe);

        mListViewnya.setAdapter(adapter_tipe);

        adapter_tipe.setList(listTipe);

    }

    private final List<PulauModel> listDaerah = new ArrayList<>();
    private ListView mListView;
    DaerahAdapter adapter_daerah;

    private void popupdaerah (final EditText editText) {
        AlertDialog.Builder alert;
        AlertDialog ad;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View alertLayout = inflater.inflate(R.layout.dialog_daerah, null);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Pilih Daerah ");
        alert.setIcon(R.drawable.ic_logo);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        mListView = alertLayout.findViewById(R.id.listItem);

        listDaerah.clear();
        adapter_daerah = new DaerahAdapter(this, listDaerah);

        mListView.setClickable(true);

        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Object o = mListView.getItemAtPosition(i);
            PulauModel cn = (PulauModel) o;
            ad.dismiss();
            editText.setError(null);
            editText.setText(cn.getName_pulau());
            daerah = cn.getName_pulau();
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang menyiapkan..");
        progressDialog.show();

        getDaerah();

    }

    private void getDaerah() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("master pulau");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDaerah.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    PulauModel p = dataSnapshot1.getValue(PulauModel.class);
                    listDaerah.add(p);
                }
                mListView.setAdapter(adapter_daerah);
                adapter_daerah.setList(listDaerah);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "databaseError : " + databaseError.getMessage());

                Toast.makeText(TambahSoalMapActivity.this, "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final List<LaguModel> listLagu = new ArrayList<>();
    private ListView mListViewLagu;
    AdapterLagu adapter_lagu;

    private void popLagu (final EditText editText) {
        AlertDialog.Builder alert;
        AlertDialog ad;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View alertLayout = inflater.inflate(R.layout.dialog_daerah, null);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Pilih Lagu ");
        alert.setIcon(R.drawable.ic_logo);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        mListViewLagu = alertLayout.findViewById(R.id.listItem);

        listLagu.clear();
        adapter_lagu = new AdapterLagu(this, listLagu);

        mListViewLagu.setClickable(true);

        mListViewLagu.setOnItemClickListener((adapterView, view, i, l) -> {
            Object o = mListViewLagu.getItemAtPosition(i);
            LaguModel cn = (LaguModel) o;
            ad.dismiss();
            editText.setError(null);
            editText.setText(cn.getNama());
            idLagu = cn.getId();
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang menyiapkan..");
        progressDialog.show();

        getLagu();

    }


    private void getLagu() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Lagu");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listLagu.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    LaguModel p = dataSnapshot1.getValue(LaguModel.class);
                    listLagu.add(p);
                }
                mListViewLagu.setAdapter(adapter_lagu);
                adapter_lagu.setList(listLagu);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "databaseError : " + databaseError.getMessage());

                Toast.makeText(TambahSoalMapActivity.this, "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void clear() {
        txt_soal.setText("");
        txt_jawaban_benar.setText("");
        txt_jawaban1.setText("");
        txt_jawaban2.setText("");
        txt_jawaban3.setText("");
        txt_jawaban4.setText("");
    }

    private void batal() {
        txt_soal.setText("");
        txt_jawaban_benar.setText("");
        txt_jawaban1.setText("");
        txt_jawaban2.setText("");
        txt_jawaban3.setText("");
        txt_jawaban4.setText("");
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

        if (idLagu.equals("")){
            txt_lagu.setError( "Lagu boleh kosong");
            return;
        }

        if (tipe.equals("")){
            txt_lagu.setError( "Tipe Lagu boleh kosong");
            return;
        }

        saving(soal, jawaban_benar, jawaban1, jawaban2, jawaban3, jawaban4);
    }


    private void saving( String soal, String jawaban_benar, String jawaban1, String jawaban2, String jawaban3, String jawaban4) {
        if (txt_tipe.getText().toString().trim().equalsIgnoreCase("Tidak Acak") &&
                txt_daerah.getText().toString().trim().equalsIgnoreCase("")){
            txt_daerah.setError( "Daerah tidak boleh kosong");
        }else {
            String id = FirebaseDatabase.getInstance().getReference("soal").push().getKey();
            SoalModel upload = new SoalModel(id, jawaban_benar, tipe, idLagu,
                    daerah, jawaban1, jawaban2, jawaban3, jawaban4, soal);
            FirebaseDatabase.getInstance().getReference("soal").child(id).setValue(upload);

            Toast.makeText(TambahSoalMapActivity.this,
                    "Data berhasil disimpan.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void checkAndRequestPermissions() {
        int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (write != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray
                    (new String[0]),1);
        }

    }


}