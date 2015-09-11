package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HCLASSES database table.
 * 
 */
@Entity
@Table(name="HCLASSES")
@NamedQuery(name="Hclass.findAll", query="SELECT h FROM Hclass h")
public class Hclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CLASS_ID")
	private long classId;

	@Column(name="\"DAY\"")
	private String day;

	private String enabled;

	private String endtime;

	private String semester;

	private String starttime;

	@Column(name="\"YEAR\"")
	private String year;

	//bi-directional many-to-one association to Hclassenrollment
	@OneToMany(mappedBy="hclass")
	private List<Hclassenrollment> hclassenrollments;

	//bi-directional many-to-one association to Hclassroom
	@ManyToOne
	@JoinColumn(name="CLASSROOM_ID")
	private Hclassroom hclassroom;

	//bi-directional many-to-one association to Hcours
	@ManyToOne
	@JoinColumn(name="COURSE_ID")
	private Hcours hcours;

	//bi-directional many-to-one association to Hofficial
	@ManyToOne
	@JoinColumn(name="INSTRUCTOR_ID")
	private Hofficial hofficial;

	public Hclass() {
	}

	public long getClassId() {
		return this.classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Hclassenrollment> getHclassenrollments() {
		return this.hclassenrollments;
	}

	public void setHclassenrollments(List<Hclassenrollment> hclassenrollments) {
		this.hclassenrollments = hclassenrollments;
	}

	public Hclassenrollment addHclassenrollment(Hclassenrollment hclassenrollment) {
		getHclassenrollments().add(hclassenrollment);
		hclassenrollment.setHclass(this);

		return hclassenrollment;
	}

	public Hclassenrollment removeHclassenrollment(Hclassenrollment hclassenrollment) {
		getHclassenrollments().remove(hclassenrollment);
		hclassenrollment.setHclass(null);

		return hclassenrollment;
	}

	public Hclassroom getHclassroom() {
		return this.hclassroom;
	}

	public void setHclassroom(Hclassroom hclassroom) {
		this.hclassroom = hclassroom;
	}

	public Hcours getHcours() {
		return this.hcours;
	}

	public void setHcours(Hcours hcours) {
		this.hcours = hcours;
	}

	public Hofficial getHofficial() {
		return this.hofficial;
	}

	public void setHofficial(Hofficial hofficial) {
		this.hofficial = hofficial;
	}

}