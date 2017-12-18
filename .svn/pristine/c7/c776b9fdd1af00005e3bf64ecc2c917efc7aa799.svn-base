
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Course;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private CourseService	courseService;
	@Autowired
	private AcademyService	academyService;
	@Autowired
	private StyleService	styleService;


	// Tests
	// ====================================================

	/*
	 * Browse the list of courses and navigate to the courses that their offer.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los cursos que existen en nuestra aplicación.
	 */

	public void listOfCoursesTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.courseService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * To check the validity of a new course in our system, the system must check
	 * the name, the description, the pictures, and the videos.
	 * 
	 * En este test, se comprueba la creación de un nuevo curso. Para ello
	 * introducimos valores correctos e incorrectos para observa el
	 * comportamiento de la aplicación.
	 */

	/*
	 * Create a new course.
	 * 
	 * En este caso de uso una academia puede crear un curso.
	 */

	@SuppressWarnings("deprecation")
	public void courseCreateTest(final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Course result = this.courseService.create();

			final Date startDate = new Date();
			final Date finishDate = new Date();
			finishDate.setYear(2018);

			result.setTitle(title);
			result.setLevel("BEGINNER");
			result.setStartDate(startDate);
			result.setEndDate(finishDate);
			result.setDay("MONDAY");
			result.setHour("10:00");
			result.setAcademy(this.academyService.findByPrincipal());
			result.setStyle(this.styleService.findOne(17));

			this.courseService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit a new course.
	 * 
	 * En este caso de uso una academia puede editar un curso existente.
	 */

	public void editCourseTest(final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Course result;

			result = this.courseService.findOne(20);

			result.setTitle(title);

			this.courseService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a course.
	 * 
	 * En este caso de uso una academia puede borrar un curso existente.
	 */

	public void deleteCourseTest(final String username, final int courseId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			final Course course = this.courseService.findOne(courseId);

			this.authenticate(username);

			this.courseService.delete(course);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverListCourseTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				null, null
			},
			// Un dancer -> true
			{
				"dancer1", null
			},
			// Una academy -> true
			{
				"academy1", null
			},
			// Un administrador -> true
			{
				"admin", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfCoursesTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverCourseCreateTest() {

		final Object testingData[][] = {
			// Crear un curso estando logueado como academy -> true
			{
				"academy1", "curso iniciacion salsa", null
			},
			// Crear un curso sin estar logueado --> false
			{
				null, "curso iniciacion salsa", IllegalArgumentException.class
			},
			// Crear un curso logueado como dancer -> false
			{
				"dancer1", "curso iniciacion salsa", IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.courseCreateTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	//@Test
	public void driverEditCourseTest() {

		final Object testingData[][] = {
			// Editar un curso estando logueado como academy -> true
			{
				"academy1", "curso hiphop", null
			},
			// Editar un curso sin estar logueado -> false
			{
				null, "curso hiphop", IllegalArgumentException.class
			},
			// Editar un curso logueado como dancer -> false
			{
				"dancer1", " curso hiphop", IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.editCourseTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	//@Test
	public void driverDeleteCourseTest() {

		final Object testingData[][] = {
			// Borrar un curso estando logueado como academy -> true
			{
				"academy1", 20, null
			},
			// Borrar un curso sin estar logueado -> false
			{
				null, 20, IllegalArgumentException.class
			},
			// Borrar un curso que tiene Applies -> false
			{
				"academy1", 18, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteCourseTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
