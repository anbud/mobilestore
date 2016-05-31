package phoneShop;

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
		User u = new User();
		u.setUsername("user@gmail.com");
		um.save(u);
		
		
		u = um.get("user@gmail.com");
		System.out.println(u.getUsername());
		
	}

}
