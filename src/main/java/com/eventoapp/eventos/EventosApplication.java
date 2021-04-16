package com.eventoapp.eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eventoapp.model.Convidado;
import com.eventoapp.model.Evento;

@SpringBootApplication
@ComponentScan({"com.eventoapp.controller"})
@EnableJpaRepositories({"com.eventoapp.repository"})
@EntityScan(basePackages = { "com.eventoapp.model" })
@ComponentScan(basePackageClasses = { Evento.class })
@ComponentScan(basePackageClasses = { Convidado.class })

public class EventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventosApplication.class, args);
	}

}
