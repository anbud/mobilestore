package phoneShop;

import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.user.UserEJB;
import beans.user.UserManager;
import exceptions.UsernameExistsException;
import model.User;

public class Test {

	private static InitialContext context;
	private static String SERVER_NAME = "TelefoniServerTim1";
	private static String STATEFUL_LOCATION = "ejb:/" + SERVER_NAME + "//" + UserEJB.class.getSimpleName() 
			+ "!" + UserManager.class.getName() + "?stateful"; 

	public static void main(String[] args) throws NamingException {

		context = new InitialContext();
		UserManager um = (UserManager)context.lookup(STATEFUL_LOCATION);
		try {
			insertionTest(um);
			loginTest(um);
		} catch (UsernameExistsException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private static void loginTest(UserManager um) {
		System.out.println("username: ");
		Scanner in = new Scanner(System.in);
		String username = in.nextLine();
		if (um.login(username)) {
			System.out.println("Ulogovani ste. Pritisnite Enter za logout.");
			in.nextLine();
			um.logout();
		} else
			System.out.println("Morate se registrovati.");
		in.close();
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
		
		
		u = um.get();
		if (u != null)
			System.out.println(u.getUsername());
		else
			System.out.println("User doesn't exists.");
	}

}
