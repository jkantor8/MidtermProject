package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.AgeGroup;

@Service
@Transactional
public class AgeGroupDAOImpl implements AgeGroupDAO {


		@PersistenceContext
		private EntityManager em;

		@Override
		public AgeGroup findById(int id) {
			return em.find(AgeGroup.class, id);
		}

		@Override
		public List<AgeGroup> findAll() {
			String jpql = "SELECT ageGroup FROM AgeGroup ageGroup";
			return em.createQuery(jpql, AgeGroup.class).getResultList();
		}

		@Override
		public AgeGroup create(AgeGroup ageGroup) {

			em.persist(ageGroup);

			return ageGroup;
		}


		@Override
		public AgeGroup update(int id, AgeGroup ageGroup) {

			AgeGroup managed = em.find(AgeGroup.class, id);
			
			if (managed != null) {
			managed.setAge(ageGroup.getAge());
			
			
			
			em.persist(managed);
			em.flush();
			}
			
			return managed;
		}

		@Override
		public boolean deleteById(int id) {
			boolean success = true;

			AgeGroup toDelete = em.find(AgeGroup.class, id);
			if (em.contains(toDelete)) {
		
				em.remove(toDelete);
				

			} else {
				success = false;
			}
			return success;
		}

}
