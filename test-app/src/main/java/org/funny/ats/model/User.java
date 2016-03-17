package org.funny.ats.model;

public class User {

	private int id;
	private int organization_id;
	private String username;
	private String password;
	private String salt;
	private String role_ids;
	private short locked;
	
	
	/*=======================[get/set方法]========================*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRole_ids() {
		return role_ids;
	}
	public void setRole_ids(String role_ids) {
		this.role_ids = role_ids;
	}
	public short getLocked() {
		return locked;
	}
	public void setLocked(short locked) {
		this.locked = locked;
	}
	
	
}
