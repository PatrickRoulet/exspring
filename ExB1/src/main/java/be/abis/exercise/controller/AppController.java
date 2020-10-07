package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {

	@Autowired
	TrainingService trainingService;
	
	Person loggedInPerson;
	
	@GetMapping("/")
	public String showPerson(Model model) {
		Person p = new Person();
		model.addAttribute("person",p);
		return "home";
	}
	
	@PostMapping("/")
	public String submitPassword(Model model, Person p) {
		loggedInPerson = trainingService.findPerson(p.getEmailAddress(), p.getPassword());
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public String showWelcome(Model model) {
		model.addAttribute("person",loggedInPerson);
		return "welcome";
	}
	
	@PostMapping("/welcome")
	public String chooseRef(Model model) {
		return "redirect:/course";
	}
	
	@GetMapping("/course")
	public String showCourse(Model model) {
		return "course";
	}

}