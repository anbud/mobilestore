package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the auction database table.
 * 
 */
@Entity
@Table(name = "AuctionTim1")
@NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a")
public class Auction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUCTION_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "BID")
	private Integer bid;

	@Column(name = "IS_CLOSED")
	private boolean closed;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date date;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "USER")
	private User user;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "ParticipantsTim1", joinColumns = {
			@JoinColumn(name = "AUCTION_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "USER", nullable = false) })
	private List<User> participants;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "auction")
	private List<Comment> comments;

	public Auction() {
		participants = new ArrayList<User>();
		comments = new ArrayList<Comment>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
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