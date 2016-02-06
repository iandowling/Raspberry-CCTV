package services;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import domain.Role;

public class CreateUserForm {
	
	@NotEmpty
	private String firstname = "";

    @NotEmpty
    private String surname = "";
    
    @NotEmpty
    private String username = "";
    
	@NotEmpty
	private String email = "";

    @NotEmpty
    private String password = "";
    
    @NotNull
    private Role role = Role.USER;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
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
	
	public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}
