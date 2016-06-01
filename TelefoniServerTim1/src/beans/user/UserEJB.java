package beans.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import exceptions.UsernameExistsException;
import model.Auction;
import model.User;

@Stateful
public class UserEJB implements UserManager {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	private User user;

	@Override
	public boolean register(User user) throws UsernameExistsException {
		if (login(user.getUsername())) {
			this.user = null;
			throw new UsernameExistsException();
		}

		try {
			em.persist(user);
			this.user = user;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public User get() {
		return user;
	}

	@Override
	public List<Auction> getActiveAuctions() {
		return user.getAuctions().stream().filter(x -> !x.getClosed()).collect(Collectors.toList());
	}

	@Override
	public List<Auction> getClosedAuctions() {
		return user.getAuctions().stream().filter(x -> x.getClosed()).collect(Collectors.toList());
	}

	@Override
	public List<Auction> getActiveParticipations() {
		return user.getParticipations().stream().filter(x -> !x.getClosed()).collect(Collectors.toList());
	}

	@Override
	public List<Auction> getClosedParticipations() {
		return user.getParticipations().stream().filter(x -> x.getClosed()).collect(Collectors.toList());
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
	public void logout() {
	}
}
