package com.example.cobaa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobaa.R;
import com.example.cobaa.utils.DialogUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import static com.example.cobaa.constans.CekConnections.isConnectingToInternet;

public class LoginAdminActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final String TAG = "LoginAdminActivity";

    private EditText edUsername, edPassword;
    private boolean isVisiblePassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        setTitle("Login Admin");
        mAuth = FirebaseAuth.getInstance();

        edUsername = findViewById(R.id.et_username);
        edPassword = findViewById(R.id.et_password);
        ImageView ivVisiblePassword = findViewById(R.id.iv_visible_password);

        ivVisiblePassword.setOnClickListener(view -> {
            ivVisiblePassword.setImageResource(isVisiblePassword ? R.drawable.ic_visibility_on : R.drawable.ic_visibility_off);
            edPassword.setTransformationMethod(isVisiblePassword ? null : new PasswordTransformationMethod());
            isVisiblePassword = !isVisiblePassword;
            edPassword.setSelection(edPassword.getText().toString().length());
        });

        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(view ->
//                login(edUsername.getText().toString().trim(), edPassword.getText().toString().trim())
                validateLogin()
        );
    }

    private void validateLogin() {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();

//        if(username.isEmpty() && password.isEmpty()){
//            Snackbar.make(edUsername, "Username and Password is empty", Snackbar.LENGTH_SHORT).show();
//        } else

        if(username.isEmpty()){
            Snackbar.make(edUsername, "Username is empty", Snackbar.LENGTH_SHORT).show();
        }else

//        if(password.isEmpty()){
//            Snackbar.make(edUsername, "Password is empty", Snackbar.LENGTH_SHORT).show();
//        }else

        login(username, password);
    }

    //cek koneksi
    private void startProcess() {
        if (!isConnectingToInternet(this)) {
            popUpConection();
        }else {
            login(edUsername.getText().toString().trim(), edPassword.getText().toString().trim());
        }
    }

    private void login(String Username, String Password) {
        mAuth.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                FirebaseUser user = mAuth.getCurrentUser();
                assert user != null;
                Log.e(TAG, "signInWithEmailAndPassword: "+user.getUid());
                Intent intent = new Intent(this, DashboarAdminActivity.class);
                startActivity(intent);
            } else {
                // If sign in fails, display a message to the user.
                Log.e(TAG, "signInWithEmailAndPassword: failure", task.getException());
            }
        }).addOnFailureListener(e -> {
            if( e instanceof FirebaseAuthInvalidUserException){
                Toast.makeText(LoginAdminActivity.this, "This User Not Found , Create A New Account", Toast.LENGTH_SHORT).show();
            }
            if( e instanceof FirebaseAuthInvalidCredentialsException){
                Toast.makeText(LoginAdminActivity.this, "The Password Is Invalid, Please Try Valid Password", Toast.LENGTH_SHORT).show();
            }
            if(e instanceof FirebaseNetworkException){
                Toast.makeText(LoginAdminActivity.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //pesan info tidak ada koneksi
    private void popUpConection() {
        Dialog dialog = new DialogUtils(LoginAdminActivity.this)
                .buildDialogWarning(
                        R.string.title_no_internet,
                        R.string.msg_no_internet,
                        R.string.TRY_AGAIN,
                        R.string.CLOSE,
                        R.drawable.ic_no_wifi,
                        new DialogUtils.CallbackDialog() {
                            @Override
                            public void onPositiveClick(Dialog dialog) {
                                dialog.dismiss();
                                retryOpenApplication();
                            }

                            @Override
                            public void onNegativeClick(Dialog dialog) {
                                dialog.dismiss();
                                finish();
                            }
                        });
        dialog.show();
    }

    //mengulang cek koneksi
    private void retryOpenApplication() {
        new Handler().postDelayed(this::startProcess, 2000);
    }


}