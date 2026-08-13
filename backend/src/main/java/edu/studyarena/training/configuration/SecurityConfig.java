package edu.studyarena.training.security;

import org.springframework.security.crypto.password.PasswordEncoder;      // <-- es la interfaz de BCryptPasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


}
