package com.example.entrega2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Carro> arrayList;

    static final int ACTIVITY_2_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        displayItens();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_2_REQUEST) {
            if (resultCode == RESULT_OK) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                String marca = data.getStringExtra("marca");
                String modelo = data.getStringExtra("modelo");
                int ano = Integer.parseInt(data.getStringExtra("ano"));
                String cor = data.getStringExtra("cor");

                if(data.getStringExtra("chamaFuncao").equals("1")) {
                    Carro car = new Carro(marca, modelo, ano, cor);
                    car.setId(id);
                    arrayList.add(car);
                }
                if(data.getStringExtra("chamaFuncao").equals("2")) {
                    for(int i = 0; i < arrayList.size();i++){
                        if(arrayList.get(i).getId() == id){
                            Carro car = arrayList.get(i);
                            car.setMarca(marca);
                            car.setModelo(modelo);
                            car.setAno(ano);
                            car.setCor(cor);
                        }
                    }
                }
                displayItens();
            }
        }
    }

    public int buscaIdInexistente(){
        int auxId = 1;
        for(int i = 0; i < arrayList.size();i++){
            if(arrayList.get(i).getId() == auxId){
                auxId++;
            }
            else{
                return auxId;
            }
        }
        return auxId;
    }

    public boolean verificaIdExiste(int id){
        for(int i = 0; i < arrayList.size();i++){
            if(arrayList.get(i).getId() == id){
                return true;
            }
        }
        return false;
    }

    public void displayItens(){
        TextView txtLista = findViewById(R.id.txtLista);
        txtLista.setText("");
        for(int i = 0; i < arrayList.size();i++){
            txtLista.setText(txtLista.getText()+"----------------------" +
                    "\nID: "+arrayList.get(i).getId()+
                    "\nMarca: "+arrayList.get(i).getMarca()+
                    "\nModelo: "+arrayList.get(i).getModelo()+
                    "\nAno: "+arrayList.get(i).getAno()+
                    "\nCor: "+arrayList.get(i).getCor()+"\n");
        }
    }

    public void adicionar(View v){
        int idLivre = buscaIdInexistente();
        Intent i = new Intent(MainActivity.this, Activity2.class);
        i.putExtra("chamaFuncao",1);
        i.putExtra("idLivre",idLivre);
        startActivityForResult(i, ACTIVITY_2_REQUEST);
    }

    public void editar(View v){
        EditText txtIptEdtItem = findViewById(R.id.txtIptEdtItem);
        try{
            int idSelecionado = Integer.parseInt(String.valueOf(txtIptEdtItem.getText()));
            if(verificaIdExiste(idSelecionado)){
                Intent i = new Intent(MainActivity.this, Activity2.class);
                i.putExtra("chamaFuncao",2);
                i.putExtra("idSelecionado",idSelecionado);
                for(int o = 0; o < arrayList.size(); o++){
                    if(arrayList.get(o).getId() == idSelecionado){
                        i.putExtra("marca", arrayList.get(o).getMarca());
                        i.putExtra("modelo", arrayList.get(o).getModelo());
                        i.putExtra("ano", arrayList.get(o).getAno());
                        i.putExtra("cor", arrayList.get(o).getCor());
                    }
                }
                startActivityForResult(i, ACTIVITY_2_REQUEST);
            }else{
                Toast.makeText(MainActivity.this, "Identificador não encontrado!", Toast.LENGTH_SHORT).show();
            }
        }catch(java.lang.NumberFormatException err){
            Toast.makeText(MainActivity.this, "Digite um identificador!", Toast.LENGTH_SHORT).show();
        }

    }
}