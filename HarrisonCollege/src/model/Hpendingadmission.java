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
	@Column(name="PENDING_ID")
	private long pendingId;

	@Column(name="\"MESSAGE\"")
	private String message;

	private String permissions;

	//bi-directional many-to-one association to Huser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Huser huser;

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

	public Huser getHuser() {
		return this.huser;
	}

	public void setHuser(Huser huser) {
		this.huser = huser;
	}

}