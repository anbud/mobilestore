package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "PhonePictureTim1")
public class PhonePicture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;

	@Lob
	@Column(name = "PICTURE", nullable = true)
	private byte[] picture;

	@ManyToOne
	@JoinColumn(name = "PHONE", nullable = false)
	private Phone phone;

	public PhonePicture() {
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
		PhonePicture other = (PhonePicture) obj;
		if (id != other.id)
			return false;
		return true;
	}
}