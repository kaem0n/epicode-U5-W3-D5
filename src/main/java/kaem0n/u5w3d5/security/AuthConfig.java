package kaem0n.u5w3d5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig {
    @Bean
    SecurityFilterChain sfc(HttpSecurity hs) throws Exception {
        hs.formLogin(http -> http.disable());
        hs.csrf(http -> http.disable());
        hs.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        hs.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

        return hs.build();
    }

    @Bean
    PasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder(13);
    }
}
