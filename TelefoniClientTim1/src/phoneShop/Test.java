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
		//postCommentTest(pm, um);
		postAuctionTest(pm, um);
		um.logout();
	}

	private static void loginTest(UserManager um) throws IncorrectPasswordException, NotRegisteredException {		
		System.out.println("username: ");
		String username = in.nextLine();
		System.out.println("password: ");
		String password = in.nextLine();
		if (um.login(username, password)) {
			System.out.println(um.getUser().getName());
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

		Auction a = new Auction();
		a.setBid(250);
		
		User u = um.getUser();	
		u.addAuction(a);
		u.addComment(c);		
		
		pm.postComment(a, u, c);
		
		System.out.println(u.getAuctions().size());
		for (Auction b: u.getAuctions())
			System.out.println(b.getId() + " " + b.getBid());
		
		for (Comment com: u.getComments())			
			System.out.println(com.getText());
		
		in.close();
	}
	
	private static void postAuctionTest(PostManager pm, UserManager um) {	
		Auction a = new Auction();
		a.setId(199);
		
		pm.postAuction(um.getUser(), a);

		for (Auction b: um.getUser().getAuctions())
			System.out.println(b.getId());		
	}
}
