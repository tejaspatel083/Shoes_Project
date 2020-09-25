package com.example.goshoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment extends AppCompatActivity {

    Button payContinue;
    EditText cardname,cardno,mm,yy,cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardname = findViewById(R.id.card_name);
        cardno = findViewById(R.id.card_number);
        mm = findViewById(R.id.expiry_month);
        yy = findViewById(R.id.expiry_year);
        cvv = findViewById(R.id.cvv_number);

        payContinue = findViewById(R.id.make_payment);

        Intent intent = getIntent();
        final String s =  intent.getStringExtra("shoe_quantity");


        payContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                if (cardno.getText().toString().trim().length() == 0 || cardname.getText().toString().trim().length() == 0 || mm.getText().toString().trim().length() == 0 || yy.getText().toString().trim().length() == 0 || cvv.getText().toString().trim().length() == 0 )
                {
                    Toast toast = Toast.makeText(Payment.this,"Enter All Details",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    Intent intent = new Intent(Payment.this, PayConfirm.class);
                    intent.putExtra("shoe_quantity",s);
                    startActivity(intent);
                }




            }
        });
    }
}
