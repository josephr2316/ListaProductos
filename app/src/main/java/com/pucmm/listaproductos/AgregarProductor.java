package com.pucmm.listaproductos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class AgregarProductor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productor);
        ExtendedFloatingActionButton clean = findViewById(R.id.clean_b);
        ExtendedFloatingActionButton addb = findViewById(R.id.addl_b);
        ExtendedFloatingActionButton returnb = findViewById(R.id.return_b);
        EditText articulo = findViewById(R.id.articulo_tn);
        EditText description = findViewById(R.id.description_tn);
        EditText precio = findViewById(R.id.precio_tn);

        clean.setOnClickListener(view->{
            cleanView(articulo, description, precio);
        });
        addb.setOnClickListener(view ->{
            if (!articulo.getText().toString().isEmpty() && !description.getText().toString().isEmpty() && !precio.getText().toString().isEmpty()){

                int count = 0;
                for (Producto product: MainActivity.list){
                    if(product.name.equals(articulo.getText().toString())){
                        Toast.makeText(AgregarProductor.this,"Este articulo esta digitado",Toast.LENGTH_LONG).show();
                        count = 1;
                    }
                }
                if (count==0) {
                    MainActivity.list.add(new Producto(articulo.getText().toString(), description.getText().toString(), precio.getText().toString()));
                    Toast.makeText(AgregarProductor.this, "Articulo guardado excitosamente", Toast.LENGTH_SHORT).show();
                    cleanView(articulo, description, precio);

                }
            }
            else {
                if (articulo.getText().toString().isEmpty())
                    articulo.setError("Digite el nombre del articulo");
                if (description.getText().toString().isEmpty())
                    description.setError("Digite la descripcion");
                if (precio.getText().toString().isEmpty())
                    precio.setError("Digite el precio");
            }

        });
        returnb.setOnClickListener(view ->{
            Intent goBack = new Intent(this,MainActivity.class);
            startActivity(goBack);

        });

    }
    public void cleanView(EditText articulo,EditText description, EditText precio){
        articulo.setText("");
        description.setText("");
        precio.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}