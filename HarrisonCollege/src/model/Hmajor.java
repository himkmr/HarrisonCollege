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
	private long majorId;

	private String enabled;

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

	public Hdepartment getHdepartment() {
		return this.hdepartment;
	}

	public void setHdepartment(Hdepartment hdepartment) {
		this.hdepartment = hdepartment;
	}

}