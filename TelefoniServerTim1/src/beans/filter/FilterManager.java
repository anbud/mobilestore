package beans.filter;

import javax.ejb.Remote;

import model.Auction;
import model.Phone;

@Remote
public interface FilterManager {

	public Auction getAuction(Phone phone);
}
