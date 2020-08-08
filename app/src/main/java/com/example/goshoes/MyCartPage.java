package com.example.goshoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCartPage extends AppCompatActivity {

    private Button btn1,btn2,checkoutbtn;
    private TextView t1,shoe_price,brand_name;
    private ImageView cart_imageview;
    int quantity1=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart_page);



        Intent intent = getIntent();
        final String received_Name =  intent.getStringExtra("shoe_name");
        final int received_Image = intent.getIntExtra("shoe_image",0);
        final String received_Price =  intent.getStringExtra("shoe_price");


        btn1 = findViewById(R.id.minusbtn);
        btn2 = findViewById(R.id.plusbtn);
        checkoutbtn = findViewById(R.id.checkout);

        cart_imageview = findViewById(R.id.shoe_cart_imageview);

        t1 = findViewById(R.id.cartpage_text);
        shoe_price = findViewById(R.id.shoe_cart_price);
        brand_name = findViewById(R.id.shoe_cart_brandname);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement1();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment1();
            }
        });

        t1.setText("" + quantity1);
        //int x = Integer.parseInt(received_Price);
        int l = 10*quantity1;
        shoe_price.setText(received_Price);
        brand_name.setText(received_Name);
        cart_imageview.setImageResource(received_Image);

        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyCartPage.this,PaymentOptions.class);
                intent.putExtra("shoe_name",received_Name);
                intent.putExtra("shoe_image",received_Image);
                intent.putExtra("shoe_price",received_Price);
                startActivity(intent);

            }
        });

    }

    public int increment1 () {
        quantity1 = quantity1 + 1;
        return quantity1;
    }

    public int decrement1 () {
        if (quantity1>0){
            quantity1 = quantity1 - 1;

        }
        else
        {

        }
        return quantity1;
    }
}
