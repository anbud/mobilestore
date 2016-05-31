package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the auction database table.
 * 
 */
@Entity
@Table(name="AuctionTim1")
@NamedQuery(name="Auction.findAll", query="SELECT a FROM Auction a")
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AUCTION_ID", unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int bid;

	@Column(nullable=false)
	private boolean closed;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER")
	private User user;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="ParticipantsTim1"
		, joinColumns={
			@JoinColumn(name="AUCTION_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="USER", nullable=false)
			}
		)
	private List<User> participants;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="auction")
	private List<Comment> comments;

	public Auction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBid() {
		return this.bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public boolean getClosed() {
		return this.closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User userBean) {
		this.user = userBean;
	}

	public List<User> getParticipants() {
		return this.participants;
	}

	public void setParticipants(List<User> users1) {
		this.participants = users1;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setAuction(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setAuction(null);

		return comment;
	}
}