package models;

public class User {
	private String user;
	private String pass;
	private String role;

	public User(String user, String pass, String role) {
		this.user = user;
		this.pass = pass;
		this.role = role;
	}

	public String getUser() {
		return this.user;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}