package com.example.demo.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
    public CustomUserDetails() {
    	
    }
    
	public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.authorities = authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
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
