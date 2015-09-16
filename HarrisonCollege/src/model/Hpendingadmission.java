package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HPENDINGADMISSIONS database table.
 * 
 */
@Entity
@Table(name="HPENDINGADMISSIONS")
@NamedQuery(name="Hpendingadmission.findAll", query="SELECT h FROM Hpendingadmission h")
public class Hpendingadmission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PENDING_GENERATOR", sequenceName="HPENDINGADMISSIONS_SEQ", allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PENDING_GENERATOR")
	@Column(name="PENDING_ID")
	private long pendingId;

	@Column(name="\"MESSAGE\"")
	private String message;
	
	@Column(name="USER_ID")
	private long userid;
	
	@Column(name="PERMISSIONS")
	private String permissions;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public Hpendingadmission() {
	}

	public long getPendingId() {
		return this.pendingId;
	}

	public void setPendingId(long pendingId) {
		this.pendingId = pendingId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}


}