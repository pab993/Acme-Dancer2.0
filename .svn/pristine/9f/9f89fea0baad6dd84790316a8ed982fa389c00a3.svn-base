
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Style;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class StyleServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private StyleService	styleService;


	// Tests
	// ====================================================

	/*
	 * Browse the taxonomy of styles and navigate to the courses in which they are taught.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar la taxonomía de estilos que existen en nuestra aplicación.
	 */

	public void listOfStylesTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.styleService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * To check the validity of a new style in our system, the system must check
	 * the name, the description, the pictures, and the videos.
	 * 
	 * En este test, se comprueba la creación de un nuevo estilo. Para ello
	 * introducimos valores correctos e incorrectos para observa el
	 * comportamiento de la aplicación.
	 */

	/*
	 * Create a new style.
	 * 
	 * En este caso de uso simularemos la creación de un estilo.
	 */

	public void styleCreateTest(final String username, final String name, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Style result = this.styleService.create();

			result.setName(name);
			result.setDescription("description");

			this.styleService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit a style.
	 * 
	 * En este caso de uso un administrador puede editar un estilo.
	 */

	public void editStyleTest(final String username, final String name, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Style result;

			result = this.styleService.findOne(22);

			result.setName(name);

			this.styleService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a style.
	 * 
	 * En este caso de uso un administrador puede borrar un estilo existente.
	 */

	public void deleteStyleTest(final String username, final int styleId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Style result;

			result = this.styleService.findOne(styleId);

			this.styleService.delete(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverListStyleTest() {

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
			this.listOfStylesTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	@Test
	public void driverStyleCreateTest() {

		final Object testingData[][] = {
			// Crear un estilo estando logueado como administrador -> true
			{
				"admin", "reggaeton", null
			},
			// Crear un estilo sin estar logueado --> false
			{
				null, "reggaeton", IllegalArgumentException.class
			},
			// Crear un estilo logueado como dancer -> false
			{
				"dancer1", "reggaeton", IllegalArgumentException.class
			},
			// Crear un estilo logueado como academy -> false
			{
				"academy1", "reggaeton", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.styleCreateTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	@Test
	public void driverEditStyleTest() {

		final Object testingData[][] = {
			// Editar un estilo estando logueado como administrador -> true
			{
				"admin", "hiphop", null
			},
			// Editar un estilo sin estar logueado -> false
			{
				null, "hiphop", IllegalArgumentException.class
			},
			// Editar un estilo logueado como dancer -> false
			{
				"dancer1", "hiphop", IllegalArgumentException.class
			},
			// Editar un estilo logueado como academy -> false
			{
				"academy1", "hiphop", IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editStyleTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteStyleTest() {

		final Object testingData[][] = {
			// Borrar un estilo estando logueado como administrador -> true
			{
				"admin", 22, null
			},
			// Borrar un estilo sin estar logueado -> false
			{
				null, 22, IllegalArgumentException.class
			},
			// Borrar un estilo que se imparte en un curso -> false
			{
				"admin", 15, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteStyleTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
