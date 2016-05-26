package com.rcctv.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rcctv.entities.User;

/*
 * create the attributes of the reset password form. 
 */
public class ResetPasswordForm {
	
	@NotNull
	@Size(min=1, max=User.PASSWORD_MAX, message="{passwordSizeError}")
	private String password = "";
	
	@NotNull
	@Size(min=1, max=User.PASSWORD_MAX, message="{passwordSizeError}")
	private String retypePassword = "";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}		

}
