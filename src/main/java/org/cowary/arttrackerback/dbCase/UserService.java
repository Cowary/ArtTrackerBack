package org.cowary.arttrackerback.dbCase;


import org.cowary.arttrackerback.entity.User;
import org.cowary.arttrackerback.repo.user.RoleRepo;
import org.cowary.arttrackerback.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepo userRepo;
    final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public Long getIdCurrentUser() {
        return 1L;
        //return userRepo.findByUsername(getName()).getId();
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
