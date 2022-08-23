package tn.telecom.mgmtbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.telecom.mgmtbackend.model.Role;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.services.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class MgmtBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgmtBackendApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null,"ADMIN"));
            userService.saveRole(new Role(null,"USER"));
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEmail("rania.derouiche@gmail.com");
            userService.saveUser(user);
            userService.addRoleToUser("admin","ADMIN");
        };
    }

}
