package domain;

import org.springframework.security.core.authority.AuthorityUtils;

@SuppressWarnings("serial")
public class CurrentUser extends org.springframework.security.core.userdetails.User {
	
	private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUsername()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }
}
