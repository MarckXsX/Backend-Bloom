package com.HospitalBloom.BackendNotas.service.impl;

import com.HospitalBloom.BackendNotas.entities.User;
import com.HospitalBloom.BackendNotas.enums.StatusUser;
import com.HospitalBloom.BackendNotas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUserName(username);

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().toString());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				Collections.singleton(authority));
	}

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found."));
	}

	public User getUserDetails() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByUserName(username);
	}

	public User StatusUpdate(Long id, String statusUser){
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()){
			User updateUser = optionalUser.orElseThrow();

			updateUser.setStatusUser(statusUser.equals(StatusUser.PENDIENTE.getName())? StatusUser.PENDIENTE : StatusUser.APROBADO );

			 return userRepository.save(updateUser);
		}

		return null;
	}

	public List<User> getUsersDetails(){
		return (List<User>) userRepository.findByRole_Id(1);
	}

	public Optional<User> deleteUser(Long id){
		Optional<User> userOptional = userRepository.findById(id);
		userOptional.ifPresent(user -> {
			userRepository.delete(user);
		});
		return userOptional;
	}

	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	public User existsById(Long id){
		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not Found."));
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
