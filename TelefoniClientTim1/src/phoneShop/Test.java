package phoneShop;

import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.user.UserEJB;
import beans.user.UserManager;
import model.User;

public class Test {

	private static InitialContext context;
	private static String SERVER_NAME = "TelefoniServerTim1";
	private static String STATEFUL_LOCATION = "ejb:/" + SERVER_NAME + "//" + UserEJB.class.getSimpleName() 
			+ "!" + UserManager.class.getName() + "?stateful"; 

	public static void main(String[] args) throws NamingException {

		context = new InitialContext();
		UserManager um = (UserManager)context.lookup(STATEFUL_LOCATION);
		loginTest(um);
		
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
	
	private void insertionTest(UserManager um) {
		User u = new User();
		u.setUsername("gmail-user");
		u.setAboutMe("");
		u.setEMail("user@hotmail.com");
		u.setName("User");
		u.setSurname("User");
		u.setPassword("password");		
		u.setAvatar(new byte[0]);
		um.save(u);
		
		
		u = um.get("user@hotmail.com");
		if (u != null)
			System.out.println(u.getUsername());
		else
			System.out.println("User doesn't exists.");
	}

}
