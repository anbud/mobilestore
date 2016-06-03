package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CommentTim1")
@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_ID", unique = true, nullable = false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date date;

	@Column(name = "TEXT")
	private String text;

	@ManyToOne
	@JoinColumn(name = "AUCTIO_ID", nullable = false)
	private Auction auction;

	@ManyToOne
	@JoinColumn(name = "USER", nullable = false)
	private User user;

	public Comment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public void setUser(User user) {
		this.user = user;
	}
}