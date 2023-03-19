package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Condition;


@Service
@Transactional
public class ConditionDAOImpl implements ConditionDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Condition findById(int id) {
		return em.find(Condition.class, id);
	}

	@Override
	public List<Condition> findAll() {
		String jpql = "SELECT condition FROM Condition condition";
		return em.createQuery(jpql, Condition.class).getResultList();
	}

	@Override
	public Condition create(Condition condition) {

		em.persist(condition);

		return condition;
	}


	@Override
	public Condition update(int id, Condition condition) {

		Condition managed = em.find(Condition.class, id);
		
		if (managed != null) {
		managed.setName(condition.getName());
		
		
		
		em.persist(managed);
		em.flush();
		}
		
		return managed;
	}

	@Override
	public boolean deleteById(int id) {
		boolean success = true;

		Condition toDelete = em.find(Condition.class, id);
		if (em.contains(toDelete)) {
	
			em.remove(toDelete);
			

		} else {
			success = false;
		}
		return success;
	}

}
