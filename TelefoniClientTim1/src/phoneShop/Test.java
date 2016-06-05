package phoneShop;

import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.filter.FilterEJB;
import beans.filter.FilterManager;
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
import model.PhonePicture;
import model.User;

public class Test {

	private static InitialContext context;
	private static final String SERVER_NAME = "TelefoniServerTim1";
	private static final String UserManager_LOCATION = "ejb:/" + SERVER_NAME + "//" + UserEJB.class.getSimpleName()
			+ "!" + UserManager.class.getName() + "?stateful";
	private static final String PostManager_LOCATION = "ejb:/" + SERVER_NAME + "//" + PostEJB.class.getSimpleName()
			+ "!" + PostManager.class.getName();
	private static final String FilterManager_LOCATION = "ejb:/" + SERVER_NAME + "//" + FilterEJB.class.getSimpleName()
			+ "!" + FilterManager.class.getName();
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws NamingException {

		context = new InitialContext();
		UserManager um = (UserManager) context.lookup(UserManager_LOCATION);
		PostManager pm = (PostManager) context.lookup(PostManager_LOCATION);
		FilterManager fm = (FilterManager) context.lookup(FilterManager_LOCATION);

		//postTest(um, pm, fm);
		findAuctionsByPhoneTest(fm);
		//findCommentsTest(fm);
		//findAuctionsTest(fm);
		//postPicturesTest(pm, fm);
		um.logout();
	}

	private static void postTest(UserManager um, PostManager pm, FilterManager fm) {
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
		postCommentTest(pm, um, fm);	
		postBidTest(um, pm, fm);		
	}

	private static void postCommentTest(PostManager pm, UserManager um, FilterManager fm) {
		Scanner in = new Scanner(System.in);
		System.out.println("Unesite komentar: ");
		String poruka = in.nextLine();
		
		Comment c = new Comment();
		c.setText(poruka);

		Auction a = fm.getAuction();

		pm.postComment(um.getUser(), a, c);

		//pm.postComment(u, a, c);

		//pm.postComment(u, a, c);

		//pm.postComment(u, a, c);

		/*for (Auction b : u.getAuctions())
			System.out.println("POST COMMENT - ID aukcije: " + b.getId());*/

		for (Comment com : um.getUser().getComments())
			System.out.println("POST COMMENT: " + com.getText());

		in.close();
	}

	private static void postAuctionTest(PostManager pm, UserManager um) {
		//Auction a = new Auction();		

		Phone p = new Phone();		

		pm.postAuction(um.getUser(), p, 400);

		for (Auction b : um.getActiveAuctions())
			System.out.println("POST AUCTION: " + b.getId());

		//pm.postAuctionClosed(a);
	}

	private static void postBidTest(UserManager um, PostManager pm, FilterManager fm) {		
		Auction a = fm.getAuction();

		if (pm.postBid(um.getUser(), a, 500))
			System.out.println("Bid = 500");

		if (pm.postBid(um.getUser(), a, 800))
			System.out.println("Bid = 800");

		if (pm.postBid(um.getUser(), a, 1200))
			System.out.println("Bid = 1200");

		for (Auction c : um.getActiveParticipations())
			System.out.println("POST BID AUCTIONS " + c.getId());

		for (User uu : a.getParticipants())
			System.out.println("POST BID USERS " + uu.getUsername());
	}

	public static void findAuctionsByPhoneTest(FilterManager fm) {
		Phone p = new Phone();
		p.setBluetooth(true);
		List<Auction> auctions = fm.findAuctionsByPhone(p);
		for (Auction a : auctions) {
			System.out.println("Aukcije za trazeni fon " + a.getId());
		}
	}

	public static void findCommentsTest(FilterManager fm) {
		List<Comment> comments = fm.findComments(fm.getAuction());
		for (Comment c : comments) {
			System.out.println("Komentari za aukciju " + c.getText());
		}
	}

	public static void findAuctionsTest(FilterManager fm) {
		List<Auction> auctions = fm.findAuctions();
		for (Auction a : auctions) {
			System.out.println("Aukcije " + a.getId());
		}
	}
	
	public static void postPicturesTest(PostManager pm, FilterManager fm) {
		pm.postPictures(fm.getPhone(), new PhonePicture(), new PhonePicture());		
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
}
