package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/login")
	public String showLoginForm() {
		System.out.println("Showing login get");
		return "login";
	}

	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	// public String login(HttpServletRequest request) {
	// System.out.println("Showing login post");
	//
	// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// System.out.println(auth);
	// if (auth != null && auth.isAuthenticated()) {
	//
	// System.out.println("Showing login post if");
	//
	// // Redirect to the desired page after successful login
	// // System.out.println("successful login,redirecting to home2");
	// return "home1"; // "redirect:/home2";
	//
	// } else {
	//
	// System.out.println("Showing login post else");
	//
	// // Redirect to the login page with an error parameter
	// return "login"; // "redirect:/login?error=true";
	// }
	// }

	@RequestMapping("/home1")
	public String home1() {

		System.out.println("Showing  home1");

		// System.out.println("calling to home1");
		return "home1";
	}

	@RequestMapping("/home2")
	public String home2(HttpSession session) {

		System.out.println("Showing  home2");
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			System.out.println("Logged in user: " + loggedInUser);
			// Perform actions based on the logged-in user
		} else {
			System.out.println("User is not logged in");
			// Handle the case when the user is not logged in
		}
		return "home2";
	}

	@RequestMapping("/logout")
	public String logout() {

		System.out.println("Showing  logout");

		// Perform logout logic here

		return "login"; // "redirect:/login";
	}
}
