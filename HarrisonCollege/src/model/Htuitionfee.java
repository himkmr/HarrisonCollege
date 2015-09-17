package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HTUITIONFEES database table.
 * 
 */
@Entity
@Table(name="HTUITIONFEES")
@NamedQuery(name="Htuitionfee.findAll", query="SELECT h FROM Htuitionfee h")
public class Htuitionfee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HTUITIONFEES_ID_GENERATOR", sequenceName="PRIMARY_KEY_GEN_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HTUITIONFEES_ID_GENERATOR")
	private long id;

	@Column(name="PER_CREDIT_TUITION")
	private String perCreditTuition;

	private String semester;

	@Column(name="\"YEAR\"")
	private String year;

	public Htuitionfee() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPerCreditTuition() {
		return this.perCreditTuition;
	}

	public void setPerCreditTuition(String perCreditTuition) {
		this.perCreditTuition = perCreditTuition;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}