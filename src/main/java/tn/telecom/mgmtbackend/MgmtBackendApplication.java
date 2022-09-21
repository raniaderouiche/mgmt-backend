package tn.telecom.mgmtbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.telecom.mgmtbackend.model.*;
import tn.telecom.mgmtbackend.services.*;

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
    CommandLineRunner run(UserService userService, RoleService roleService, BusinessSectorService sectorService, ProfessionService professionService, TypeService typeService){
        return args -> {
            /*roleService.saveRole(new Role(null,"SUPER_ADMIN"));
            roleService.saveRole(new Role(null,"ADMIN"));
            roleService.saveRole(new Role(null,"USER"));
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEmail("rania.derouiche@gmail.com");
            userService.saveUser(user);
            userService.addRoleToUser("admin","SUPER_ADMIN");

            // data for testing
            BusinessSector sector = new BusinessSector();
            sector.setName("TEST");
            sectorService.saveBusinessSector(sector);
            Type type = new Type();
            type.setName("TEST");
            typeService.saveType(type);
            Profession profession = new Profession();
            profession.setName("TEST");
            profession.setSector(sector);
            professionService.saveProfession(profession);*/
        };
    }

}
