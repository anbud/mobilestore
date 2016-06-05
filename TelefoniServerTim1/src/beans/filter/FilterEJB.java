package beans.filter;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Auction;
import model.Comment;
import model.Phone;

@Stateless
@Remote(FilterManager.class)
public class FilterEJB implements FilterManager {

	@PersistenceContext(name="TelefoniServerTim1")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Auction> findAuctionsByPhone(Phone phone) {		
		Query q = em.createNamedQuery("Phone.filter");
		q.setParameter("b", phone.getBluetooth());
		q.setParameter("c", phone.getContractor());
		q.setParameter("d", phone.getDescription());
		q.setParameter("ds", phone.getDualSim());		
		q.setParameter("fc", phone.getFrontCamera());
		q.setParameter("is", phone.getInternalStorage());
		q.setParameter("n", phone.getName());
		q.setParameter("o", phone.getOs());
		q.setParameter("ov", phone.getOsVersion());
		q.setParameter("p", phone.getPrice());
		q.setParameter("pc", phone.getPrimaryCamera());
		q.setParameter("pr", phone.getProcessor());
		q.setParameter("r", phone.getRam());
		q.setParameter("sr", phone.getScreenRes());
		q.setParameter("ss", phone.getScreenSize());	
		q.setParameter("wf", phone.getWiFi());
		
		phone = (Phone)q.getSingleResult();
		
		q = em.createNamedQuery("Auction.filter");
		q.setParameter("p", phone);
		
		return q.getResultList();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findComments(Auction auction) {
		Query q = em.createNamedQuery("Comment.filter");
		q.setParameter("a", auction);
		
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auction> findAuctions() {
		return em.createNamedQuery("Auction.findAll").getResultList();		
	}
	
	@Override
	public Auction getAuction() {
		return em.find(Auction.class, 1);
	}
	
	@Override
	public Phone getPhone() {	
		return em.find(Phone.class, 1);
	}
}
