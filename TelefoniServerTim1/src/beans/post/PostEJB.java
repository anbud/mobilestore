package beans.post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Auction;
import model.Comment;
import model.User;

@Stateless
public class PostEJB implements PostManager {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	@Override
	public boolean postComment(Auction auction, User user, Comment comment) {
		user.addComment(comment);
		auction.addComment(comment);	
		try {
			em.merge(user);
			em.merge(auction);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean postAuction(User user, Auction auction) {
		user.addAuction(auction);
		try {
			em.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
}
