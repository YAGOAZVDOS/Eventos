package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoapp.model.Convidado;
import com.eventoapp.model.Evento;

@Repository
public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findById(long id);
}
