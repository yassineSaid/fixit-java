package Entities;

public class User {
	private int id;
	private String email,username,firstname,lastname,roles,image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String role) {
		this.roles = role;
	}
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
        

        @Override
        public String toString() {
            return username;
        }
        

	
}
