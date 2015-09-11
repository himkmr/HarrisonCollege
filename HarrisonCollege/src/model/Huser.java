package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HUSERS database table.
 * 
 */
@Entity
@Table(name="HUSERS")
@NamedQuery(name="Huser.findAll", query="SELECT h FROM Huser h")
public class Huser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private long userId;

	private String name;

	private String password;

	private String permissions;

	//bi-directional one-to-one association to Hofficial
	@OneToOne(mappedBy="huser")
	private Hofficial hofficial;

	//bi-directional many-to-one association to Hpendingadmission
	@OneToMany(mappedBy="huser")
	private List<Hpendingadmission> hpendingadmissions;

	//bi-directional one-to-one association to Hstudent
	@OneToOne(mappedBy="huser")
	private Hstudent hstudent;

	public Huser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Hofficial getHofficial() {
		return this.hofficial;
	}

	public void setHofficial(Hofficial hofficial) {
		this.hofficial = hofficial;
	}

	public List<Hpendingadmission> getHpendingadmissions() {
		return this.hpendingadmissions;
	}

	public void setHpendingadmissions(List<Hpendingadmission> hpendingadmissions) {
		this.hpendingadmissions = hpendingadmissions;
	}

	public Hpendingadmission addHpendingadmission(Hpendingadmission hpendingadmission) {
		getHpendingadmissions().add(hpendingadmission);
		hpendingadmission.setHuser(this);

		return hpendingadmission;
	}

	public Hpendingadmission removeHpendingadmission(Hpendingadmission hpendingadmission) {
		getHpendingadmissions().remove(hpendingadmission);
		hpendingadmission.setHuser(null);

		return hpendingadmission;
	}

	public Hstudent getHstudent() {
		return this.hstudent;
	}

	public void setHstudent(Hstudent hstudent) {
		this.hstudent = hstudent;
	}

}