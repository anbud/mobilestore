package beans.post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Auction;
import model.Comment;
import model.Phone;
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
			em.merge(user);
			em.merge(auction);
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
		try {			
			em.merge(user);
			em.persist(phone);
			auction = em.merge(auction);
			return auction;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Auction postBid(User user, Auction auction, int bid) {
		auction.setBid(bid);
		if (user == null) {
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
	public Auction getAuction() {
		return em.find(Auction.class, 2);
	}
}
