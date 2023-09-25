package com.example.myee;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import com.example.myee.model.Event;
import com.example.myee.percistense.DBhelper;
import com.example.myee.percistense.EventoDao;
import com.example.myee.percistense.EventoImplDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText nome;
    private DatePicker data;
    private Spinner sp;
    private Button botao;
    private ListView listagem;
    private List<Event> dados;
    private EventoDao dao;
    private Event a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapeamentoXML();
        consulta();
        vinculaAdapterALista();
        acoes();

    }
    private void mapeamentoXML(){
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editTextEventName);
        data = findViewById(R.id.datePickerEventDate);
        sp = findViewById(R.id.spinnerEventCategory);
        botao = findViewById(R.id.buttonAddEvent);
        listagem = findViewById(R.id.listViewEvents);

    }
    private void consulta(){
        if(dao==null)
            dao= new EventoImplDB(this);
        dados=dao.listagem();

    }

    private void vinculaAdapterALista() {

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.event_categories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerAdapter);

        listagem.setAdapter(
                new ArrayAdapter(getApplicationContext(),
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        dados)// cria o adapter
        );//
    }

    private void acoes() {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(a==null)
                        a = new Event();
                    a.setNome(nome.getText().toString());
                    a.setData(data.getDayOfMonth() + "/" +
                            (data.getMonth() + 1) + "/" +
                            data.getYear());
                    a.setCategoria(sp.getSelectedItem().toString());
                    if(a.getId()==null)
                        dao.salvar(a);
                    else dao.editar(a);
                    limpaCampos();
                    atualizaDados();
                }
        });
        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int indice, long l) {
                new  AlertDialog.Builder(listagem.getContext())
                        .setTitle("Realmente deseja excluir")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).
                        setNeutralButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dao.remover(dados.get(indice));
                                atualizaDados();
                            }
                        })
                        .create().show();

                return false;
            }
        });
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a=dados.get(i);
                nome.setText(a.getNome());

            }
        });
    }

    private void atualizaDados() {
        dados.clear();
        dados.addAll(dao.listagem());
        ((ArrayAdapter) listagem.getAdapter()
        ).notifyDataSetChanged();
    }
    private void limpaCampos() {
        nome.setText("");
        sp.setSelection(0);
        a=null;
    }


    public void cancelar(View view){
        AlertDialog.Builder acao = new AlertDialog.Builder(this);
        acao.setTitle("Você quer sair");
        acao.setItems(new CharSequence[]{"Sair"},new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        acao.create().show();
    }
}