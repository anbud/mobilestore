package beans.post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Auction;
import model.Comment;
import model.Phone;
import model.PhonePicture;
import model.User;

@Stateless
public class PostEJB implements PostManager {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	@Override
	public Comment postComment(User user, Auction auction, Comment comment) {
		user.addComment(comment);
		auction.addComment(comment);		
		try {
			em.persist(comment);
			return comment;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Auction postAuction(User user, Auction auction, Phone phone) {
		user.addAuction(auction);		
		user.addPhone(phone);
		auction.addPhone(phone);		
		try {
			em.persist(phone);
			em.persist(auction);
			return auction;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Auction postBid(User user, Auction auction, int bid) {
		auction.setBid(bid);
		if (isParticipant(user, auction)) {
			try {
				auction = em.merge(auction);
				return auction;
			} catch (Exception e) {	
				return null;
			}
		} else {
			user.addParticipation(auction);
			auction.addParticipant(user);
			try {
				auction = em.merge(auction);
				return auction;
			} catch (Exception e) {
				return null;
			}
		}
	}	

	@Override
	public boolean postAuctionClosed(Auction auction) {
		auction.setClosed(true);
		try {
			em.merge(auction);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isParticipant(User user, Auction auction) {
		if (user.getParticipations().contains(auction))
			return true;
	
		user = em.find(User.class, user.getUsername());

		if (user.getParticipations().contains(auction))
			return true;

		return false;
	}

	@Override
	public Phone postPictures(Phone phone, PhonePicture... pictures) {
		phone.addPictures(pictures);
		try {
			 phone = em.merge(phone);
			 return phone;
		} catch (Exception e) {
			return null;
		}	
	}
}
