package africa.semicolon.lumexpress.security;

import africa.semicolon.lumexpress.exception.UserNotFoundException;
import africa.semicolon.lumexpress.service.userService.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record CustomUserDetailsServiceImpl(UserService userService) implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new SecureUser(userService.getUserByEmail(username));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
