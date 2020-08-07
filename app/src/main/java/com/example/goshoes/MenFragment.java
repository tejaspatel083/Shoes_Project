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

public class MenFragment extends Fragment {

    GridView gridView;

    public String[] shoesNames = {"Adidas","Adidas","Fila","Fila","Puma","Puma","Reebok","Reebok","Nike","Nike"};
    public int[] shoesImages = {R.drawable.adidas_1,R.drawable.adidas_2,R.drawable.fila_1,R.drawable.fila_2,R.drawable.puma_1,R.drawable.puma_2,R.drawable.reebok_1,R.drawable.reebok_2,R.drawable.nike_1,R.drawable.nike_2};




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_men,container,false);

        gridView = view.findViewById(R.id.men_grid_view);

        MenCustomAdapter customAdapter = new MenCustomAdapter();

        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                //intent.putExtra("name",fruitNames[i]);
                //intent.putExtra("image",fruitImages[i]);
                //startActivity(intent);
                Toast.makeText(getContext(), shoesNames[position], Toast.LENGTH_SHORT).show();
            }

        });

        return view;

    }
}

