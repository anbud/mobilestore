package beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Auction;
import model.Comment;
import model.Phone;
import model.User;

@LocalBean
@Singleton
@Startup
public class FillDatabase {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	@PostConstruct
	public void fill() {

		User user = new User();
		Comment comment = new Comment();
		Phone phone = new Phone();
		Auction auction = new Auction();

		user.setAboutMe("");
		user.setEMail("user@gmail.com");
		user.setName("User");
		user.setSurname("User");
		user.setPassword("password");
		user.setUsername("username");
		user.setAvatar(new byte[0]);

		phone.setBluetooth(true);
		phone.setContractor("sim-free");
		phone.setDescription(null);
		phone.setDualSim(false);
		phone.setExternalStorage("No external storage");
		phone.setFrontCamera("5MP");
		phone.setGps(true);
		phone.setInternalStorage("64GB");
		phone.setManufacturer("HTC");
		phone.setName("10");
		phone.setNumCores("2");
		phone.setOs("Android");
		phone.setOsVersion("v6.0.1");
		phone.setPrice(500);
		phone.setPrimaryCamera("12MP");
		phone.setProcessore("Snapdragon 820");
		phone.setRam("4GB");
		phone.setScreenRes("1440x2560");
		phone.setScreenSize("5.2\"");
		phone.setThicknes(9);
		phone.setWeight(161);
		phone.setWiFi(true);

		auction.setClosed(false);
		auction.setDate(new Date());

		comment.setDate(new Date());
		comment.setText("Don't bid");

		comment.setAuction(auction);
		user.addPhone(phone);
		auction.setUser(user);
		auction.addComment(comment);

		em.persist(phone);
		em.persist(user);
		em.persist(auction);
		em.persist(comment);
	}
}
