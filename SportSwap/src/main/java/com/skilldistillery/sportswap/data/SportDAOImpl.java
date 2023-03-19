package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Sport;

@Service
@Transactional
public class SportDAOImpl implements SportDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Sport findById(int id) {
		return em.find(Sport.class, id);
	}

	@Override
	public List<Sport> findAll() {
		String jpql = "SELECT sport FROM Sport sport";
		return em.createQuery(jpql, Sport.class).getResultList();
	}

	@Override
	public Sport add(Sport sport) {

		em.persist(sport);

		return sport;
	}

	
	@Override
	public Sport update(int id, Sport sport) {

		Sport managed = em.find(Sport.class, id);
		
		if (managed != null) {
		managed.setName(sport.getName());
		
		
		
		em.persist(managed);
		em.flush();
		}
		
		return managed;
	}

	@Override
	public boolean deleteById(int id) {
		boolean success = true;

		Sport toDelete = em.find(Sport.class, id);
		if (em.contains(toDelete)) {
	
			em.remove(toDelete);
			

		} else {
			success = false;
		}
		return success;
	}

	
	

}

