package org.cowary.arttrackerback.security;


import org.cowary.arttrackerback.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        return UserDetailsImpl.build(user);
    }

    public Long getIdCurrentUser() {
        //var lel = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //System.out.println(lel.getUsername());
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null);
        var userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetail.getUsername());
        var user = loadUserByUsername(userDetail.getUsername());
        System.out.println(user.getId());

//        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public String getNameCurrentUser() {
        return userRepo.findByUsername(getName()).getUsername();
    }

    private String getName() {
        return "stub";
        //return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser() {
        return userRepo.findByUsername(getName());
    }

}
