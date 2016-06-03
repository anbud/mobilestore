package beans.post;

import javax.ejb.Remote;

import model.Auction;
import model.Comment;
import model.Phone;
import model.User;

@Remote
public interface PostManager {

	public Comment postComment(User user, Auction auction, Comment comment);
	
	public Auction postAuction(User user, Auction auction, Phone phone);
	
	public Auction postBid(User user, Auction auction, int bid);
	
	public Auction getAuction();	
}
