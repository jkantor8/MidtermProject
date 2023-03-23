package com.skilldistillery.sportswap.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Post;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.SwapListing;
import com.skilldistillery.sportswap.entities.User;

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
	public Post addToDonate(Post post, User user, DonationListing donationListing) {
		User managedUser =em.find(User.class, user.getId());
		DonationListing mangagedListing = em.find(DonationListing.class, donationListing.getId());
		
		
		post.setPostingUser(managedUser);
		post.setDonationListing(mangagedListing);
		post.setActive(true);
		em.persist(post);
		
		return post;
	}
	
	
	
	@Override
	public Post addToSale(Post post, User user, SaleListing saleListing) {
		User managedUser =em.find(User.class, user.getId());
		SaleListing mangagedListing = em.find(SaleListing.class, saleListing.getId());

		post.setPostingUser(managedUser);
		post.setSaleListing(mangagedListing);
		post.setActive(true);
		em.persist(post);
		
		return post;
	}
	
	
	@Override
	public Post addToSwap(Post post, User user, SwapListing swapListing) {
		User managedUser =em.find(User.class, user.getId());
		SwapListing mangagedListing = em.find(SwapListing.class, swapListing.getId());

		post.setPostingUser(managedUser);
		post.setSwapListing(mangagedListing);
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
