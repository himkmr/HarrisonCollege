package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the HCLASSENROLLMENTS database table.
 * 
 */
@Entity
@Table(name="HCLASSENROLLMENTS")
@NamedQuery(name="Hclassenrollment.findAll", query="SELECT h FROM Hclassenrollment h")
public class Hclassenrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CLASSENROLLMENTS_ID")
	@SequenceGenerator(name="ENROLLMENT_GENERATOR", sequenceName="HCLASSENROLLMENTS_SEQ", allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENROLLMENT_GENERATOR")
	private long classenrollmentsId;

	private String enrolled;

	private String grade;

	//bi-directional many-to-one association to Hclass
	@ManyToOne
	@JoinColumn(name="CLASS_ID")
	private Hclass hclass;

	//bi-directional many-to-one association to Hstudent
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	private Hstudent hstudent;

	public Hclassenrollment() {
	}

	public long getClassenrollmentsId() {
		return this.classenrollmentsId;
	}

	public void setClassenrollmentsId(long classenrollmentsId) {
		this.classenrollmentsId = classenrollmentsId;
	}

	public String getEnrolled() {
		return this.enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Hclass getHclass() {
		return this.hclass;
	}

	public void setHclass(Hclass hclass) {
		this.hclass = hclass;
	}

	public Hstudent getHstudent() {
		return this.hstudent;
	}

	public void setHstudent(Hstudent hstudent) {
		this.hstudent = hstudent;
	}

}