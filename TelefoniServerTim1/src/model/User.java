package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="UserTim1")
@NamedQueries(value = { @NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
						@NamedQuery(name="User.findByUsername", query="SELECT u FROM User u WHERE u.username=:x")})
public class User implements Serializable {
	private static final long serialVersionUID = 6213132359807936749L;

	@Id	
	@Column(unique=true, nullable=false, length=50)
	private String username;

	@Column(name="ABOUT_ME", nullable=false, length=250)
	private String aboutMe;

	@Lob
	@Column(nullable=false)
	private byte[] avatar;

	@Column(name="E_MAIL", nullable=false, length=50)
	private String eMail;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=50)
	private String password;

	@Column(nullable=false, length=50)
	private String surname;

	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="user")
	private List<Auction> auctions;

	//bi-directional many-to-many association to Auction
	@ManyToMany(mappedBy="participants")
	private List<Auction> participations;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="user")
	private List<Phone> phones;

	public User() {
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

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
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

	public List<Auction> getParticipations() {
		return this.participations;
	}

	public void setParticipations(List<Auction> auctions2) {
		this.participations = auctions2;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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

	public List<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Phone addPhone(Phone phone) {
		getPhones().add(phone);
		phone.setUserBean(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setUserBean(null);

		return phone;
	}
}