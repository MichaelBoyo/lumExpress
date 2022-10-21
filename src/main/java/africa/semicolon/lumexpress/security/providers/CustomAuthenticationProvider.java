package africa.semicolon.lumexpress.security.providers;

import africa.semicolon.lumexpress.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        if (passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                    authentication.getCredentials(), userDetails.getAuthorities());
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
