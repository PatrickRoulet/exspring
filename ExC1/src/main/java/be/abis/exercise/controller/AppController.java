package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	Person personToSearch;
	
	@GetMapping("/")
	public String showHome(Model model) {
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
	
	@GetMapping("/changepassword")
	public String showChangePassword(Model model, Person p) {
		loggedInPerson = trainingService.findPerson(p.getEmailAddress(), p.getPassword());
		model.addAttribute("person",loggedInPerson);
		model.addAttribute("newPswd","");
		return "changepassword";
	}
	
	@PostMapping("/changepassword")
	public String changePassword(Model model, Person p, String newPswd) {
		try {
			trainingService.changePassword(p, newPswd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "changepassword";
	}
	
	@GetMapping("/coursemenu")
	public String showCourse(Model model) {
		return "coursemenu";
	}
	
	@GetMapping("/allcourses")
	public String showAllCourses(Model model) {
		List<Course> courses = courseService.findAllCourses();
		model.addAttribute("courses",courses);
		return "allcourses";
	}
	
	@GetMapping("/findcoursebyid")
	public String findCourseById(Model model) {
		Course c = new Course();
		model.addAttribute("course",c);
		return "findcoursebyid";
	}
	
	@GetMapping("/findcoursebytitle")
	public String findCourseByTitle(Model model) {
		Course c = new Course();
		model.addAttribute("course",c);
		return "findcoursebytitle";
	}
	
	@PostMapping("/courseresult")
	public String showCourseResult(Model model, Course c) {
		String courseIdString = c.getCourseId();
		if (courseIdString == null || courseIdString.isEmpty()) {
			courseToSearch = courseService.findCourse(c.getShortTitle());
		} else {
			courseToSearch = courseService.findCourse(Integer.parseInt(courseIdString));			
		}
		model.addAttribute("course",courseToSearch);
		return "courseresult";
		
	}
	/*
	 * @PostMapping("/newcoursesearchbyid") public String newCourseSearch(Model
	 * model) { Course c = new Course(); model.addAttribute("course",c); return
	 * "redirect:findcoursebyid"; }
	 */
	
	@GetMapping("/personmenu")
	public String showPerson(Model model) {
		return "personmenu";
	}
	
	@GetMapping("/allpersons")
	public String showAllPersons(Model model) {
		List<Person> persons = trainingService.findAllPersons();
		model.addAttribute("persons",persons);
		return "allpersons";
	}
	
	@GetMapping("/findpersonbyid")
	public String findPersonById(Model model) {
		Person p = new Person();
		model.addAttribute("person",p);
		return "findpersonbyid";
	}
	
	@GetMapping("/findpersonbyemail")
	public String findPersonByEmail(Model model) {
		Person p = new Person();
		model.addAttribute("person",p);
		return "findpersonbyemail";
	}
	
	@PostMapping("/personresult")
	public String showPersonResult(Model model, Person p) {
		String personEmail = p.getEmailAddress();
		if (personEmail == null || personEmail.isEmpty()) {
			personToSearch = trainingService.findPerson(p.getPersonId());			
		} else {
			personToSearch = trainingService.findPerson(personEmail, p.getPassword());
		}
		model.addAttribute("person",personToSearch);
		return "personresult";
		
	}
	
	@GetMapping("/addperson")
	public String showAddPerson(Model model) {
		Person p = new Person();
		model.addAttribute("person",p);
		return "addPerson";
	}
	
	@PostMapping("/addperson")
	public String addPerson(Model model, @Valid Person p, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
            return "addperson";
        }
		
		try {
			trainingService.addPerson(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("person",p);
		return "addperson";
		
	}
	
	@GetMapping("/deleteperson")
	public String showDeletePerson(Model model) {
		Person p = new Person();
		String message = "";
		model.addAttribute("person",p);
		model.addAttribute("message",message);
		return "deleteperson";
	}
	
	@PostMapping("/deleteperson")
	public String deletePerson(Model model, Person p) {
		trainingService.deletePerson(p.getPersonId());
		String message = "Person with id "+p.getPersonId()+" deleted";
		model.addAttribute("message",message);
		return "deleteperson";
	}

}