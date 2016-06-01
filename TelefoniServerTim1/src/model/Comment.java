package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name = "CommentTim1")
@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COM_ID", unique = true, nullable = false)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(nullable = false, length = 250)
	private String text;

	// bi-directional many-to-one association to Auction
	@ManyToOne
	@JoinColumn(name = "BID")
	private Auction auction;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "USER")
	private User user;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User userBean) {
		this.user = userBean;
	}

}