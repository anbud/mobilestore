package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserTim1")
@NamedQueries(value = { @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByEMail", query = "SELECT u FROM User u WHERE u.eMail=:x"),
		@NamedQuery(name = "User.auctions", query = "SELECT a FROM Auction a WHERE a.user=:u")})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "ABOUT_ME")
	private String aboutMe;

	@Lob
	@Column(name = "AVATAR")
	private byte[] avatar;

	@Column(name = "E_MAIL")
	private String eMail;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "ADDRESS")
	private String address;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Auction> auctions;

	@ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Auction> participations;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Comment> comments;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Phone> phones;

	public User() {
		auctions = new ArrayList<Auction>();
		participations = new ArrayList<Auction>();
		comments = new ArrayList<Comment>();
		phones = new ArrayList<Phone>();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAboutMe() {
		return this.aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public byte[] getAvatar() {
		return this.avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public List<Auction> getParticipations() {
		return this.participations;
	}

	public void setParticipations(List<Auction> participations) {
		this.participations = participations;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Auction addAuction(Auction auction) {
		getAuctions().add(auction);
		auction.setUser(this);

		return auction;
	}

	public Auction removeAuction(Auction auction) {
		getAuctions().remove(auction);
		auction.setUser(null);

		return auction;
	}

	public Auction addParticipation(Auction auction) {
		getAuctions().add(auction);
		auction.addParticipant(this);

		return auction;
	}

	public Auction removeParticipation(Auction auction) {
		getAuctions().remove(auction);
		auction.removeParticipant(this);

		return auction;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public Phone addPhone(Phone phone) {
		getPhones().add(phone);
		phone.setUser(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setUser(null);

		return phone;
	}
}