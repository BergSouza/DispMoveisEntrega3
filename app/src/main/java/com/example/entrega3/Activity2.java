package com.example.entrega3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    Button btnConcluir;
    int chamaFuncao;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        chamaFuncao = getIntent().getExtras().getInt("chamaFuncao");
        btnConcluir = findViewById(R.id.btnConcluir);
        if(chamaFuncao == 1){
            atualizaItem();
        }
        if(chamaFuncao == 2){
            editaItem();
        }
    }

    public boolean verificaCamposFormulario(String marca, String modelo, String ano, String cor){
        boolean verificaMarca = false;
        boolean verificaModelo = false;
        boolean verificaAno = false;
        boolean verificaCor = false;
        if(marca.equals("")){
            Toast.makeText(Activity2.this, "Digite uma Marca!", Toast.LENGTH_SHORT).show();
        }else{
            char[] marcaC = marca.toCharArray();
            for(int i = 0; i < marcaC.length;i++){
                if(!(marcaC[i] == (' '))){
                    verificaMarca = true;
                }
            }
        }
        if(!verificaMarca){
            Toast.makeText(Activity2.this, "Digite uma Marca!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(modelo.equals("")){
            Toast.makeText(Activity2.this, "Digite um Modelo!", Toast.LENGTH_SHORT).show();
        }else{
            char[] modeloC = modelo.toCharArray();
            for(int i = 0; i < modeloC.length;i++){
                if(!(modeloC[i] == (' '))){
                    verificaModelo = true;
                }
            }
        }
        if(!verificaModelo){
            Toast.makeText(Activity2.this, "Digite um Modelo!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ano.equals("")){
            Toast.makeText(Activity2.this, "Digite um Ano!", Toast.LENGTH_SHORT).show();
        }else{
            char[] anoC = ano.toCharArray();
            for(int i = 0; i < anoC.length;i++){
                if(!(anoC[i] == (' '))){
                    verificaAno = true;
                }
            }
        }
        if(!verificaAno){
            Toast.makeText(Activity2.this, "Digite um Ano!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cor.equals("")){
            Toast.makeText(Activity2.this, "Digite uma Cor!", Toast.LENGTH_SHORT).show();
        }else{
            char[] corC = cor.toCharArray();
            for(int i = 0; i < corC.length;i++) {
                if (!(corC[i] == (' '))) {
                    verificaCor = true;
                }
            }
        }
        if(!verificaCor){
            Toast.makeText(Activity2.this, "Digite uma Cor!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void atualizaItem() {
        id = getIntent().getExtras().getInt("idLivre");
        btnConcluir.setText("Adicionar");
    }

    public void editaItem() {
        id = getIntent().getExtras().getInt("idSelecionado");
        String marca = getIntent().getExtras().getString("marca");
        String modelo = getIntent().getExtras().getString("modelo");
        String ano = Integer.toString(getIntent().getExtras().getInt("ano"));
        String cor = getIntent().getExtras().getString("cor");
        EditText inputMarca = findViewById(R.id.InputMarca);
        EditText inputModelo = findViewById(R.id.InputModelo);
        EditText inputAno = findViewById(R.id.InputAno);
        EditText inputCor = findViewById(R.id.InputCor);
        inputMarca.setText(marca);
        inputModelo.setText(modelo);
        inputAno.setText(ano);
        inputCor.setText(cor);
        btnConcluir.setText("Atualizar");
    }

    public void concluir(View v){
        EditText inputMarca = findViewById(R.id.InputMarca);
        EditText inputModelo = findViewById(R.id.InputModelo);
        EditText inputAno = findViewById(R.id.InputAno);
        EditText inputCor = findViewById(R.id.InputCor);
        String marca = String.valueOf(inputMarca.getText());
        String modelo = String.valueOf(inputModelo.getText());
        String ano = String.valueOf(inputAno.getText());
        String cor = String.valueOf(inputCor.getText());

        boolean formularioVerificado = verificaCamposFormulario(marca,modelo,ano,cor);

        if(formularioVerificado) {
            Intent returnIntent = new Intent();
            if (chamaFuncao == 1) {
                returnIntent.putExtra("chamaFuncao", "1");
            }
            if (chamaFuncao == 2) {
                returnIntent.putExtra("chamaFuncao", "2");
            }
            returnIntent.putExtra("id", Integer.toString(id));
            returnIntent.putExtra("marca", marca);
            returnIntent.putExtra("modelo", modelo);
            returnIntent.putExtra("ano", ano);
            returnIntent.putExtra("cor", cor);
            setResult(RESULT_OK, returnIntent);
            fecha2();
        }
    }

    public void fecha(View v){
        finish();
    }
    public void fecha2(){
        finish();
    }
}