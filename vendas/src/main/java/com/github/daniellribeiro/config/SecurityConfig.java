package com.github.daniellribeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.daniellribeiro.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	@Bean
	public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("Daniel")
        .password(passwordEncoder().encode("123"))
        .roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .authorizeRequests()
            .anyRequest()
            .hasRole("USER")
            .and()
        .httpBasic();
	}
}
