package com.example.trabalhoapipaises.controller;

import android.content.Context;
import android.widget.TextView;

import com.example.trabalhoapipaises.dao.PaisDao;
import com.example.trabalhoapipaises.dto.PaisDto;
import com.example.trabalhoapipaises.retrofit.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaisController {

    private Context context;

    public PaisController(Context context) {
        this.context = context;
    }

    public String salvarListaPaises(String codigo, String descricao){
        try{
            PaisDto pais = new PaisDto();
            pais.setCodigo(Integer.parseInt(codigo));
            pais.setDescricao(descricao);

            PaisDao.getInstance(context).insert(pais);

        }catch (Exception ex){
            return "Erro ao salvar Paises";
        }
        return null;
    }

    public ArrayList<PaisDto> retornarListaPaises(){
        return PaisDao.getInstance(context).getAll();
    }


    public static void getPaises(TextView tvCodigo, TextView tvDescricao){
        try{
            Call<ArrayList<PaisDto>> call = new RetrofitConfig().paisService().getPaises();
            call.enqueue(new Callback<ArrayList<PaisDto>>() {
                @Override
                public void onResponse(Call<ArrayList<PaisDto>> call, Response<ArrayList<PaisDto>> response) {

                    if(response.isSuccessful()){
                        ArrayList<PaisDto> dto = response.body();
                        tvCodigo.setText(dto.get(0).getCodigo());
                        tvDescricao.setText(dto.get(1).getDescricao());
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<PaisDto>> call, Throwable t) {
                    tvCodigo.setText(t.getMessage());
                    tvDescricao.setText(t.getMessage());
                }

            });
        }catch (Exception ex){}
    }

}
