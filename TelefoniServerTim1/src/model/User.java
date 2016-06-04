package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
		@NamedQuery(name = "User.findByEMail", query = "SELECT u FROM User u WHERE u.eMail=:x")})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
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

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Auction> auctions;

	@ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
	private Set<Auction> participations;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Phone> phones;

	public User() {
		auctions = new HashSet<Auction>();
		participations = new HashSet<Auction>();
		comments = new HashSet<Comment>();
		phones = new HashSet<Phone>();
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

	public Set<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<Auction> getAuctions() {
		return this.auctions;
	}

	public Set<Auction> getParticipations() {
		return this.participations;
	}

	public void setParticipations(Set<Auction> participations) {
		this.participations = participations;
	}

	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}

	public Set<Comment> getComments() {		
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
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

		return auction;
	}

	public Auction removeParticipation(Auction auction) {
		getAuctions().remove(auction);

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