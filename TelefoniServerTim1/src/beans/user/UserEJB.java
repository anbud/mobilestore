package beans.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import exceptions.IncorrectPasswordException;
import exceptions.NotRegisteredException;
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
		this.user = em.find(User.class, user.getUsername());
		if (this.user == null) {
			try {
				em.persist(user);
				this.user = em.merge(user);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			this.user = null;
			throw new UsernameExistsException();
		}
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public boolean login(String username, String password) throws IncorrectPasswordException, NotRegisteredException {
		user = em.find(User.class, username);
		if (user == null)
			throw new NotRegisteredException();

		if (user.getPassword().equals(password))
			return true;

		user = null;
		throw new IncorrectPasswordException();
	}

	@Override
	@Remove
	public void logout() {
		user = null;
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
}
