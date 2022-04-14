package com.example.api_exp1.service;
import  com.example.api_exp1.Model.Produit;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoServiceAPI {
  String BASE_URL="http://10.0.2.2:8686/";
  @GET("produits/listes/")
    Call<List<Produit>> Affichage();
}
