package com.example.goshoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button login,signUp;
    private TextView forgotPassword;
    private EditText email,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(MainActivity.this);


        login = findViewById(R.id.MainLoginBtn);
        signUp = findViewById(R.id.MainCreateBtn);
        forgotPassword = findViewById(R.id.MainForgotPassword);
        email = findViewById(R.id.MainEmail);
        password = findViewById(R.id.MainPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();




        if (firebaseUser != null)
        {

            startActivity(new Intent(MainActivity.this,HomePage.class));
            finish();
        }



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,CreateAccount.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                //mp.start();


                if (email.getText().toString().trim().length() == 0)
                {
                    email.setError("Email Id Required");
                     Toast toast = Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (password.getText().toString().trim().length() == 0)
                {
                    email.setError(null);
                    password.setError("Password Required");
                    //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    progressDialog.setMessage("Logging in...");
                    progressDialog.show();
                    email.setError(null);
                    password.setError(null);
                    validate(email.getText().toString(),password.getText().toString());

                }
            }
        });


    }

    private void validate(String userName,String userPassword)
    {

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    checkEmailVerification();
                }
                else
                {
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this,"Enter Valid Username and Password",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Valid Email and Password",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

            }
        });


    }

    private void checkEmailVerification()
    {
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag)
        {
            progressDialog.dismiss();
            //Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
            Toast toast = Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            startActivity(new Intent(MainActivity.this,HomePage.class));
        }
        else
        {
            progressDialog.dismiss();
            //Toast.makeText(MainActivity.this,"Need to Verify Email",Toast.LENGTH_LONG).show();
            Toast toast = Toast.makeText(MainActivity.this,"Need to Verify Email",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            firebaseAuth.signOut();
        }

    }

}
