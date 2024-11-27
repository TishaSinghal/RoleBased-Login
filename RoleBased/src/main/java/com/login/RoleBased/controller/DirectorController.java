package com.login.RoleBased.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectorController {

    @Secured("ROLE_DIRECTOR")
    @GetMapping("/director/dashboard")
    public String getDirectorDashboard() {
        return "Director Dashboard: Welcome Director!";
    }
}