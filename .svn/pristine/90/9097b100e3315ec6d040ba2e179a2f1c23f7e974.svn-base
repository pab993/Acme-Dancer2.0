
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Academy;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class AcademyServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private AcademyService	academyService;


	// Tests
	// ====================================================

	/*
	 * Browse the catalogue of academies and navigate to the courses that they offer.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar las academias que existen en nuestra aplicación.
	 */

	public void listOfAcademiesTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			this.academyService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * To check the validity of a new academy in our system, the system must check the username,
	 * the passwords, the name, the surname, the phone, the email and the postal address.
	 * 
	 * En este test, se comprueba el registro de una nueva academia.
	 * Para ello introducimos valores correctos e incorrectos para observar el comportamiento de la aplicación.
	 */

	/*
	 * Register a new academy.
	 * 
	 * En este caso de uso simularemos el registro de un candidato.
	 */

	public void academyRegisterTest(final String username, final String password, final String passwordRepeat, final String name, final String surname, final String phone, final String email, final String postalAddress, final String comercialName,
		final Class<?> expected) {
		Class<?> caught = null;

		try {

			final Academy result = this.academyService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			Assert.notNull(passwordRepeat);
			Assert.isTrue(password.equals(passwordRepeat));
			Assert.notNull(phone);
			Assert.isTrue(phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"));
			Assert.notNull(email);
			Assert.notNull(name);
			Assert.notNull(surname);
			Assert.notNull(comercialName);

			result.getUserAccount().setUsername(username);
			result.setName(name);
			result.setSurname(surname);
			result.setPhone(phone);
			result.setEmail(email);
			result.setPostalAddress(postalAddress);
			result.setComercialName(comercialName);
			result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

			this.academyService.save(result);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverListAcademyTest() {

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
			this.listOfAcademiesTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverAcademyRegisterTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				"academyTest", "academyTest", "academyTest", "academyTestName", "academyTestSurname", "+ES1234456", "academyTest@academyTest.com", "12345", "comercialName", null
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, null, null, null, null, IllegalArgumentException.class
			},
			// Las contraseñas no coinciden -> false
			{
				"academyTest", "academyTest", "12345", "academyTestName", "academyTestSurname", "+ES1234456", "academyTest@academyTest.com", "12345", "comercialName", IllegalArgumentException.class
			},
			// Todos los campos completados, excepto la direccion postal -> true
			{
				"academyTest", "academyTest", "academyTest", "academyTestName", "academyTestSurname", "+ES1234456", "academyTest@academyTest.com", "", "comercialName", null
			},
			// Patrón del telefono erroneo -> false
			{
				"academyTest", "academyTest", "academyTest", "academyTestName", "academyTestSurname", "678574635", "academyTest@academyTest.com", "12345", "comercialName", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.academyRegisterTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9]);
	}

}
