package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView allUsers() {

        List<User> listUser = userService.allUsers();
        ModelAndView mav = new ModelAndView("admin");
        User currUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("userName", currUser.getName());
        mav.addObject("listRole", currUser.getRoles());
        mav.addObject("listUser", listUser);
        mav.addObject("allRoles", roleDao.allRoles());
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam("userRoles") String userRole) {
        user.setRoles(getSetRole(userRole));
        userService.add(user);
        return "redirect:/admin";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@PathVariable("id") long id, @RequestParam("editRoles") String editRoles,
                           @ModelAttribute("user") User user) {
        user.setRoles(getSetRole(editRoles));
        userService.edit(user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") long id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView printWelcomeUser() {
        User currUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List<User> listUser = new ArrayList<>();
        listUser.add(currUser);
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("userName", currUser.getName());
        mav.addObject("listUser", listUser);
        mav.addObject("allRoles", currUser.getRoles());
        return mav;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    Set<Role> getSetRole(String roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles.contains("test")) {
            roleSet.add(userService.getRoleByName("test"));
        }
        if (roles.contains("admin")) {
            roleSet.add(userService.getRoleByName("admin"));
        }
        if(roles.contains("user")){
            roleSet.add(userService.getRoleByName("user"));
        }
        return roleSet;
    }
}