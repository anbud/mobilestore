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
import model.Comment;
import model.User;

public class Test {

	private static InitialContext context;
	private static final String SERVER_NAME = "TelefoniServerTim1";
	private static final String STATEFUL_LOCATION = "ejb:/" + SERVER_NAME + "//" + UserEJB.class.getSimpleName() + "!"
			+ UserManager.class.getName() + "?stateful";
	private static final String STATELES_LOCATION = "ejb:/" + SERVER_NAME + "//" + PostEJB.class.getSimpleName() + "!"
			+ PostManager.class.getName();

	public static void main(String[] args) throws NamingException {

		context = new InitialContext();
		UserManager um = (UserManager) context.lookup(STATEFUL_LOCATION);
		PostManager cm = (PostManager) context.lookup(STATELES_LOCATION);
		
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
		postCommentTest(um);
		um.logout();
	}

	private static void loginTest(UserManager um) throws IncorrectPasswordException, NotRegisteredException {
		Scanner in = new Scanner(System.in);
		System.out.println("username: ");
		String username = in.nextLine();
		System.out.println("password: ");
		String password = in.nextLine();
		if (um.login(username, password)) {
			// System.out.println("Ulogovani ste. Pritisnite Enter za logout.");
			// System.out.println("ID aktivne aukcije korisnika " + um.getActiveAuctions().get(0).getId() + ".");
			System.out.println(um.getUser().getName());
		} else
			System.out.println("Morate se registrovati.");
		// in.close();
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

	private static void postCommentTest(UserManager um) {
		Scanner in = new Scanner(System.in);
		System.out.println("Unesite komentar: ");
		String poruka = in.nextLine();
		Comment c = new Comment();
		c.setText(poruka);
		if (um.postComment(c))
			System.out.println("Ostavljeni komentar \"" + um.getUser().getComments().get(0).getText() + "\"");
		in.close();
	}
}
