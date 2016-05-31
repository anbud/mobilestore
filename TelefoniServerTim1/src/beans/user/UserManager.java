package beans.user;

import javax.ejb.Remote;

import model.User;

@Remote
public interface UserManager {

	public void save(User user);
	
	public User get(String username);
}
