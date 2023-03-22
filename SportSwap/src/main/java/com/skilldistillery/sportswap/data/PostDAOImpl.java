package com.skilldistillery.sportswap.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Post;

@Transactional
@Service
public class PostDAOImpl implements PostDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Post findById(int id) {
		return em.find(Post.class, id);
		
	}

	@Override
	public Post add(Post post) {
		
		post.setActive(true);
		em.persist(post);
		
		return post;
	}

	@Override
	public Post update(int id, Post post) {
		
		Post updatedPost = em.find(Post.class, id);
		
		updatedPost.setComment(post.getComment());
		updatedPost.setCreated(post.getCreated());
		updatedPost.setUpdated(post.getUpdated());
		updatedPost.setDeactivated(post.getDeactivated());
		updatedPost.setActive(true);
		return updatedPost;
	}

	@Override
	public boolean deactivate(int id) {
		boolean deactivated = false;
		
		Post post = em.find(Post.class, id);
		if (em.contains(post)) {
			post.setActive(false);
			deactivated = true;
		}
		
		return deactivated;
	}

}
