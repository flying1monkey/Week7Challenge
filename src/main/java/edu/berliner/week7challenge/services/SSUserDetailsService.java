package edu.berliner.week7challenge.services;

import edu.berliner.week7challenge.models.PersonUser;
import edu.berliner.week7challenge.models.RoleSec;
import edu.berliner.week7challenge.repositories.PersonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService
{
    @Autowired
    private PersonUserRepository userRepo;

    public SSUserDetailsService(PersonUserRepository userRepo)
    {
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try
        {
            PersonUser user = userRepo.findByUsername(username);
            if(user==null)
            {
                System.out.println("User not found-this message is for debug only");
                return null;
            }
            System.out.println("Username: "+user.toString());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Unable to find user");
        }
    }

    private Set<GrantedAuthority> getAuthorities(PersonUser user)
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(RoleSec role : user.getSecRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getSecRoleName());
            authorities.add(grantedAuthority);
        }
        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }
}
