
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Academy;
import domain.Apply;
import domain.Course;
import domain.Curriculum;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplyServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ApplyService		applyService;

	@Autowired
	private CourseService		courseService;

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private AcademyService		academyService;


	// Tests
	// ====================================================

	/*
	 * List their applies.
	 * 
	 * En este caso de uso se comprobamos que un usuario logueado como "Academy"
	 * puede listar las applies que posea.
	 */

	public void listOfAppliesTest(final String username, final int academyId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final Academy academy = this.academyService.findOne(academyId);

			this.applyService.findAllByAcademy(academy);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Accept or Reject their applies.
	 * 
	 * En este caso de uso se comprobamos que un usuario logueado como "Academy"
	 * puede aceptar o rechazar las applies que posea.
	 */

	public void saveStatusOfAppliesTest(final String username, final int academyId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final Apply apply = this.applyService.findOne(29);

			apply.setStatus("ACCEPTED");

			this.applyService.saveStatus(apply);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Create an student apply.
	 * 
	 * En este caso de uso se comprobamos que un usuario logueado como "Dancer"
	 * puede aplicar como estudiante sobre un curso.
	 */

	public void createAndSaveApplyStudentTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Course course = this.courseService.findOne(27);

			final Apply apply = this.applyService.create(course);

			this.applyService.save(apply);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	public void createAndSaveApplyTeacherTest(final String username, final int curriculumId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Course course = this.courseService.findOne(27);

			final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

			final Apply apply = this.applyService.create(course);

			apply.setCurriculum(curriculum);

			this.applyService.saveTeacher(apply);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverListAcademyTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 12, NullPointerException.class
			},
			// Un dancer -> false
			{
				"dancer1", 12, NullPointerException.class
			},
			// Una academy -> true
			{
				"academy1", 9, null
			},
			// Un administrador -> false
			{
				"admin", 8, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfAppliesTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverSaveStatusOfAppliesTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 12, NullPointerException.class
			},
			// Un dancer -> false
			{
				"dancer1", 12, NullPointerException.class
			},
			// Una academy -> true
			{
				"academy1", 9, null
			},
			// Un admin -> false
			{
				"admin", 8, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfAppliesTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverCreateAndSaveApplyStudentTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, IllegalArgumentException.class
			},
			// Un dancer -> true
			{
				"dancer1", null
			},
			// Una academy -> false
			{
				"academy1", IllegalArgumentException.class
			},
			// Un admin -> false
			{
				"admin", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.createAndSaveApplyStudentTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverCreateAndSaveApplyTeacherTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 37, IllegalArgumentException.class
			},
			// Un dancer -> true
			{
				"dancer1", 37, null
			},
			// Una academy -> false
			{
				"academy1", 37, IllegalArgumentException.class
			},
			// Un admin -> false
			{
				"admin", 37, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.createAndSaveApplyTeacherTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
