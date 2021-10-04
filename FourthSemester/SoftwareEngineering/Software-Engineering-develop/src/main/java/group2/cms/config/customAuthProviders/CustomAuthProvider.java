package group2.cms.config.customAuthProviders;

import group2.cms.domain.Authority;
import group2.cms.domain.CMSUser;
import group2.cms.repository.GenericUserRepository;
import group2.cms.service.DTO.Auth.AuthenticatedUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@AllArgsConstructor
public class CustomAuthProvider<T extends CMSUser> implements AuthenticationProvider {
    private final GenericUserRepository<T> repo;

    public AuthenticatedUserDTO doAuth(Authentication auth) {
        authenticate(auth);
        var loggedInUser = new AuthenticatedUserDTO();
        var ctx = SecurityContextHolder.getContext();
        loggedInUser.setUserName(ctx.getAuthentication().getName());
        loggedInUser.setAuthority(
                Authority.valueOf(ctx.getAuthentication()
                        .getAuthorities()
                        .toArray()[0]
                        .toString()));
        return loggedInUser;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        var user = repo.findByUsername(userName);

        var passwordEncoder = new BCryptPasswordEncoder();
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            var authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.get().getAuthority().name()));
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth;
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
