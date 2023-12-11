package com.example.trabalhoapipaises.service;

import com.example.trabalhoapipaises.dto.PaisDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPaisService {
    @GET("/api/paises")
    Call<ArrayList<PaisDto>> getPaises();

}
