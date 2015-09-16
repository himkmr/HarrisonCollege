package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the HMAJORS database table.
 * 
 */
@Entity
@Table(name="HMAJORS")
@NamedQuery(name="Hmajor.findAll", query="SELECT h FROM Hmajor h")
public class Hmajor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MAJOR_ID")
	@SequenceGenerator(name="MAJORS_GENERATOR", sequenceName="HMAJORS_SEQ", allocationSize = 1, initialValue = 400, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAJORS_GENERATOR")
	private long majorId;

	private String enabled;

	private String name;

	//bi-directional many-to-one association to Hdepartment
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Hdepartment hdepartment;

	public Hmajor() {
	}

	public long getMajorId() {
		return this.majorId;
	}

	public void setMajorId(long majorId) {
		this.majorId = majorId;
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

	public Hdepartment getHdepartment() {
		return this.hdepartment;
	}

	public void setHdepartment(Hdepartment hdepartment) {
		this.hdepartment = hdepartment;
	}

}