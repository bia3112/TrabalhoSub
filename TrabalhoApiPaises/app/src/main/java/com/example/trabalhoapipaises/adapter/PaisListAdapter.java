package com.example.trabalhoapipaises.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhoapipaises.R;
import com.example.trabalhoapipaises.dto.PaisDto;

import java.util.ArrayList;

public class PaisListAdapter extends
        RecyclerView.Adapter<PaisListAdapter.ViewHolder> {

    private ArrayList<PaisDto> listaPais;
    private Context context;

    public PaisListAdapter(ArrayList<PaisDto> listaPais, Context context) {
        this.listaPais = listaPais;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCodigo;
        public TextView tvDescricao;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvDescricao = itemView.findViewById(R.id.tvDescricao);
        }
    }

    @NonNull
    @Override
    public PaisListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listaItem = inflater.inflate(R.layout.item_lista_pais,
                parent, false);

        return new ViewHolder(listaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisListAdapter.ViewHolder holder, int position) {
        PaisDto paises = listaPais.get(position);
        holder.tvCodigo.setText(String.valueOf(paises.getCodigo()));
        holder.tvDescricao.setText(paises.getDescricao());
    }

    @Override
    public int getItemCount() {
        return this.listaPais.size();
    }

}
