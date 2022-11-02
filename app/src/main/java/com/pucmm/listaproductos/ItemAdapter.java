package com.pucmm.listaproductos;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
 {
     private final List<Producto> list;
     public ItemAdapter(List<Producto> list) {this.list = list;}
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos , parent , false);
         return new ItemViewHolder(view);
    }

    @Override
     public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {

         Producto product = list.get(position);

         holder.name.setText(product.getName());
         holder.description.setText(product.getDescription());
         holder.price.setText(product.getPrice());

         holder.share.setOnClickListener(view ->{
             Intent sendIntent = new Intent();
             sendIntent.setAction(Intent.ACTION_SEND);
             sendIntent.setType("text/plain");
             sendIntent.putExtra(Intent.EXTRA_TEXT, holder.description.toString());
             view.getContext().startActivity(sendIntent);
         });
         holder.delete.setOnClickListener(view ->{
             //list.remove(holder.getAdapterPosition());
             //notifyItemRemoved(holder.getAdapterPosition());
             list.remove(position);
             notifyDataSetChanged();
             Toast.makeText(view.getContext(), "Articulo borrado excitosamente!", Toast.LENGTH_SHORT).show();
         });
     }

    @Override
    public int getItemCount() {
        return list.size();
    }

     public static class  ItemViewHolder extends RecyclerView.ViewHolder {

         TextView name;
         TextView description;
         TextView price;
         ExtendedFloatingActionButton delete;
         ExtendedFloatingActionButton share;

         public ItemViewHolder(@NonNull View itemView) {
             super(itemView);

             name = itemView.findViewById(R.id.name_tv);
             description = itemView.findViewById(R.id.description_tv);
             price   = itemView.findViewById(R.id.price_tv);
             delete = itemView.findViewById(R.id.delete_b);
             share = itemView.findViewById(R.id.share_b);

         }
     }

}
