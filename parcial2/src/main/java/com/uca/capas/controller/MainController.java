package com.uca.capas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {

	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	LibroService libroService;
	
	@RequestMapping("/index")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/ingresarCategoria")
	public ModelAndView ingresarC() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoria", new Categoria());
		mav.setViewName("categoria");
		return mav;
	}
	
	@PostMapping("/guardarCategoria")
	public ModelAndView guardarCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("categoria");
		}else {
			categoriaService.save(categoria);
			mav.addObject("respuesta", "Categoría guardada con éxito");
			mav.setViewName("index");
		}
		return mav;
	}
	
	
	@RequestMapping("/ingresarLibro")
	public ModelAndView ingresarL() {
		ModelAndView mav = new ModelAndView();
		List<Categoria> categorias = null;
		try {
			categorias = categoriaService.findAll();
			mav.addObject("categoriaL", categorias);
		}catch (Exception e){
			e.printStackTrace();
		}
		mav.addObject("libro", new Libro());
		mav.setViewName("libro");
		return mav;
	}
	
	@PostMapping("/guardarLibro")
	public ModelAndView guardarLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");  
	    Date date = new Date();  
		if(result.hasErrors()) {
			List<Categoria> categorias = null;
			try {
				categorias = categoriaService.findAll();
				mav.addObject("categoriaL", categorias);
			}catch (Exception e){
				e.printStackTrace();
			}
			mav.setViewName("libro");
		}else {
			libro.setFechaIngreso(date);
			libroService.save(libro);
			mav.addObject("respuesta", "Libro guardado con éxito");
			mav.setViewName("index");
		}
		return mav;
	}
	
	
	@RequestMapping("/listadoLibros")
	public ModelAndView listadoL() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = null;
		try {
			libros = libroService.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		mav.addObject("libros", libros);
		mav.setViewName("listado");
		return mav;
	}
}
