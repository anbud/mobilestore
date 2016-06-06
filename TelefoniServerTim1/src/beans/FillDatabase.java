package beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Auction;
import model.Comment;
import model.Phone;
import model.PhonePicture;
import model.User;

@LocalBean
@Singleton
@Startup
public class FillDatabase {

	@PersistenceContext(name = "TelefoniServerTim1")
	private EntityManager em;

	@PostConstruct
	@Remove
	public void fill() {
		user();
		anbud();
		cole();
		vsakos();
		ridjis();
	}
	
	private void user() {
		User user = new User();
		Comment comment = new Comment();
		Phone phone = new Phone();
		Auction auction = new Auction();
		PhonePicture phonePic = new PhonePicture();

		user.setAboutMe("");
		user.setEMail("user@gmail.com");
		user.setName("User");
		user.setSurname("User");
		user.setPassword("5f4dcc3b5aa765d61d8327deb882cf99"); //password
		user.setUsername("username");
		user.setAddress("????");
		user.setAvatar(new byte[0]);

		phone.setBluetooth(true);
		phone.setContractor("sim-free");
		phone.setDualSim(false);
		phone.setFrontCamera(5);
		phone.setInternalStorage(64);
		phone.setName("10");
		phone.setOs("Android");
		phone.setOsVersion("v6.0.1");
		phone.setPrice(500);
		phone.setPrimaryCamera(12);
		phone.setProcessor("Snapdragon 820");
		phone.setRam(4);
		phone.setScreenRes("1440x2560");
		phone.setScreenSize(5.2);		
		phone.setWiFi(true);

		auction.setClosed(true);
		auction.setDate(new Date());		

		comment.setDate(new Date());
		comment.setText("Don't bid");
		
		phonePic.setPicture(new byte[0]);
		
		auction.setPhone(phone);
		auction.setUser(user);
		phone.setUser(user);
		phone.setAuction(auction);
		comment.setUser(user);
		comment.setAuction(auction);
		phone.addPictures(phonePic);		

		em.persist(user);
		em.persist(phone);		
		em.persist(auction);
		em.persist(comment);
		//em.persist(phonePic);
	}
	
	private void vsakos() {
		User user = new User();
		Comment comment = new Comment();
		Phone phone = new Phone();
		Auction auction = new Auction();
		PhonePicture phonePic = new PhonePicture();

		user.setAboutMe("");
		user.setEMail("Me@vsakos.com");
		user.setName("Akos");
		user.setSurname("Varga-Somogyi");
		user.setPassword("e10adc3949ba59abbe56e057f20f883e"); //123456
		user.setUsername("vsakos");
		user.setAddress("????");
		user.setAvatar(new byte[0]);

		phone.setName("Xiaomi Redmi 2 Prime");
		phone.setContractor("sim-free");
		phone.setDualSim(true);
		phone.setScreenRes("720x1280");
		phone.setScreenSize(4.7);		
		phone.setOs("Android");
		phone.setOsVersion("v4.4.4 (KitKat)");
		phone.setProcessor("Snapdragon 410");
		phone.setInternalStorage(16);		
		phone.setRam(2);
		phone.setPrimaryCamera(8);
		phone.setFrontCamera(2);
		phone.setWiFi(true);
		phone.setBluetooth(true);		
		phone.setPrice(120);

		auction.setClosed(false);
		auction.setDate(new Date());		

		comment.setDate(new Date());
		comment.setText("Akos's phone.");
		
		phonePic.setPicture(new byte[0]);
		
		auction.setPhone(phone);
		auction.setUser(user);
		phone.setUser(user);
		phone.setAuction(auction);
		comment.setUser(user);
		comment.setAuction(auction);
		phone.addPictures(phonePic);

		em.persist(user);
		em.persist(phone);		
		em.persist(auction);
		em.persist(comment);
	}
	
