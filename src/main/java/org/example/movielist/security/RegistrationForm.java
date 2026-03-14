package org.example.movielist.security;

import lombok.Data;
import org.example.movielist.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        return user;
    }
}
