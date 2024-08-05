package org.aacounty.wastetrackingng.configurations;

import org.aacounty.wastetrackingng.security.AwsCognitoJwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// put here what are the extract roles here:
	private static final String ADMIN_ROLE = "Admin";
	private static final String SUPERVISOR_ROLE = "Supervisor";
	private static final String USER_ROLE = "User";
	private static final String VIEW_ROLE = "View";

	// put all the endpoints here according to their access level:
	private static final String[] ALLOWED_TO_ADMIN = {
			"/serviceareas",
			"/serviceareas/new",
			"/alluser/details",
			"/alluser/api/user",
			"/applicationuser/register",
			"/applicationuser/update",
			"/applicationuser/UserRole",
			"/api/role",
			"/api/departments",
			"/batches/hello/admin"
	};

	private static final String[] ALLOWED_TO_ADMIN_N_USER = {
			"/batches/create",
			"/batches/update",
			"/batches/delete",
			"/batches/suspendedbatches",
			"/batches/accounthistory/update",
			"/batches/accounthistory/create",
			"/batches/accounthistory/addFileData",
			"/batches/accounthistory/delete",
			"/batches/hello/admin/user"
	};

	private static final String[] ALLOWED_TO_ADMIN_SUPERVISOR_USER_N_VIEW = {
			"/batches/view/mostrecentid",
			"/batches/view/pending",
			"/batches/view/batchtype",
			"/batches/accounthistory/view",
			"/batches/view/search",
			"/batches/updatestatus",
			"/batches/accounthistory/view/id",
			"/batches/accounthistory/view/allbyid",
			"/batches/accounthistory/view/servicearea",
			"/batches/accounthistory/view/serviceareadate",
			"/batches/accounthistory/view/search",
			"/serviceareas",
			"/serviceareas/new",
			"/batches/hello/all"
	};

	@Autowired
	private AwsCognitoJwtAuthFilter awsCognitoJwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()));
		http.headers(headers -> headers.cacheControl());
		http.csrf(csrf -> csrf.disable())
				.authorizeRequests(requests -> requests
						.antMatchers("**/health").permitAll()
						.antMatchers("/api/timekeeper/**").hasRole("TIMEKEEPER")
						.antMatchers("/api/supervisor/**").hasAnyRole("SUPERVISOR", "ADMIN")
						.antMatchers(ALLOWED_TO_ADMIN).permitAll()
						.antMatchers(ALLOWED_TO_ADMIN_N_USER).hasAnyRole(ADMIN_ROLE, USER_ROLE, "Admin", "User")
						.antMatchers(ALLOWED_TO_ADMIN_SUPERVISOR_USER_N_VIEW)
						.hasAnyRole(ADMIN_ROLE, SUPERVISOR_ROLE, USER_ROLE, VIEW_ROLE, "Admin", "User", "Supervisor",
								"View")
						.antMatchers("/api/**").authenticated())
				.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	// Commenting out to use Dean's working configure() method
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.cors().configurationSource(request -> { //FOR DEV ONLY
	//
	// CorsConfiguration corsConfig = new CorsConfiguration();
	// corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:8910",
	// "http://localhost:3001", "http://localhost:3000",
	// "https://wastetrackingtest.aacounty.org",
	// "https://wastetracking.aacounty.org"));
	// corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
	// "OPTIONS"));
	// corsConfig.applyPermitDefaultValues();
	// corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control",
	// "Content-Type"));
	// corsConfig.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control",
	// "Content-Type"));
	// corsConfig.setAllowCredentials(true);
	// return corsConfig;
	// });
	// http.headers().cacheControl();
	// http.csrf().disable()
	// .authorizeRequests()
	// .antMatchers("/alluser/api/user").permitAll()
	//
	//// .antMatchers(ALLOWED_TO_ADMIN).hasAnyRole(ADMIN_ROLE, "Admin")
	//// .antMatchers(ALLOWED_TO_ADMIN_N_USER).hasAnyRole(ADMIN_ROLE, USER_ROLE,
	// "Admin", "User")
	//// .antMatchers(ALLOWED_TO_ADMIN_SUPERVISOR_USER_N_VIEW).hasAnyRole(ADMIN_ROLE,
	// SUPERVISOR_ROLE, USER_ROLE, VIEW_ROLE, "Admin", "User", "Supervisor", "View")
	// .antMatchers("**/health").permitAll()
	// .antMatchers("/api/**").permitAll()
	//
	// .antMatchers("/admin").hasRole("COACH")
	// .antMatchers(ALLOWED_TO_ADMIN).permitAll()
	// .antMatchers(ALLOWED_TO_ADMIN_N_USER).permitAll()
	// .antMatchers(ALLOWED_TO_ADMIN_SUPERVISOR_USER_N_VIEW).permitAll()
	// .antMatchers("/login").permitAll()
	// .anyRequest().authenticated().and()
	// .addFilterBefore(awsCognitoJwtAuthenticationFilter,
	// UsernamePasswordAuthenticationFilter.class);
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}