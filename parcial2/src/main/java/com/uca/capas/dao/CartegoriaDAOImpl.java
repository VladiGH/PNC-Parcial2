package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Categoria;

@Repository
public class CartegoriaDAOImpl implements CategoriaDAO{
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	@Override
	public List<Categoria> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_categoria");
		Query query = entityManager.createNativeQuery(sb.toString(), Categoria.class); 
		List <Categoria> resultset = query.getResultList();
		return resultset;
	}

	@Override
	public void save(Categoria categoria) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			if(categoria.getCodigoCategoria()==null)
				entityManager.persist(categoria);
			else {
				entityManager.merge(categoria);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}

}
