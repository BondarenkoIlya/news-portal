package com.epam.ilya.security;

import com.epam.ilya.dao.api.UserDaoLocal;
import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ComponentScan({"com.epam.ilya.dao.impl"})
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(lookup = "userDaoJPA")
    private UserDaoLocal userDaoLocal;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.epam.ilya.domain.entities.User user;
        List<GrantedAuthority> authorities;
        try {
            user = userDaoLocal.findByName(username);
            authorities = buildUserAuthority(user.getUserRole());
        } catch (DaoException e) {
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
