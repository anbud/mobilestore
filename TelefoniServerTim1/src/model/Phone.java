package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PhoneTim1")
@NamedQueries(value = { @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p"),
		@NamedQuery(name = "Phone.filter", query = "SELECT p FROM Phone p WHERE p.bluetooth=:b "
				+ "AND p.contractor=:c AND p.description=:d AND p.dualSim=:ds "
				+ "AND p.frontCamera=:fc AND p.internalStorage=:is AND p.name=:n AND p.os=:o "
				+ "AND p.osVersion=:ov AND p.price=:p AND p.primaryCamera=:pc AND p.processor=:pr "
				+ "AND p.ram=:r AND p.screenRes=:sr AND p.screenSize=:ss AND p.wiFi=:wf") })
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PHONE_ID", unique = true, nullable = false)
	private int id;

	@Column(name = "BLUETOOTH")
	private boolean bluetooth;

	@Column(name = "CONTRACTOR")
	private String contractor;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DUAL_SIM")
	private boolean dualSim;

	@Column(name = "FRONT_CAMERA")
	private double frontCamera;

	@Column(name = "INTERNAL_STORAGE")
	private int internalStorage;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OS_NAME")
	private String os;

	@Column(name = "OS_VERSION")
	private String osVersion;

	@Column(name = "PRICE")
	private int price;

	@Column(name = "PRIMARY_CAMERA")
	private double primaryCamera;

	@Column(name = "PROCESSOR_NAME")
	private String processor;

	@Column(name = "RAM_SIZE")
	private int ram;

	@Column(name = "SCREEN_RES")
	private String screenRes;

	@Column(name = "SCREEN_SIZE")
	private double screenSize;

	@Column(name = "WI_FI")
	private boolean wiFi;
	
	@ManyToOne
	@JoinColumn(name = "USER", nullable = false)
	private User user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUCTION")
	private Auction auction;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "phone", cascade = CascadeType.ALL)
	private Set<PhonePicture> pictures;

	public Phone() {
		pictures = new HashSet<PhonePicture>();
		contractor = "";
		description = "";
		name = "";
		os = "";
		osVersion = "";
		processor = "";
		screenRes = "";		
		bluetooth = false;
		dualSim = false;		
		wiFi = false;
		frontCamera = 0.0;
		internalStorage = 0;
		price = 0;
		primaryCamera = 0.0;		
		ram = 0;		
		screenSize = 0.0;		
	}
	
	

	public Phone(String name, String os, String osVersion, String processor, int ram, double primaryCamera,
				double frontCamera, int internalStorage, String screenRes, double screenSize, boolean wiFi,
				boolean bluetooth, boolean dualSim, String contractor, int price, String description) {
		super();
		
		this.name = name;
		this.os = os;
		this.osVersion = osVersion;
		this.processor = processor;
		this.ram = ram;
		this.primaryCamera = primaryCamera;
		this.frontCamera = frontCamera;
		this.internalStorage = internalStorage;
		this.screenRes = screenRes;
		this.screenSize = screenSize;
		this.wiFi = wiFi;
		this.bluetooth = bluetooth;
		this.dualSim = dualSim;
		this.contractor = contractor;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getBluetooth() {
		return this.bluetooth;
	}

	public void setBluetooth(boolean bluetooth) {
		this.bluetooth = bluetooth;
	}

	public String getContractor() {
		return this.contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getDualSim() {
		return this.dualSim;
	}

	public void setDualSim(boolean dualSim) {
		this.dualSim = dualSim;
	}

	public double getFrontCamera() {
		return this.frontCamera;
	}

	public void setFrontCamera(double frontCamera) {
		this.frontCamera = frontCamera;
	}

	public int getInternalStorage() {
		return this.internalStorage;
	}

	public void setInternalStorage(Integer internalStorage) {
		this.internalStorage = internalStorage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getPrimaryCamera() {
		return this.primaryCamera;
	}

	public void setPrimaryCamera(double primaryCamera) {
		this.primaryCamera = primaryCamera;
	}

	public String getProcessor() {
		return this.processor;
	}

	public void setProcessor(String processore) {
		this.processor = processore;
	}

	public int getRam() {
		return this.ram;
	}
	
	public String getRamString() {
		if(this.ram >= 1024)
			return (this.ram/1024) + " GB";
		else
			return this.ram + " MB";
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public String getScreenRes() {
		return this.screenRes;
	}

	public void setScreenRes(String screenRes) {
		this.screenRes = screenRes;
	}

	public Double getScreenSize() {
		return this.screenSize;
	}

	public void setScreenSize(Double screenSize) {
		this.screenSize = screenSize;
	}

	public boolean getWiFi() {
		return this.wiFi;
	}

	public void setWiFi(boolean wiFi) {
		this.wiFi = wiFi;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}	

	public Set<PhonePicture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<PhonePicture> pictures) {
		this.pictures = pictures;
	}

	public PhonePicture[] addPictures(PhonePicture... pictures) {
		for (PhonePicture picture : pictures) {
			getPictures().add(picture);
			picture.setPhone(this);
		}

		return pictures;
	}

	public PhonePicture[] removePictures(PhonePicture... pictures) {
		for (PhonePicture picture : pictures) {
			getPictures().remove(picture);
			picture.setPhone(null);
		}

		return pictures;
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
		Phone other = (Phone) obj;
		if (id != other.id)
			return false;
		return true;
	}
}