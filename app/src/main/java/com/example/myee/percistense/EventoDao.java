package com.example.myee.percistense;

import com.example.myee.model.Event;

import java.util.List;

public interface EventoDao {
    public void salvar(Event e);

    public void editar(Event e);

    public void remover(Event e);

    public List listagem();
}
