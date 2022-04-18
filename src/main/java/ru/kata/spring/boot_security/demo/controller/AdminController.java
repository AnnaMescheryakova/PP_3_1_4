package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRoles", required = false) String roles) {
        user.setRoles(roleService.getByName(roles));
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "showUser";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id,
                             @RequestParam(value = "nameRoles", required = false) String roles) {
        user.setRoles(roleService.getByName(roles));
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}