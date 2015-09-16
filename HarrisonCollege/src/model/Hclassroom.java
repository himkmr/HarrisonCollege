package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the HCLASSROOMS database table.
 * 
 */
@Entity
@Table(name="HCLASSROOMS")
@NamedQuery(name="Hclassroom.findAll", query="SELECT h FROM Hclassroom h")
public class Hclassroom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CLASSROOM_ID")
	@SequenceGenerator(name="CLASSROOM_GENERATOR", sequenceName="HCLASSROOM_SEQ", allocationSize = 1, initialValue = 100, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLASSROOM_GENERATOR")
	private long classroomId;

	private String building;

	private int capacity;

	private String enabled;

	@Column(name="ROOM_NUMBER")
	private int roomNumber;

	//bi-directional many-to-one association to Hclass
	@OneToMany(mappedBy="hclassroom")
	private List<Hclass> hclasses;

	public Hclassroom() {
	}

	public long getClassroomId() {
		return this.classroomId;
	}

	public void setClassroomId(long classroomId) {
		this.classroomId = classroomId;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<Hclass> getHclasses() {
		return this.hclasses;
	}

	public void setHclasses(List<Hclass> hclasses) {
		this.hclasses = hclasses;
	}

	public Hclass addHclass(Hclass hclass) {
		getHclasses().add(hclass);
		hclass.setHclassroom(this);

		return hclass;
	}

	public Hclass removeHclass(Hclass hclass) {
		getHclasses().remove(hclass);
		hclass.setHclassroom(null);

		return hclass;
	}

}