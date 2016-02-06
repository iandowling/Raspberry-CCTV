package domain;

import domain.Role;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false, unique=true, updatable=false)
	private long id;
	
	@NotNull
	@Size(min = 4, message = "Username must be at least 4 characters long.")
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="firstname", nullable=false)
	private String firstname;
	
	@Column(name="surname", nullable=false)
	private String surname;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@NotNull
	@Size(min=6, message = "Password should be at least 6 characters.")
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
	
	public User(User user) {
		this.id = user.id;
		this.firstname = user.firstname;
		this.surname   = user.surname;
		this.username  = user.username;
		this.email	   = user.email;
		this.password  = user.password;
		this.role	   = user.role;
	}
	
	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
	
    @Override
    public String toString() {
        return "User[" + "id = " + id +"," + "username = " + username + ","
        		+ "email = " + email.replaceFirst("@.*", "@***") + "," +
                "password = " + password.substring(0, 10) + "," +
                "role = " + role +
                ']';
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
