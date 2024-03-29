package beans.post;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Auction;
import model.Comment;
import model.Phone;
import model.PhonePicture;
import model.User;

@Stateless
@Remote(PostManager.class)
public class PostEJB implements PostManager {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	@Override
	public boolean postComment(User user, Auction auction, Comment comment, Comment... parent) {
		try {
			comment.setUser(user);
			comment.setAuction(auction);
			
			if(parent.length > 0)
				comment.setParent(parent[0]);
			
			em.persist(comment);
			user.addComment(comment);
			auction.addComment(comment);
			em.merge(user);
			em.merge(comment);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean postAuction(User user, Phone phone) {
		Auction auction = new Auction();
		auction.setBid(phone.getPrice());
		auction.setClosed(false);
		auction.setDate(new Date());
		auction.addPhone(phone);
		user.addAuction(auction);
		user.addPhone(phone);		
		try {
			em.persist(phone);
			em.persist(auction);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean postBid(User user, Auction auction, int bid) {
		auction.setBid(bid);
		if (isParticipant(user, auction)) {
			try {
				em.merge(auction);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			user.addParticipation(auction);
			auction.addParticipant(user);
			try {
				em.merge(auction);
				return true;
			} catch (Exception e) {
				return false;
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
	public boolean postPictures(Phone phone, PhonePicture... pictures) {
		try {
			phone.addPictures(pictures);
			em.merge(phone);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
