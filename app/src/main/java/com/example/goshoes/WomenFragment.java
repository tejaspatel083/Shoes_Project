package com.example.goshoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WomenFragment extends Fragment {

    GridView gridView;

    public String[] womenShoesNames = {"Adidas","Adidas","Fila","Fila","Puma","Puma","Reebok","Reebok","Nike","Nike"};
    public int[] womenShoesImages = {R.drawable.women_adidas_1,R.drawable.women_adidas_2,R.drawable.women_fila_1,R.drawable.women_fila_2,R.drawable.women_puma_1,R.drawable.women_puma_2,R.drawable.women_reebok_1,R.drawable.women_reebok_2,R.drawable.women_nike_1,R.drawable.women_nike_2};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_women,container,false);


        gridView = view.findViewById(R.id.women_grid_view);

        WomenCustomAdapter customAdapter = new WomenCustomAdapter();

        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                //intent.putExtra("name",fruitNames[i]);
                //intent.putExtra("image",fruitImages[i]);
                //startActivity(intent);
                Toast.makeText(getContext(), womenShoesNames[position], Toast.LENGTH_SHORT).show();
            }

        });
        return view;

    }
}
