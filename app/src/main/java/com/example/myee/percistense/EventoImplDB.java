package com.example.myee.percistense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.myee.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventoImplDB implements EventoDao{

    DBhelper db;
    @Override
    public void salvar(Event e) {
        ContentValues dados = new ContentValues();
        dados.put("nome",e.getNome());
        dados.put("data", e.getData());
        dados.put("categoria",e.getCategoria());
        db.getWritableDatabase().insertOrThrow("evento",null,dados);
        db.close();
    }

    @Override
    public void editar(Event e) {
        ContentValues dados = new ContentValues();
        dados.put("nome",e.getNome());
        dados.put("data", e.getData());
        dados.put("categoria",e.getCategoria());
        db.getWritableDatabase().update("evento",dados,"id=?",new String[]{e.getId()+""});
        db.close();
    }

    @Override
    public void remover(Event e) {
        db.getWritableDatabase().delete("evento","id=?",new String[]{e.getId()+""});
        db.close();
    }

    public EventoImplDB(Context context){
        this.db=new DBhelper(context);
    }

    @Override
    public List listagem() {
        List retorno = new ArrayList();
        String colunas[] = {"id","nome","data","categoria"};
        Cursor cursor = db.getReadableDatabase().query("evento", colunas, null,
                null, null, null, "nome");

        final int COLUMN_ID=0, CULUMN_TITULO=1, COLUMN_DESCRICAO=2, COLUMN_ST=3;
        while(cursor.moveToNext()){
            Event e = new Event();
            e.setId(cursor.getInt(COLUMN_ID));
            e.setNome(cursor.getString(CULUMN_TITULO));
            e.setData(cursor.getString(COLUMN_DESCRICAO));
            e.setCategoria(cursor.getString(COLUMN_ST));
            retorno.add(e);
        }
        db.close();

        return retorno;
    }
}
