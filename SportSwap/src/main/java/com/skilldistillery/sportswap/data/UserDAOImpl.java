package com.skilldistillery.sportswap.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.User;

@Transactional
@Service
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User login(User user) {
		String jpql = "SELECT u FROM User u WHERE u.username = :name "
						+ "AND u.password = :pass AND u.enabled = 1";
		try {
		user = em.createQuery(jpql, User.class)
				.setParameter("name", user.getUsername())
				.setParameter("pass", user.getPassword())
				.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		return user;
	}
	
}
