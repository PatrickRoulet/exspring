package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.exception.personNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	PersonRepository personRepository;
	
	@Override
	public List<Person> findAllPersons() {
		return personRepository.getAllPersons();
	}

	@Override
	public Course findCourse(int id) {
		return courseService.findCourse(id);
	}

	@Override
	public Person findPerson(int id) throws personNotFoundException {
		Person p = personRepository.findPerson(id);
		if (p == null) {
			throw new personNotFoundException();
		}
		return p;
	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {

	}
	
	@Override
	public void addPerson(Person p) throws IOException {
		personRepository.addPerson(p);
	}
	
	@Override
	public void deletePerson(int id) {
		personRepository.deletePerson(id);
	}
	
	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		personRepository.changePassword(p, newPswd);
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) throws personNotFoundException {
		Person p = personRepository.findPerson(emailAddress, passWord);
		if (p == null) {
			throw new personNotFoundException();
		}
		return p;
	}

}
