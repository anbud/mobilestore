package beans.post;

import javax.ejb.Remote;

import model.Auction;
import model.Comment;
import model.User;

@Remote
public interface PostManager {

	public boolean postComment(Auction auction, User user, Comment comment);
	
	public boolean postAuction(User user, Auction auction);
}
