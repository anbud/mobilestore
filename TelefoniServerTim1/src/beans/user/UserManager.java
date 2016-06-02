package beans.user;

import java.util.List;

import javax.ejb.Remote;

import exceptions.IncorrectPasswordException;
import exceptions.NotRegisteredException;
import exceptions.UsernameExistsException;
import model.Auction;
import model.Comment;
import model.User;

@Remote
public interface UserManager {

	public boolean register(User user) throws UsernameExistsException;
	
	public User getUser();
	
	public boolean login(String username, String password) throws IncorrectPasswordException, NotRegisteredException;
	
	public void logout();
	
	public boolean postComment(Comment comment);
	
	public List<Auction> getActiveAuctions();
	
	public List<Auction> getClosedAuctions();
	
	public List<Auction> getActiveParticipations();
	
	public List<Auction> getClosedParticipations();
}
