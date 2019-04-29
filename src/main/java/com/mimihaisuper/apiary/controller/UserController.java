package com.mimihaisuper.apiary.controller;

import com.mimihaisuper.apiary.model.authModel.Role;
import com.mimihaisuper.apiary.model.authModel.RoleName;
import com.mimihaisuper.apiary.model.authModel.User;
import com.mimihaisuper.apiary.repository.UserRepository;
import com.mimihaisuper.apiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping({"/", ""})
    public ResponseEntity getUsers(Principal principal) {
        if (hasAccess(principal)) {
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/count")
    public ResponseEntity getModuleCount(Principal principal, @RequestBody String username) {
        if (hasAccess(principal)) {
            return ResponseEntity.ok(userService.getModuleCount(username));
        }
        return ResponseEntity.status(401).build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteUser(Principal principal, @PathVariable("username") String username) {
        if(hasAccess(principal)) {
            userService.deleteUser(username);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.status(401).build();
    }

    private boolean hasAccess(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return user.getRoles().contains(new Role(RoleName.ROLE_ADMIN));
    }
}
