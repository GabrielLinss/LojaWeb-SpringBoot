package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufc.br.security.UserDetailsImplementacao;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsImplementacao userDetailsImplementacao;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/cadastrar").permitAll()
		.antMatchers("/produto/cadastrar").hasRole("ADMIN")
		.antMatchers("/produto/produtosCadastrados").hasRole("ADMIN")
		.antMatchers("/produto/excluir/**").hasRole("ADMIN")
		.antMatchers("/usuario/comprar/**").hasRole("USER")
		.antMatchers("/usuario/carrinho").hasRole("USER")
		.antMatchers("/usuario/historico").hasRole("USER")
		.antMatchers("/usuario/removerProduto/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/produto/salvar").hasRole("ADMIN")
		.antMatchers("/sobre").permitAll()
		.antMatchers("/produto/buscar/**").permitAll()
		.antMatchers("/usuario/formulario").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().defaultSuccessUrl("/").loginPage("/usuario/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/img/**");
	}
}
	