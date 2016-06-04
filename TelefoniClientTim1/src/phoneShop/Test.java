package phoneShop;

import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.post.PostEJB;
import beans.post.PostManager;
import beans.user.UserEJB;
import beans.user.UserManager;
import exceptions.IncorrectPasswordException;
import exceptions.NotRegisteredException;
import exceptions.UsernameExistsException;
import model.Auction;
import model.Comment;
import model.Phone;
import model.User;

public class Test {

	private static InitialContext context;
	private static final String SERVER_NAME = "TelefoniServerTim1";
	private static final String STATEFUL_LOCATION = "ejb:/" + SERVER_NAME + "//" + UserEJB.class.getSimpleName() + "!"
			+ UserManager.class.getName() + "?stateful";
	private static final String STATELES_LOCATION = "ejb:/" + SERVER_NAME + "//" + PostEJB.class.getSimpleName() + "!"
			+ PostManager.class.getName();
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws NamingException {

		context = new InitialContext();
		UserManager um = (UserManager) context.lookup(STATEFUL_LOCATION);
		PostManager pm = (PostManager) context.lookup(STATELES_LOCATION);		
		
		try {
			insertionTest(um);
		} catch (UsernameExistsException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try {
			loginTest(um);
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (NotRegisteredException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		
		postAuctionTest(pm, um);
		postCommentTest(pm, um);
		postBidTest(um, pm);
		um.logout();
	}

	private static void loginTest(UserManager um) throws IncorrectPasswordException, NotRegisteredException {		
		System.out.println("username: ");
		String username = in.nextLine();
		System.out.println("password: ");
		String password = in.nextLine();
		if (um.login(username, password)) {
			System.out.println(um.getUser().getEMail());
		}
	}

	@SuppressWarnings("unused")
	private static void insertionTest(UserManager um) throws UsernameExistsException {
		User u = new User();
		u.setUsername("gmail-user");
		u.setAboutMe("");
		u.setEMail("user@hotmail.com");
		u.setName("User");
		u.setSurname("User");
		u.setPassword("password");
		u.setAvatar(new byte[0]);		
		um.register(u);		
	}
	
	private static void postCommentTest(PostManager pm, UserManager um) {
		Scanner in = new Scanner(System.in);
		System.out.println("Unesite komentar: ");
		String poruka = in.nextLine();
		Comment c = new Comment();
		c.setText(poruka);	
		
		User u = um.getUser();	
		
		Auction a = pm.getAuction();
						
		pm.postComment(u, a, c);
		
		pm.postComment(u, a, c);
		
		pm.postComment(u, a, c);
		
		c = pm.postComment(u, a, c);
		System.out.println(c.getId());
			
		for (Auction b: u.getAuctions())
			System.out.println("Aukcije " + b.getId() + " " + b.getBid());
		
		for (Comment com: u.getComments())			
			System.out.println("Komentari " + com.getText());
		
		in.close();
	}
	
	private static void postAuctionTest(PostManager pm, UserManager um) {	
		Auction a = new Auction();
		a.setBid(12);					
				
		Phone p = new Phone();
		p.setBluetooth(true);
		
		User u = um.getUser();					
		
		a = pm.postAuction(u, a, p);				
		System.out.println(a.getId());		
		
		for (Auction b: u.getAuctions())
			System.out.println(b.getId());		
	}
	
	private static void postBidTest(UserManager um, PostManager pm) {				
		User u = um.getUser();

		Auction a = pm.getAuction();
		
		if (pm.postBid(u, a, 500) != null)
			System.out.println("Bid = 500");					
		
		if (pm.postBid(null, a, 800) != null)
			System.out.println("Bid = 800");
		
		if (pm.postBid(null, a, 1200) != null)
			System.out.println("Bid = 1200");			
		
		for (Auction c: u.getParticipations())
			System.out.println("Ucesnici " + c.getBid());
		
		for (User uu: a.getParticipants())
			System.out.println("Ucesca " + uu.getEMail());
	}
}
