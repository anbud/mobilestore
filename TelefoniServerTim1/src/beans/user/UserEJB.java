package beans.user;

import javax.ejb.Stateful;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

@Stateful
public class UserEJB implements UserManager {

	@PersistenceContext(name="TelefoniServerTim1")
	private EntityManager em;
	
	private User user;
	
	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public User get(String username) {
		Query q = em.createNamedQuery("User.findByUserName");
		q.setParameter("x", username);
		user = (User) q.getSingleResult();
		
		return user;
	}
}
