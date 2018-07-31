package com.brunno.SpringBase.config.security;


import com.brunno.SpringBase.domain.User;
import com.brunno.SpringBase.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException { }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        String password = (String) authentication.getCredentials();

        preConditions(username, password);

        User user = userService.findUserByUsername(username);

        posConditions(password, user);

        return user;
    }

    private void preConditions(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            setHideUserNotFoundExceptions(false);
            throw new UsernameNotFoundException("User not found");
        }

        if (password == null || StringUtils.isEmpty(password)) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private void posConditions(String password, User user) {
        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

    }
}
