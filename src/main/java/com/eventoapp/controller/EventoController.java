package com.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.model.Convidado;
import com.eventoapp.model.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller	
public class EventoController { 

	@Autowired(required=true)
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos! (Nome e RG não podem ser vazios...)");
			attributes.addFlashAttribute("flashType", "danger");
		
			return "redirect:/cadastrarEvento";
		}
		
		eventoRepository.save(evento);
		attributes.addFlashAttribute("flashMessage", "Evento adicionado com sucesso");
		attributes.addFlashAttribute("flashType", "success");
		
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		mv.addObject("eventos", eventos);
		
		return mv;
	}
	
	@RequestMapping("/deletar")
	public String deletarEvento(long codigo) {
		Evento evento = eventoRepository.findById(codigo);
		eventoRepository.delete(evento);
		
		return "redirect:/eventos";
	}
	
	
	@RequestMapping(value="/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = eventoRepository.findById(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("flashMessage", "Verifique os campos! (Nome e RG não podem ser vazios...)");
			attributes.addFlashAttribute("flashType", "danger");
		
			return "redirect:/{codigo}";
		}
		
		Evento evento = eventoRepository.findById(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		attributes.addFlashAttribute("flashMessage", "Convidado adicionado com sucesso!");
		attributes.addFlashAttribute("flashType", "success");
		
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(long codigo) {
		Convidado convidado = convidadoRepository.findById(codigo);
		convidadoRepository.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getId();
		String codigo1 = ""+codigoLong;
		return "redirect:/"+codigo1;
	}
}
