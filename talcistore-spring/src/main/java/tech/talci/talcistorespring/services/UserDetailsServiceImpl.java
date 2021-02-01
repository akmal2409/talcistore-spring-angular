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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String ROLE_PREFIX = "ROLE_";
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User fetchedUser = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found"));

        return new org.springframework.security.core.userdetails.User(
                fetchedUser.getUsername(), fetchedUser.getPassword(), fetchedUser.isEnabled(),
                true, true, true, getAuthority(fetchedUser)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthority(User user) {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()))
                .collect(Collectors.toList());
    }
}
