package config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("In configure 1");

		auth.inMemoryAuthentication().withUser("Rabin").password("admin").roles("admin");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		System.out.println("In configure 2");

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
				.authorizeRequests().antMatchers("/home2").hasRole("admin") // Specific rule comes first
				.antMatchers("/home1").permitAll() // Specific rule comes first
				.anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=true")
				.successHandler(authenticationSuccessHandler()) // Add this line
				.defaultSuccessUrl("/home1").permitAll()

				.and().logout().logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home1").permitAll();
		System.out.println("Authentication done");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		System.out.println("In getPasswordEncoder");

		return NoOpPasswordEncoder.getInstance();

	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {

		System.out.println("In the session set method..");
		return new SimpleUrlAuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				System.out.println("In the session set method..1");

				String loggedInUser = authentication.getName();
				System.out.println("Logged in user: " + loggedInUser);

				request.getSession().setAttribute("loggedInUser", loggedInUser);

				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}

}