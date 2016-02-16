package services;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateUserForm {
	
    @NotEmpty
    private String username = "";
    
	@NotEmpty
	private String email = "";

    @NotEmpty
    private String password = "";
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
