package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User fetchedUser = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found"));

        return new org.springframework.security.core.userdetails.User(
                fetchedUser.getUsername(), fetchedUser.getPassword(), fetchedUser.isEnabled(),
                true, true, true, getAuthority("USER")
        );
    }

    private Collection<? extends GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
