package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Libro;

public interface LibroService {

	public List<Libro> findAll() throws DataAccessException;
	
	public void save(Libro libro) throws DataAccessException;
}
