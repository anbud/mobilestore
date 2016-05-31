package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the phone database table.
 * 
 */
@Entity
@Table(name="PhoneTim1")
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = -1385499388080325670L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PHONE_ID", unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private boolean bluetooth;

	@Column(nullable=false, length=20)
	private String contractor;

	@Column(nullable=true, length=250)
	private String description;

	@Column(name="DUAL_SIM", nullable=false)
	private boolean dualSim;

	@Column(name="EXTERNAL_STORAGE", length=10)
	private String externalStorage;

	@Column(name="FRONT_CAMERA", nullable=false, length=10)
	private String frontCamera;

	@Column(nullable=false)
	private boolean gps;

	@Column(name="INTERNAL_STORAGE", nullable=false, length=10)
	private String internalStorage;

	@Column(nullable=false, length=50)
	private String manufacturer;

	@Column(nullable=false, length=50)
	private String name;

	@Column(name="NUM_CORES", nullable=false, length=10)
	private String numCores;

	@Column(nullable=false, length=20)
	private String os;

	@Column(name="OS_VERSION", nullable=false, length=25)
	private String osVersion;

	@Column(nullable=false)
	private int price;

	@Column(name="PRIMARY_CAMERA", nullable=false, length=10)
	private String primaryCamera;

	@Column(nullable=false, length=30)
	private String processore;

	@Column(nullable=false, length=10)
	private String ram;

	@Column(name="SCREEN_RES", nullable=false, length=20)
	private String screenRes;

	@Column(name="SCREEN_SIZE", nullable=false, length=5)
	private String screenSize;

	@Column(nullable=false)
	private int thicknes;

	@Column(nullable=false)
	private int weight;

	@Column(name="WI_FI", nullable=false)
	private boolean wiFi;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER")
	private User user;

	public Phone() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getExternalStorage() {
		return this.externalStorage;
	}

	public void setExternalStorage(String externalStorage) {
		this.externalStorage = externalStorage;
	}

	public String getFrontCamera() {
		return this.frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public boolean getGps() {
		return this.gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public String getInternalStorage() {
		return this.internalStorage;
	}

	public void setInternalStorage(String internalStorage) {
		this.internalStorage = internalStorage;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumCores() {
		return this.numCores;
	}

	public void setNumCores(String numCores) {
		this.numCores = numCores;
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

	public String getPrimaryCamera() {
		return this.primaryCamera;
	}

	public void setPrimaryCamera(String primaryCamera) {
		this.primaryCamera = primaryCamera;
	}

	public String getProcessore() {
		return this.processore;
	}

	public void setProcessore(String processore) {
		this.processore = processore;
	}

	public String getRam() {
		return this.ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getScreenRes() {
		return this.screenRes;
	}

	public void setScreenRes(String screenRes) {
		this.screenRes = screenRes;
	}

	public String getScreenSize() {
		return this.screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public int getThicknes() {
		return this.thicknes;
	}

	public void setThicknes(int thicknes) {
		this.thicknes = thicknes;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
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

	public void setUserBean(User userBean) {
		this.user = userBean;
	}

}