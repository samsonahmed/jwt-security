package sam.tutorial.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sam.tutorial.springsecurity.domain.AppUser;
import sam.tutorial.springsecurity.domain.Role;
import sam.tutorial.springsecurity.domain.RoleToUserForm;
import sam.tutorial.springsecurity.serice.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
      return ResponseEntity.ok().body(userService.getUsers());
        //return new ResponseEntity<List<AppUser>>(userService.getUsers(), HttpStatus.OK);
    }
    @PostMapping("/users/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUSer(appUser));
        //return new ResponseEntity<List<AppUser>>(userService.getUsers(), HttpStatus.OK);
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.ok().body(userService.saveRole(role));
        //return new ResponseEntity<List<AppUser>>(userService.getUsers(), HttpStatus.OK);
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm toUserForm){
        userService.addRoleToUser(toUserForm.getUserName(),toUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }


}
