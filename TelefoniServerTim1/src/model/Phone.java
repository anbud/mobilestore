package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PhoneTim1")
@NamedQueries(value = { @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p"),
		@NamedQuery(name = "Phone.filter", query = "SELECT p FROM Phone p WHERE p.bluetooth=:b "
				+ "AND p.contractor=:c " + "AND p.description=:d " + "AND p.dualSim=:ds " + "AND p.externalStorage=:es "
				+ "AND p.frontCamera=:fc " + "AND p.internalStorage=:is " + "AND p.name=:n " + "AND p.os=:o "
				+ "AND p.osVersion=:ov " + "AND p.price=:p " + "AND p.primaryCamera=:pc " + "AND p.processor=:p "
				+ "AND p.ram=:r " + "AND p.screenSize=:ss " + "AND p.screenRes=:sr " + "AND p.thickness=:t "
				+ "AND p.weight=:w " + "AND p.wiFi=:wf") })
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PHONE_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "BLUETOOTH")
	private boolean bluetooth;

	@Column(name = "CONTRACTOR")
	private String contractor;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DUAL_SIM")
	private boolean dualSim;

	@Column(name = "EXTERNAL_STORAGE")
	private Integer externalStorage;

	@Column(name = "FRONT_CAMERA")
	private Integer frontCamera;

	@Column(name = "INTERNAL_STORAGE")
	private Integer internalStorage;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OS_NAME")
	private String os;

	@Column(name = "OS_VERSION")
	private String osVersion;

	@Column(name = "PRICE")
	private Integer price;

	@Column(name = "PRIMARY_CAMERA")
	private String primaryCamera;

	@Column(name = "PROCESSOR_NAME")
	private String processor;

	@Column(name = "RAM_SIZE")
	private Integer ram;

	@Column(name = "SCREEN_RES")
	private String screenRes;

	@Column(name = "SCREEN_SIZE")
	private Float screenSize;

	@Column(name = "THICKNESS")
	private Integer thickness;

	@Column(name = "WEIGHT")
	private Integer weight;

	@Column(name = "WI_FI")
	private boolean wiFi;

	@ManyToOne
	@JoinColumn(name = "USER", nullable = false)
	private User user;

	@OneToMany(mappedBy = "phone")
	private Set<PhonePic> pictures;

	public Phone() {
		pictures = new HashSet<PhonePic>();
	}

	public Integer getId() {
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

	public Integer getExternalStorage() {
		return this.externalStorage;
	}

	public void setExternalStorage(Integer externalStorage) {
		this.externalStorage = externalStorage;
	}

	public Integer getFrontCamera() {
		return this.frontCamera;
	}

	public void setFrontCamera(Integer frontCamera) {
		this.frontCamera = frontCamera;
	}

	public Integer getInternalStorage() {
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

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPrimaryCamera() {
		return this.primaryCamera;
	}

	public void setPrimaryCamera(String primaryCamera) {
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

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public String getScreenRes() {
		return this.screenRes;
	}

	public void setScreenRes(String screenRes) {
		this.screenRes = screenRes;
	}

	public Float getScreenSize() {
		return this.screenSize;
	}

	public void setScreenSize(Float screenSize) {
		this.screenSize = screenSize;
	}

	public Integer getThickness() {
		return this.thickness;
	}

	public void setThickness(Integer thicknes) {
		this.thickness = thicknes;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean getWiFi() {
		return this.wiFi;
	}

	public void setWiFi(boolean wiFi) {
		this.wiFi = wiFi;
	}

	public User getUserBean() {
		return this.user;
	}

	public void setUser(User userBean) {
		this.user = userBean;
	}

	public Set<PhonePic> getPictures() {
		return pictures;
	}

	public void setPictures(Set<PhonePic> pictures) {
		this.pictures = pictures;
	}

	public PhonePic addPhonePic(PhonePic pic) {
		getPictures().add(pic);
		pic.setPhone(this);

		return pic;
	}

	public PhonePic removePhonePic(PhonePic pic) {
		getPictures().remove(pic);
		pic.setPhone(null);

		return pic;
	}
}