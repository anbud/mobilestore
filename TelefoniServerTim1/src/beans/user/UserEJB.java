package beans.user;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

@Stateful
public class UserEJB implements UserManager {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	private User user;
	
	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public User get(String username) {		
		return user;
	}
	
	@Override
	public boolean login(String username) {
		user = em.find(User.class, username);
		if (user == null)
			return false;
		
		return true;
	}
	
	@Override
	@Remove
	public void logout() {}
}
