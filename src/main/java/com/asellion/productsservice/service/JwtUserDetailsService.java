package com.asellion.productsservice.service;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asellion.productsservice.model.ApplicationUser;
import com.asellion.productsservice.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = this.userRepository.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User not found with username: " + username) );

		return user;
	}
	
	public UserDetails loadByUsernameAndPassword(String username, String password) throws CredentialNotFoundException {
		ApplicationUser user = this.userRepository.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User not found with username: " + username) );
		
		if (!this.passwordEncoder.matches(password, user.getPassword())) {
			throw new CredentialNotFoundException();
		}
		
		return user;
	}
	
}