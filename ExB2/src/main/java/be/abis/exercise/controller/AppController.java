package be.abis.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {

	@Autowired
	TrainingService trainingService;
	
	@Autowired
	CourseService courseService;
	
	Person loggedInPerson;
	Course courseToSearch;
	
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
	
	@PostMapping("/logout")
	public String logout(Model model) {
		return "redirect:/";
	}
	
	@GetMapping("/welcome")
	public String showWelcome(Model model) {
		model.addAttribute("person",loggedInPerson);
		return "welcome";
	}
	
	@GetMapping("/course")
	public String showCourse(Model model) {
		return "course";
	}
	
	@GetMapping("/allcourses")
	public String showAllCourses(Model model) {
		List<Course> courses = courseService.findAllCourses();
		model.addAttribute("courses",courses);
		return "allcourses";
	}
	
	@GetMapping("/findcourse")
	public String findCourse(Model model) {
		Course c = new Course();
		model.addAttribute("course",c);
		return "findcourse";
	}
	
	@PostMapping("/findcourse")
	public String findCourse(Model model, Course c) {
		int courseIdInt = Integer.parseInt(c.getCourseId());
		courseToSearch = courseService.findCourse(courseIdInt);
		model.addAttribute("course",courseToSearch);
		System.out.println("Course : id "+c.getCourseId()+" - "+courseToSearch.getShortTitle());
		return "findcourse";
	}

}