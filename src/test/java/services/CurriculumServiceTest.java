
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;
import domain.Dancer;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CurriculumServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private DancerService		dancerService;


	// Tests
	// ====================================================

	/*
	 * En este test, se comprueba el registro de un nuevo curriculum, así como su edición o eliminación del mismo y el listado correspondientes del usuario logueado.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Manage his o her curricula, this includes listing, creating, editing and deleting them.
	 * 
	 * En este caso de uso simularemos el listado de curriculums de un dancer.
	 */

	public void listOfCurriculaTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
			int dancerId;
			Dancer dancer;

			dancer = this.dancerService.findByPrincipal();
			dancerId = dancer.getId();
			this.curriculumService.findAllByDancerId(dancerId);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Manage his o her curricula, this includes listing, creating, editing and deleting them.
	 * 
	 * En este caso de uso simularemos la creación de un curriculum.
	 */

	public void curriculumCreateTest(final String username, final String name, final String email, final String whatsappNumber, final String linkToLinkedIn, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Curriculum result = this.curriculumService.create();

			Assert.notNull(name);
			Assert.notNull(email);
			Assert.notNull(whatsappNumber);
			Assert.isTrue(whatsappNumber.matches("^[+]([0-9]{1,3})?[0-9]{9}$"));
			Assert.notNull(linkToLinkedIn);

			result.setName(name);
			result.setEmail(email);
			result.setWhatsappNumber(whatsappNumber);
			result.setLinkToLinkedIn(linkToLinkedIn);

			this.curriculumService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Manage his o her curricula, this includes listing, creating, editing and deleting them.
	 * 
	 * En este caso de uso simularemos la edición de un curriculum.
	 */

	public void editCurriculumTest(final String username, final int curriculumId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

			this.curriculumService.save(curriculum);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Manage his o her curricula, this includes listing, creating, editing and deleting them.
	 * 
	 * En este caso de uso simularemos la eliminación de un curriculum.
	 */

	public void deleteCurriculumTest(final String username, final int curriculumId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

			this.curriculumService.delete(curriculum);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverListCurriculaTest() {

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
			// Un administrador -> false
			{
				"admin", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfCurriculaTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverCreateCurriculumTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, "curriculumName", "curriculum@curriculum.com", "+34653501916", "linkToLinkedIn", IllegalArgumentException.class
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, IllegalArgumentException.class
			},
			// Alguien logueado como dancer -> true
			{
				"dancer1", "curriculumName", "curriculum@curriculum.com", "+34653501916", "linkToLinkedIn", null
			},
			// Alguien logueado como academy -> false
			{
				"academy1", "curriculumName", "curriculum@curriculum.com", "+34653501916", "linkToLinkedIn", IllegalArgumentException.class
			},
			// Alguien logueado como dancer cuyo nº whatsapp es incorrecto-> false
			{
				"dancer1", "curriculumName", "curriculum@curriculum.com", "+34653501", "linkToLinkedIn", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.curriculumCreateTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	@Test
	public void driverEditCurriculumTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 37, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"dancer1", 37, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"dancer1", 39, IllegalArgumentException.class
			},
			// Edición por alguien que no es 'dancer' -> false
			{
				"academy1", 37, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editCurriculumTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteCurriculumTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 37, IllegalArgumentException.class
			},
			// Eliminación por parte del propietario del curriculum -> true
			{
				"dancer1", 37, null
			},
			// Eliminación por alguien que no es el propietario -> false
			{
				"dancer1", 39, IllegalArgumentException.class
			},
			// Eliminación por alguien que no es 'dancer' -> false
			{
				"academy1", 37, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteCurriculumTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
