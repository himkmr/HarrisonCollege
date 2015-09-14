package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the HSTUDENTS database table.
 * 
 */
@Entity
@Table(name="HSTUDENTS")
@NamedQuery(name="Hstudent.findAll", query="SELECT h FROM Hstudent h")
public class Hstudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STUDENT_ID")
	private long studentId;

	@Column(name="ENTRY_YEAR")
	private String entryYear;

	@ManyToOne
	@JoinColumn(name="MAJOR")
	private Hmajor major;

	//bi-directional many-to-one association to Hclassenrollment
	@OneToMany(mappedBy="hstudent")
	private List<Hclassenrollment> hclassenrollments;

	//bi-directional one-to-one association to Huser
	@OneToOne
	@PrimaryKeyJoinColumn(name="STUDENT_ID")
	private Huser huser;

	public Hstudent() {
	}

	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getEntryYear() {
		return this.entryYear;
	}

	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}

	public Hmajor getMajor() {
		return this.major;
	}

	public void setMajor(Hmajor major) {
		this.major = major;
	}

	public List<Hclassenrollment> getHclassenrollments() {
		return this.hclassenrollments;
	}

	public void setHclassenrollments(List<Hclassenrollment> hclassenrollments) {
		this.hclassenrollments = hclassenrollments;
	}

	public Hclassenrollment addHclassenrollment(Hclassenrollment hclassenrollment) {
		getHclassenrollments().add(hclassenrollment);
		hclassenrollment.setHstudent(this);

		return hclassenrollment;
	}

	public Hclassenrollment removeHclassenrollment(Hclassenrollment hclassenrollment) {
		getHclassenrollments().remove(hclassenrollment);
		hclassenrollment.setHstudent(null);

		return hclassenrollment;
	}

	public Huser getHuser() {
		return this.huser;
	}

	public void setHuser(Huser huser) {
		this.huser = huser;
	}

}