package com.example.trabalhoapipaises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.trabalhoapipaises.adapter.PaisListAdapter;
import com.example.trabalhoapipaises.controller.PaisController;
import com.example.trabalhoapipaises.dto.PaisDto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PaisController controller;
    private RecyclerView rvPaises;
    private PaisListAdapter.ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new PaisController(this);
        rvPaises = findViewById(R.id.rvPaises);

        getPais();
        salvaDados(viewHolder);
        atualizarLista();
    }

    private void getPais(){
        PaisController.getPaises(viewHolder.tvCodigo, viewHolder.tvDescricao);
    }

    private void salvaDados(PaisListAdapter.ViewHolder viewHolder){
        String codigo = viewHolder.tvCodigo.getText().toString();
        String descricao = viewHolder.tvDescricao.getText().toString();

        String retorno = controller.salvarListaPaises(codigo, descricao);
        if(retorno != null){
            if(retorno.contains("CODIGO")){
                retorno.toString();
            }
            if(retorno.contains("DESCRICAO")){
                retorno.toString();
            }
        }else{
            Toast.makeText(this, "Dados salvos",
                    Toast.LENGTH_LONG).show();

            atualizarLista();
        }

    }

    private void atualizarLista() {
        ArrayList<PaisDto> listaPaises = controller.retornarListaPaises();
        PaisListAdapter adapter = new PaisListAdapter(listaPaises, this);
        rvPaises.setLayoutManager(new LinearLayoutManager(this));
        rvPaises.setAdapter(adapter);
    }

}