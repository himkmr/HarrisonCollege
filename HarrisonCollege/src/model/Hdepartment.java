package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the HDEPARTMENTS database table.
 * 
 */
@Entity
@Table(name="HDEPARTMENTS")
@NamedQuery(name="Hdepartment.findAll", query="SELECT h FROM Hdepartment h")
public class Hdepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEPARTMENT_ID")
	@SequenceGenerator(name="DEPARTMENTS_GENERATOR", sequenceName="HDEPARTMENTS_SEQ", allocationSize = 1, initialValue = 400, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMENTS_GENERATOR")
	private long departmentId;

	private String code;

	private String enabled;

	private String name;

	//bi-directional many-to-one association to Hcours
	@OneToMany(mappedBy="hdepartment")
	private List<Hcours> hcourses;

	//bi-directional many-to-one association to Hmajor
	@OneToMany(mappedBy="hdepartment")
	private List<Hmajor> hmajors;

	//bi-directional many-to-one association to Hofficial
	@OneToMany(mappedBy="hdepartment")
	private List<Hofficial> hofficials;

	public Hdepartment() {
	}

	public long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Hcours> getHcourses() {
		return this.hcourses;
	}

	public void setHcourses(List<Hcours> hcourses) {
		this.hcourses = hcourses;
	}

	public Hcours addHcours(Hcours hcours) {
		getHcourses().add(hcours);
		hcours.setHdepartment(this);

		return hcours;
	}

	public Hcours removeHcours(Hcours hcours) {
		getHcourses().remove(hcours);
		hcours.setHdepartment(null);

		return hcours;
	}

	public List<Hmajor> getHmajors() {
		return this.hmajors;
	}

	public void setHmajors(List<Hmajor> hmajors) {
		this.hmajors = hmajors;
	}

	public Hmajor addHmajor(Hmajor hmajor) {
		getHmajors().add(hmajor);
		hmajor.setHdepartment(this);

		return hmajor;
	}

	public Hmajor removeHmajor(Hmajor hmajor) {
		getHmajors().remove(hmajor);
		hmajor.setHdepartment(null);

		return hmajor;
	}

	public List<Hofficial> getHofficials() {
		return this.hofficials;
	}

	public void setHofficials(List<Hofficial> hofficials) {
		this.hofficials = hofficials;
	}

	public Hofficial addHofficial(Hofficial hofficial) {
		getHofficials().add(hofficial);
		hofficial.setHdepartment(this);

		return hofficial;
	}

	public Hofficial removeHofficial(Hofficial hofficial) {
		getHofficials().remove(hofficial);
		hofficial.setHdepartment(null);

		return hofficial;
	}

}