package org.cowary.arttrackerback.dbCase;


import org.cowary.arttrackerback.entity.User;
import org.cowary.arttrackerback.repo.user.UserRepo;
import org.cowary.arttrackerback.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
