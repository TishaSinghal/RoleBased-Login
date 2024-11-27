package com.login.RoleBased.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.login.RoleBased.service.UserService;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private UserService userService;
    
    @Secured("ROLE_CLIENT")
    @GetMapping("/client/dashboard")
    public String getClientDashboard() {
        return "Client Dashboard: Welcome ADGITM!";
    }

//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userService.findUsersByRoles(Set.of("DIRECTOR", "HOD", "PLACEMENT_HEAD"));
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.findUserByIdAndRole(id, Set.of("DIRECTOR", "HOD", "PLACEMENT_HEAD"));
//        return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/create-director")
//    public ResponseEntity<User> createDirector(@RequestBody User user) {
//        User newUser = userService.createDirector(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }
//
//    @PostMapping("/create-hod")
//    public ResponseEntity<User> createHOD(@RequestBody User user) {
//        User newUser = userService.createHOD(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }
//
//    @PostMapping("/create-placement-head")
//    public ResponseEntity<User> createPlacementHead(@RequestBody User user) {
//        User newUser = userService.createPlacementHead(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }
}

