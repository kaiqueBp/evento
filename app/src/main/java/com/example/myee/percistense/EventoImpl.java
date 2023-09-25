package com.example.myee.percistense;

import com.example.myee.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventoImpl implements EventoDao{
    private  List lista;
    public EventoImpl(){lista = new ArrayList();}
    @Override
    public void salvar(Event e) {lista.add(e);}

    @Override
    public void editar(Event e) {
        if(lista.contains(e)){
            lista.add(lista.indexOf(e),e);
        }
    }

    @Override
    public void remover(Event e) {lista.remove(e);}

    @Override
    public List listagem() {
        return lista;
    }
}
