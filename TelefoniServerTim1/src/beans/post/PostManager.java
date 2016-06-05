package beans.post;

import javax.ejb.Remote;

import model.Auction;
import model.Comment;
import model.Phone;
import model.PhonePicture;
import model.User;

@Remote
public interface PostManager {

	public boolean postComment(User user, Auction auction, Comment comment);
	
	public boolean postAuction(User user, Phone phone);
	
	public boolean postBid(User user, Auction auction, int bid);
	
	public boolean postPictures(Phone phone, PhonePicture... pictures);
	
	public boolean postAuctionClosed(Auction auction);
}
