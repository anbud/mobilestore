package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CommentTim1")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_ID", unique = true, nullable = false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE")
	private Date date;

	@Column(name = "TEXT")
	private String text;

	@ManyToOne
	@JoinColumn(name = "AUCTION_ID", nullable = false)
	private Auction auction;

	@ManyToOne
	@JoinColumn(name = "USER", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "PARENT_ID", nullable = true)
	private Comment parent;
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private Set<Comment> children;

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

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Comment> getChildren() {
		return this.children;
	}
	
	public boolean addChild(Comment c) {
		return children.add(c);
	}
	
	public boolean removeChild(Comment c) {
		return children.remove(c);
	}
	
	public void setParent(Comment c) {
		this.parent = c;
	}
	
	public Comment getParent() {
		return parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
}