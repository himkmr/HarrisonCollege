package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the HCOURSES database table.
 * 
 */
@Entity
@Table(name="HCOURSES")
@NamedQuery(name="Hcours.findAll", query="SELECT h FROM Hcours h")
public class Hcours implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COURSE_ID")
	@SequenceGenerator(name="COURSE_GENERATOR", sequenceName="HCOURSES_SEQ", allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COURSE_GENERATOR")

	private long courseId;

	@Column(name="CREDIT_HOURS")
	private int creditHours;

	private String enabled;

	private String subject;

	//bi-directional many-to-one association to Hclass
	@OneToMany(mappedBy="hcours")
	private List<Hclass> hclasses;

	//bi-directional many-to-one association to Hdepartment
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Hdepartment hdepartment;

	public Hcours() {
	}

	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public int getCreditHours() {
		return this.creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Hclass> getHclasses() {
		return this.hclasses;
	}

	public void setHclasses(List<Hclass> hclasses) {
		this.hclasses = hclasses;
	}

	public Hclass addHclass(Hclass hclass) {
		getHclasses().add(hclass);
		hclass.setHcours(this);

		return hclass;
	}

	public Hclass removeHclass(Hclass hclass) {
		getHclasses().remove(hclass);
		hclass.setHcours(null);

		return hclass;
	}

	public Hdepartment getHdepartment() {
		return this.hdepartment;
	}

	public void setHdepartment(Hdepartment hdepartment) {
		this.hdepartment = hdepartment;
	}

}