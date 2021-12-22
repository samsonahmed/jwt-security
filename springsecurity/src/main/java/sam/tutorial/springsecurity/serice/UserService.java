package sam.tutorial.springsecurity.serice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sam.tutorial.springsecurity.domain.AppUser;
import sam.tutorial.springsecurity.domain.Role;
import sam.tutorial.springsecurity.repo.RoleRepo;
import sam.tutorial.springsecurity.repo.UserRepo;

import javax.swing.undo.AbstractUndoableEdit;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

   public AppUser saveUSer(AppUser appUser) {
        log.info("Savinf new user to db");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword() ));
        return userRepo.save(appUser);

    }

    public Role saveRole(Role role) {
        log.info("Savinf new role  to db");
        return roleRepo.save(role);
    }

    public void addRoleToUser(String userName, String roleName) {
        log.info("adding role to user");
        AppUser appUser = userRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);// since transactional ... it will save directly in db
    }

   public AppUser getUser(String userName) {
        log.info("getting user by it name");
        return userRepo.findByUserName(userName);

    }

    public List<AppUser> getUsers() {
        log.info("fetching all user");
        return userRepo.findAll();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser appUser=userRepo.findByUserName(username);
       if(appUser==null){
           throw new UsernameNotFoundException("user is not fond exception");
       }
        else{
            log.info("User found in database {} ",username);
       }
        Collection<SimpleGrantedAuthority> authortities=new ArrayList<>();
        appUser.getRoles().forEach(role -> authortities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(appUser.getUserName(),appUser.getPassword(),authortities);
    }
}
