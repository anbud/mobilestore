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
/*		Query q = em.createNamedQuery("Phone.filter1");
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
		q.setParameter("wf", phone.getWiFi());*/
		
		StringBuilder query = new StringBuilder("SELECT p FROM Phone WHERE ");
		if (!phone.getName().equals("")) {
			query.append("p.name=:n AND ");
		}
		
		if (!phone.getOsVersion().equals("")) {
			query.append("p.osVersion=:ov AND ");
		}
		
		if (!phone.getProcessor().equals("")) {
			query.append("p.processor=:p AND ");
		}
		
		if ((phone.getRam() != 0) || (phone.getRam1() != 0)) {
			query.append("(p.ram BETWEEN :r AND :r1) AND ");
		}
		
		if ((phone.getInternalStorage() != 0) || (phone.getInternalStorage1() != 0)) {
			query.append("(p.internalStorage BETWEEN :is AND :is1) AND ");
		}
		
		if (!phone.getScreenRes().equals("")) {
			query.append("p.screenRes=:sr AND ");
		}
		
		if ((phone.getScreenSize() != 0) || (phone.getScreenSize1() != 0)) {
			query.append("(p.internalStorage BETWEEN :is AND :is1) AND ");
		}
		
		Query q = em.createNamedQuery("Phone.filter1");
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
