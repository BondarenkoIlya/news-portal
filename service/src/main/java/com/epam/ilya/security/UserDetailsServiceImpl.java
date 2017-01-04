package com.epam.ilya.security;

import com.epam.ilya.api.UserService;
import com.epam.ilya.domain.entities.UserRole;
import com.epam.ilya.exception.ServiceException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userDetailsServiceImpl")
@ComponentScan(basePackages = {"com.epam.ilya.impl", "com.epam.ilya.api"})
public class UserDetailsServiceImpl implements UserDetailsService {

    //    @Inject
    @Resource(lookup = "java:app/news/UserServiceImpl")
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.epam.ilya.domain.entities.User user;
        List<GrantedAuthority> authorities;
        try {
            user = userService.getUserByName(username);
            authorities = buildUserAuthority(user.getUserRole());
        } catch (ServiceException e) {
            throw new UsernameNotFoundException("Have no user with username -" + username, e);
        }
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.epam.ilya.domain.entities.User user, List<GrantedAuthority> authorities) {
        return new User(user.getName(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        return Result;
    }
}
