
package services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Course;
import domain.Trackson;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TracksonServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private TracksonService	tracksonService;

	@Autowired
	private CourseService	courseService;


	// Tests
	// ====================================================

	/*
	 * Create a trackson.
	 * 
	 * En este caso de uso crearemos una trackson sobre un course y comprobaremos
	 * que solo se puede crear si este no ha pasado.
	 */

	public void createTracksonTest(final String username, final int courseId, String name, Date displayMoment, String title, String description, Integer score, String justification, boolean cancel, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			final Course course = this.courseService.findOne(courseId);
			final Trackson trackson = this.tracksonService.create(course);
			//			trackson.setName(tracksonService.codeGenerator());
			trackson.setName(name);
			trackson.setTitle(title);
			trackson.setDescription(description);
			trackson.setScore(score);
			trackson.setJustification(justification);
			trackson.setCancel(cancel);

			Assert.isTrue(trackson.getTitle().length() >= 1 && trackson.getTitle().length() <= 20);
			Assert.isTrue(trackson.getDescription().length() >= 1 && trackson.getTitle().length() <= 100);

			this.tracksonService.save(trackson);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	// Drivers
	// ====================================================

	@Test
	public void driverCreateTracksonTest() {

		long l = 10;
		Date actual = new Date(System.currentTimeMillis() - l);
		Calendar lastSearch = Calendar.getInstance();
		lastSearch.setTime(actual);

		final Object testingData[][] = {
			// Creacion correcta, usando un admin y un curso valido -> true
			{
				"admin", 30, "uyr453", actual, "titulo", "descripcion", 0, null, false, null
			},
			// Creacion incorrecta, usando un admin y un curso no valido -> false		
			{
				"admin", 56, "uur454", actual, "titulo", "descripcion", 0, null, false, IllegalArgumentException.class
			},
			// Creacion correcta con el otro admin -> true
			{
				"luk", 30, "utt493", actual, "titulo", "descripcion", 0, null, false, null
			}, {
				// Si no estamos autentificados -> false
				null, 30, "uyr466", actual, "titulo", "descripcion", 0, null, false, IllegalArgumentException.class
			}, {
				//
				"admin", 30, "uy6453", actual, "titulo", "descripcion", 0, "justificado", true, null
			}, {
				//
				"admin", 30, "6yr453", actual, "titulo", "d", 0, "justificado", true, null
			}, {
				//
				"admin", 30, "uyr773", actual, "titulo1234567890123", "description", 0, null, false, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createTracksonTest((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Integer) testingData[i][6], (String) testingData[i][7],
				(boolean) testingData[i][8], (Class<?>) testingData[i][9]);
	}

}
