package africa.semicolon.lumexpress.security;

import africa.semicolon.lumexpress.service.userService.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public record CustomUserDetailsServiceImpl(UserService userService) implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecureUser(userService.getUserByEmail(username));
    }
}
