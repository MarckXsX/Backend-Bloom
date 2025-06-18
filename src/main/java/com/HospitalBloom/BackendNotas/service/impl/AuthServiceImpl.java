package com.HospitalBloom.BackendNotas.service.impl;

import com.HospitalBloom.BackendNotas.dto.NewUserDto;
import com.HospitalBloom.BackendNotas.entities.Role;
import com.HospitalBloom.BackendNotas.entities.User;
import com.HospitalBloom.BackendNotas.enums.RoleList;
import com.HospitalBloom.BackendNotas.enums.StatusUser;
import com.HospitalBloom.BackendNotas.jwt.JwtUtil;
import com.HospitalBloom.BackendNotas.repositories.RoleRepository;
import com.HospitalBloom.BackendNotas.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	/*
	 * @Autowired private AuthenticationManagerBuilder authenticationManagerBuilder;
	 */

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CookieServiceImpl cookieService;

	@Override
	public String authenticate(String username, String password, HttpServletResponse response) {

		User user = userService.findByUserName(username);

		if(user.getStatusUser().equals(StatusUser.APROBADO)){

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);
			Authentication authResult = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authResult);

			String jwt = jwtUtil.generateToken(authResult);
			cookieService.addHttpOnlyCookie("jwt", jwt, 7 * 24 * 60 * 60, response);

			return user.getRole().getName().toString();
		}else {
			logout(response);
			return null;
		}
	}

	@Override
	public void registerUser(NewUserDto newUserDto) {
		if (userService.existsByUserName(newUserDto.getUserName())) {
			throw new IllegalArgumentException("El nombre de usuario ya existe");
		}

		Role roleUser = roleRepository.findByName(RoleList.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Rol no encontrado"));
		User user = new User(newUserDto.getUserName(), passwordEncoder.encode(newUserDto.getPassword()), roleUser, StatusUser.PENDIENTE);
		userService.save(user);
	}

	@Override
	public void logout(HttpServletResponse response) {
		cookieService.deleteCookie("jwt",response);
	}
}
