package beans.filter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Auction;
import model.Comment;
import model.Phone;
import model.PhonePicture;

@Stateless
@Remote(FilterManager.class)
public class FilterEJB implements FilterManager {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Auction> findAuctionsByPhone(Phone phone) {
		List<String> parameters = new ArrayList<String>();
		StringBuilder query = new StringBuilder("SELECT a FROM Auction a WHERE");
		if (!phone.getName().equals("")) {
			query.append(" a.phone.name=:n !");
			parameters.add("n");
		}

		if (!phone.getOsVersion().equals("")) {
			query.append(" a.phone.osVersion=:ov !");
			parameters.add("ov");
		}

		if (!phone.getProcessor().equals("")) {
			query.append(" a.phone.processor=:pr !");
			parameters.add("pr");
		}

		if ((phone.getRam() != 0) || (phone.getRam1() != 0)) {
			query.append(" (a.phone.ram BETWEEN :r AND :r1) !");
			parameters.add("r");
			parameters.add("r1");
		}

		if ((phone.getInternalStorage() != 0) || (phone.getInternalStorage1() != 0)) {
			query.append(" (a.phone.internalStorage BETWEEN :is AND :is1) !");
			parameters.add("is");
			parameters.add("is1");
		}

		if (!phone.getScreenRes().equals("")) {
			query.append(" a.phone.screenRes=:sr !");
			parameters.add("sr");
		}

		if ((phone.getScreenSize() != 0) || (phone.getScreenSize1() != 0)) {
			query.append(" (a.phone.screenSize BETWEEN :ss AND :ss1) !");
			parameters.add("ss");
			parameters.add("ss1");
		}

		if ((phone.getPrimaryCamera() != 0) || (phone.getPrimaryCamera1() != 0)) {
			query.append(" (a.phone.primaryCamera BETWEEN :pc AND :pc1) !");
			parameters.add("pc");
			parameters.add("pc1");
		}

		if (!phone.getContractor().equals("")) {
			query.append(" a.phone.contractor=:c !");
			parameters.add("c");
		}

		if ((phone.getPrice() != 0) || (phone.getPrice1() != 0)) {
			query.append(" (a.phone.price BETWEEN :p AND :p1)");
			parameters.add("p");
			parameters.add("p1");
		}

		String qu = query.toString();
		
		if (parameters.size() == 0) {
			qu = qu.replace(" WHERE", "");
		} else {
			qu = qu.replaceAll("!", "AND");
			qu = qu.substring(0, qu.length() - "AND".length()).trim();
		}

		Query q = em.createQuery(qu);
		for (String param : parameters) {
			if (param.equals("n"))
				q.setParameter("n", phone.getName());
			else if (param.equals("ov"))
				q.setParameter("ov", phone.getOsVersion());
			else if (param.equals("pr"))
				q.setParameter("pr", phone.getProcessor());
			else if (param.equals("r"))
				q.setParameter("r", phone.getRam());
			else if (param.equals("r1"))
				q.setParameter("r1", phone.getRam1());
			else if (param.equals("is"))
				q.setParameter("is", phone.getInternalStorage());
			else if (param.equals("is1"))
				q.setParameter("is1", phone.getInternalStorage1());
			else if (param.equals("sr"))
				q.setParameter("sr", phone.getScreenRes());
			else if (param.equals("ss"))
				q.setParameter("ss", phone.getScreenSize());
			else if (param.equals("ss1"))
				q.setParameter("ss1", phone.getScreenSize1());
			else if (param.equals("pc"))
				q.setParameter("pc", phone.getPrimaryCamera());
			else if (param.equals("pc1"))
				q.setParameter("pc1", phone.getPrimaryCamera1());
			else if (param.equals("c"))
				q.setParameter("c", phone.getContractor());
			else if (param.equals("p"))
				q.setParameter("p", phone.getPrice());
			else if (param.equals("p1"))
				q.setParameter("p1", phone.getPrice1());
		}

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findComments(Auction auction) {
		Query q = em.createNamedQuery("SELECT c FROM Comment c WHERE c.auction=:a");
		q.setParameter("a", auction);

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auction> findAuctions() {
		return em.createNamedQuery("Auction.findAll").getResultList();
	}

	@Override
	public List<PhonePicture> findPicturesByPhone(Phone phone) {
		// TODO Auto-generated method stub
		return null;
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
