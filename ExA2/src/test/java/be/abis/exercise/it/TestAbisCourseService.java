package be.abis.exercise.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisCourseService {

	@Autowired
	CourseService courseService;

	@Test
	public void findTitleWithId7900() {
		assertEquals("7900", courseService.findCourse(7900).getCourseId());
	}

	@Test
	public void checkCourse7900HasPriceHigher400() {
		assertTrue(courseService.findCourse(7900).getPricePerDay() > 400.0);
	}

}
