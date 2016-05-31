package beans.user;

import javax.ejb.Remote;

import model.User;

@Remote(value=UserManager.class)
public interface UserManager {

	public void save(User user);
	
	public User get(String username);
}
