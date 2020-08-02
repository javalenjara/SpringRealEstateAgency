package co.com.udem.rea.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.udem.rea.dto.AuthenticationRequestDTO;
import co.com.udem.rea.repository.UserRepository;
import co.com.udem.rea.security.jwt.JwtTokenProvider;
import net.minidev.json.JSONObject;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;
    
    @Autowired
    PasswordEncoder passEncoder;

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signin(@RequestBody AuthenticationRequestDTO data) {

        try {
            String username = data.getUsername();
            String password = data.getPassword();
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(upat);
            String token = jwtTokenProvider.createToken(username, users.findByUsername(username).orElseThrow(() -> 
            new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            JSONObject respuesta = new JSONObject();
            respuesta.put("username", username);
            respuesta.put("token", token);
            return ok(respuesta);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
