package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "PhonePicTim1")
public class PhonePic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "PICTURE", nullable = true)
	private byte[] picture;

	@ManyToOne
	@JoinColumn(name = "PHONE", nullable = false)
	private Phone phone;

	public PhonePic() {
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
}