	private void anbud() {
		User user = new User();
		Comment comment = new Comment();
		Phone phone = new Phone();
		Auction auction = new Auction();
		PhonePicture phonePic = new PhonePicture();

		user.setAboutMe("");
		user.setEMail("andrew@hotmail.rs");
		user.setName("Andrej");
		user.setSurname("Budincevic");
		user.setPassword("7ddf32e17a6ac5ce04a8ecbf782ca509"); //random
		user.setUsername("anbud");
		user.setAddress("????");
		user.setAvatar(new byte[0]);

		phone.setName("LG Nexus 5X");
		phone.setContractor("sim-free");
		phone.setDualSim(false);
		phone.setScreenRes("1080x1920");
		phone.setScreenSize(5.2);		
		phone.setOs("Android");
		phone.setOsVersion("v6.0 (Marshmallow)");
		phone.setProcessor("Snapdragon 808");
		phone.setInternalStorage(32);		
		phone.setRam(2);
		phone.setPrimaryCamera(12.3);
		phone.setFrontCamera(5);
		phone.setWiFi(true);
		phone.setBluetooth(true);		
		phone.setPrice(340);

		auction.setClosed(false);
		auction.setDate(new Date());		

		comment.setDate(new Date());
		comment.setText("Andrej's phone.");
		
		phonePic.setPicture(new byte[0]);
		
		auction.setPhone(phone);
		auction.setUser(user);
		phone.setUser(user);
		phone.setAuction(auction);
		comment.setUser(user);
		comment.setAuction(auction);
		phone.addPictures(phonePic);
		
		em.persist(user);
		em.persist(phone);		
		em.persist(auction);
		em.persist(comment);
	}
	
	private void ridjis() {
		User user = new User();
		Comment comment = new Comment();
		Phone phone = new Phone();
		Auction auction = new Auction();
		PhonePicture phonePic = new PhonePicture();

		user.setAboutMe("");
		user.setEMail("hotmail@ridjis.rs");
		user.setName("Stefan");
		user.setSurname("Ridjosic");
		user.setPassword("912ec803b2ce49e4a541068d495ab570"); //asdf
		user.setUsername("ridjis");
		user.setAddress("????");
		user.setAvatar(new byte[0]);

		phone.setName("Samsung Galaxy S4");
		phone.setContractor("sim-free");
		phone.setDualSim(false);
		phone.setScreenRes("1080x1920");
		phone.setScreenSize(5.0);		
		phone.setOs("Android");
		phone.setOsVersion("v4.2.2 (Jelly Bean)");
		phone.setProcessor("Snapdragon 600");
		phone.setInternalStorage(32);		
		phone.setRam(2);
		phone.setPrimaryCamera(13);
		phone.setFrontCamera(2);
		phone.setWiFi(true);
		phone.setBluetooth(true);		
		phone.setPrice(330);

		auction.setClosed(false);
		auction.setDate(new Date());		

		comment.setDate(new Date());
		comment.setText("Ridjis's phone.");
		
		phonePic.setPicture(new byte[0]);
		
		auction.setPhone(phone);
		auction.setUser(user);
		phone.setUser(user);
		phone.setAuction(auction);
		comment.setUser(user);
		comment.setAuction(auction);
		phone.addPictures(phonePic);

		em.persist(user);
		em.persist(phone);		
		em.persist(auction);
		em.persist(comment);
	}
	
	private void cole() {
		User user = new User();
		Comment comment = new Comment();
		Phone phone = new Phone();
		Auction auction = new Auction();
		PhonePicture phonePic = new PhonePicture();

		user.setAboutMe("");
		user.setEMail("stefan.cocic.c@gmail.com");
		user.setName("Stefan");
		user.setSurname("Cocic");
		user.setPassword("cd584575b6b8e327a88c072ea11141cd"); //cole
		user.setUsername("cole");
		user.setAddress("????");
		user.setAvatar(new byte[0]);

		phone.setName("HTC One (M8)");
		phone.setContractor("sim-free");
		phone.setDualSim(false);
		phone.setScreenRes("1080x1920");
		phone.setScreenSize(5.0);		
		phone.setOs("Android");
		phone.setOsVersion("v4.4.2 (Kit-Kat)");
		phone.setProcessor("Snapdragon 801");
		phone.setInternalStorage(32);		
		phone.setRam(2);
		phone.setPrimaryCamera(4);
		phone.setFrontCamera(5);
		phone.setWiFi(true);
		phone.setBluetooth(true);		
		phone.setPrice(470);

		auction.setClosed(false);
		auction.setDate(new Date());		

		comment.setDate(new Date());
		comment.setText("Cole's phone.");
		
		phonePic.setPicture(new byte[0]);
		
		auction.setPhone(phone);
		auction.setUser(user);
		phone.setUser(user);
		phone.setAuction(auction);
		comment.setUser(user);
		comment.setAuction(auction);
		phone.addPictures(phonePic);

		em.persist(user);
		em.persist(phone);		
		em.persist(auction);
		em.persist(comment);
	}
}
