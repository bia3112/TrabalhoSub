package com.example.trabalhoapipaises.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalhoapipaises.dto.PaisDto;
import com.example.trabalhoapipaises.helper.SQLiteDataHelper;

import java.util.ArrayList;

public class PaisDao implements IGenericDao<PaisDto>{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"CODIGO", "DESCRICAO"};
    private String tabela ="PAIS";
    private Context context;
    private static PaisDao instancia;

    private PaisDao(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context,
                "PAIS_BD", null, 1);

        baseDados = openHelper.getWritableDatabase();
    }

    public static PaisDao getInstance(Context context){
        if(instancia == null){
            return instancia = new PaisDao(context);
        }else{
            return instancia;
        }
    }

    @Override
    public long insert(PaisDto obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getDescricao());

            return baseDados.insert(tabela, null, valores);
        }catch (SQLException ex){
            Log.e("PAIS", "ERRO: PaisDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<PaisDto> getAll() {
        ArrayList<PaisDto> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela, colunas, null,
                    null, null, null, colunas[0]);

            if(cursor.moveToFirst()){
                do{
                    PaisDto pais = new PaisDto();
                    pais.setCodigo(cursor.getInt(0));
                    pais.setDescricao(cursor.getString(1));

                    lista.add(pais);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("PAIS", "ERRO: PaisDao.getAll() "+ex.getMessage());
        }
        return lista;
    }

}
