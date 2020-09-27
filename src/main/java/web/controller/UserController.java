package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView allUsers() {

        List<User> listUser = userService.allUsers();
        ModelAndView mav = new ModelAndView("admin");
        User currUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("userName", currUser.getName());
        mav.addObject("listRole", currUser.getRoles());
        mav.addObject("listUser", listUser);
        return mav;
    }

    @RequestMapping("/new")
    public String newUserForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView editUserForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.getById(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/delete")
    public String deleteUserForm(@RequestParam long id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return "redirect:/";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        return "user";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String printWelcomeUser(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "user";
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
}