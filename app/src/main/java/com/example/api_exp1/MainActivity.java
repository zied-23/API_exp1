package com.example.api_exp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.api_exp1.Model.Produit;
import com.example.api_exp1.service.RepoServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView editTextQuery = findViewById(R.id.editText);
        ArrayList<String> listItems = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1
                ,                android.R.id.text1, listItems
        );
        editTextQuery.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RepoServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RepoServiceAPI myApi = retrofit.create(RepoServiceAPI.class);

        Call<List<Produit>> call = myApi.Affichage();

        call.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                List<Produit> lists = response.body();
                for (Produit pr : lists) {
                    String content = "";
                    content += "NOM : " + pr.getNomProduit();
                    listItems.add(content);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();

            }
        });
    }
}

