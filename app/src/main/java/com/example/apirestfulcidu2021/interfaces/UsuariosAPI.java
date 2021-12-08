package com.example.apirestfulcidu2021.interfaces;

import com.example.apirestfulcidu2021.modelos.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuariosAPI {
    @GET("users")
    Call<List<Usuarios>> getData();
}
