package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.Sport;
import com.skilldistillery.sportswap.entities.User;

@Transactional
@Service
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User login(String name, String pw) {
		User user = null;
		String jpql = "SELECT u FROM User u WHERE u.username = :name "
						+ "AND u.password = :pass AND u.active = 1";
		try {
		user = em.createQuery(jpql, User.class)
				.setParameter("name", name)
				.setParameter("pass", pw)
				.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		return user;
	}
	
	@Override
	public User add(User user, Address address, int sportId, int sportId2) {
		
		Address managedAddress = em.find(Address.class, address.getId());
		Sport managedSport1 = em.find(Sport.class, sportId);
		Sport managedSport2 = em.find(Sport.class, sportId2);
		
		user.setUserAddress(managedAddress);
		user.addSport(managedSport1);
		user.addSport(managedSport2);
		user.setActive(true);
		em.persist(user);
		
		return user;
	}
	
	@Override
	public User findUserById(int id) {
		User user = em.find(User.class, id);
		return user;
	}
	
	@Override
	@Transactional
	public User updateUser(int id, User updatedUser) {
		
		//get managed user
		User user = em.find(User.class,id);
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		user.setUserAddress(updatedUser.getUserAddress());
		
		return user;
	}
	
	@Override
    public User findByUsername(String username) {
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        User user = null;
        try {
            user = em.createQuery(jpql, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            // No user with the given username found
            user = null;
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }
	
	@Override
	public List<User> getAllUsers(){
		String jpql = "SELECT u FROM User u";
		List<User> users = em.createQuery(jpql,User.class).getResultList();
		return users;
	}

	
	
}
