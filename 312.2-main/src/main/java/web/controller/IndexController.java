package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import web.config.exception.LoginException;
import web.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	private final UserService userService;

	@Autowired
	public IndexController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("")
	public String welcomePage(Model model, HttpSession session,
							  @SessionAttribute(required = false, name = "Authentication-Exception") LoginException authenticationException,
							  @SessionAttribute(required = false, name = "Authentication-Name") String authenticationName) {
		userService.authCheck(model, session, authenticationException, authenticationName);

		return "index";
	}
}