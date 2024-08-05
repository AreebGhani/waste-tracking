package org.aacounty.wastetrackingng;

import org.aacounty.wastetrackingng.configurations.JwtConfiguration;
//import com.aaco.user.User;
//import com.aaco.user.UserRepository;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
@EnableCaching
public class WastetrackingngApplication {

	private static final Logger logger = LoggerFactory.getLogger(WastetrackingngApplication.class);

	@Autowired
	private JwtConfiguration jwtConfiguration;

	public static void main(String[] args) {
		try {
			// WastetrackingngApplication appClass = new WastetrackingngApplication();
			// appClass.testConnection();

			SpringApplication.run(WastetrackingngApplication.class, args);
		} catch (Exception e) {
			System.err.println("Error on running application: " + e.getMessage());
		}
	}

	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			logger.info("Getting the database info from application.properties to see if env vars are being injected: "
					+ environment.getProperty("spring.datasource.url"));
		};
	}

	/* */
	@Bean
	public ConfigurableJWTProcessor<SecurityContext> configurableJWTProcessor() throws MalformedURLException {
		ResourceRetriever resourceRetriever = new DefaultResourceRetriever(
				jwtConfiguration.getConnectionTimeout(),
				jwtConfiguration.getReadTimeout());
		URL jwkSetURL = new URL(jwtConfiguration.getJwkUrl());
		JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(jwkSetURL, resourceRetriever);
		ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
		JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, keySource);
		jwtProcessor.setJWSKeySelector(keySelector);
		return jwtProcessor;
	}
}
