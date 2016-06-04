package beans.filter;

import java.util.List;

import javax.ejb.Remote;

import model.Auction;
import model.Comment;
import model.Phone;

@Remote
public interface FilterManager {

	public List<Auction> findAuctionsByPhone(Phone phone);
	
	public List<Auction> findAuctions();
	
	public List<Comment> findComments(Auction auction);
	
	public Auction getAuction();	
	
	public Phone getPhone();	
}
