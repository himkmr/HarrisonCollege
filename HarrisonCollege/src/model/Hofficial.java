package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the HOFFICIALS database table.
 * 
 */
@Entity
@Table(name="HOFFICIALS")
@NamedQuery(name="Hofficial.findAll", query="SELECT h FROM Hofficial h")
public class Hofficial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OFFICIAL_ID")
	private long officialId;

	@Column(name="OFFICE_NUMBER")
	@SequenceGenerator(name="OFFICE_GENERATOR", sequenceName="HOFFICIALS_SEQ", allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OFFICE_GENERATOR")

	private int officeNumber;

	@Column(name="\"TYPE\"")
	private String type;

	//bi-directional many-to-one association to Hclass
	@OneToMany(mappedBy="hofficial")
	private List<Hclass> hclasses;

	//bi-directional many-to-one association to Hdepartment
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Hdepartment hdepartment;

	//bi-directional one-to-one association to Huser
	@OneToOne
	@PrimaryKeyJoinColumn(name="OFFICIAL_ID")
	private Huser huser;

	public Hofficial() {
	}

	public long getOfficialId() {
		return this.officialId;
	}

	public void setOfficialId(long officialId) {
		this.officialId = officialId;
	}

	public int getOfficeNumber() {
		return this.officeNumber;
	}

	public void setOfficeNumber(int officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Hclass> getHclasses() {
		return this.hclasses;
	}

	public void setHclasses(List<Hclass> hclasses) {
		this.hclasses = hclasses;
	}

	public Hclass addHclass(Hclass hclass) {
		getHclasses().add(hclass);
		hclass.setHofficial(this);

		return hclass;
	}

	public Hclass removeHclass(Hclass hclass) {
		getHclasses().remove(hclass);
		hclass.setHofficial(null);

		return hclass;
	}

	public Hdepartment getHdepartment() {
		return this.hdepartment;
	}

	public void setHdepartment(Hdepartment hdepartment) {
		this.hdepartment = hdepartment;
	}

	public Huser getHuser() {
		return this.huser;
	}

	public void setHuser(Huser huser) {
		this.huser = huser;
	}

}