package com.eventoapp.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
//@Table(name="convidado")
public class Convidado{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	@NotEmpty
	private String rg;

	@Column
	@NotEmpty
	private String nomeConvidado;
	
	
	@ManyToOne
	private Evento evento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
}
