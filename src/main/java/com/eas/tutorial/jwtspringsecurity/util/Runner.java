package com.eas.tutorial.jwtspringsecurity.util;

import com.eas.tutorial.jwtspringsecurity.model.Authority;
import com.eas.tutorial.jwtspringsecurity.model.User;
import com.eas.tutorial.jwtspringsecurity.repository.AuthorityRepository;
import com.eas.tutorial.jwtspringsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Crea autoridades y usuarios de ejemplo si no existen previamente
        en la base de datos. */

        /*
        if (this.authorityRepository.count() == 0) {
            this.authorityRepository.saveAll(
                    List.of(
                    new Authority(AuthorityName.ROLE_ADMIN),
                    new Authority(AuthorityName.ROLE_USER)
                    )
            );
        }

        if (this.userRepository.count()==0) {
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            User user = new User();
            user.setId(1L);
            user.setUsername("estefaaguas");
            user.setPassword(encoders.encode("5678"));
            user.setAuthorities(List.of(this.authorityRepository.findByName(AuthorityName.ROLE_ADMIN).get()));
            user.setName("Estefanía");
            user.setLastname("Aguas");
            user.setCountry("Colombia");

            this.userRepository.saveAll(
                    List.of(user)
            );
        }

         */
    }
}
