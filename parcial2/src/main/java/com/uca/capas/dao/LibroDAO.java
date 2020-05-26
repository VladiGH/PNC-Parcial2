package com.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Libro;


public interface LibroDAO {

	public List<Libro> findAll() throws DataAccessException;
	
	public void save(Libro libro) throws DataAccessException;
}
