package beans.filter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Auction;
import model.Phone;

@Stateless
public class FilterEJB implements FilterManager {

	@PersistenceContext(name="TelefoniServerTim1")
	private EntityManager em;
	
	@Override
	public Auction getAuction(Phone phone) {
		Query q = em.createNamedQuery("Phone.filter");
		q.setParameter("b", phone.getBluetooth());
		q.setParameter("c", phone.getContractor());
		q.setParameter("d", phone.getDescription());
		q.setParameter("ds", phone.getDualSim());
		q.setParameter("es", phone.getExternalStorage());
		q.setParameter("fc", phone.getFrontCamera());
		q.setParameter("is", phone.getInternalStorage());
		q.setParameter("n", phone.getName());
		q.setParameter("o", phone.getOs());
		q.setParameter("ov", phone.getOsVersion());
		q.setParameter("p", phone.getPrice());
		q.setParameter("pc", phone.getPrimaryCamera());
		q.setParameter("p", phone.getProcessor());
		q.setParameter("r", phone.getRam());
		q.setParameter("sr", phone.getScreenRes());
		q.setParameter("ss", phone.getScreenSize());	
		q.setParameter("wf", phone.getWiFi());
		
		return null;
	}
}
