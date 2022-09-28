package tn.telecom.mgmtbackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class UserUtils {
    @Autowired
    private UserRepository userRepository;

    private static UserRepository userRepo;


    @PostConstruct
    public void init() {
        userRepo = userRepository;
    }

    public static User getUserFromToken(String authorizationHeader){
        System.out.println(authorizationHeader);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            return userRepo.findByUsername(username);

        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
