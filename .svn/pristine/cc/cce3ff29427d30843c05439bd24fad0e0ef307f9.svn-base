
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
import domain.Dancer;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DancerServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private DancerService	dancerService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a new dancer in our system, the system must check the username,
	 * the passwords, the name, the surname, the phone, the email and the postal address.
	 * 
	 * En este test, se comprueba el registro de un nuevo bailarín/a.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Register a new dancer.
	 * 
	 * En este caso de uso simularemos el registro de un candidato.
	 */

	public void dancerRegisterTest(final String username, final String password, final String passwordRepeat, final String name, final String surname, final String phone, final String email, final String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {

			final Dancer result = this.dancerService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			Assert.notNull(passwordRepeat);
			Assert.isTrue(password.equals(passwordRepeat));
			Assert.notNull(phone);
			Assert.isTrue(phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"));
			Assert.notNull(email);
			Assert.notNull(name);
			Assert.notNull(surname);

			result.getUserAccount().setUsername(username);
			result.setName(name);
			result.setSurname(surname);
			result.setPhone(phone);
			result.setEmail(email);
			result.setPostalAddress(postalAddress);
			result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

			this.dancerService.save(result);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverDancerRegisterTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				"dancerTest", "dancerTest", "dancerTest", "dancerTestName", "dancerTestSurname", "+ES1234456", "dancerTest@dancerTest.com", "12345", null
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, null, null, null, IllegalArgumentException.class
			},
			// Las contraseñas no coinciden -> false
			{
				"dancerTest", "dancerTest", "12345", "dancerTestName", "dancerTestSurname", "+ES1234456", "dancerTest@dancerTest.com", "12345", IllegalArgumentException.class
			},
			// Todos los campos completados, excepto la direccion postal -> true
			{
				"dancerTest", "dancerTest", "dancerTest", "dancerTestName", "dancerTestSurname", "+ES1234456", "dancerTest@dancerTest.com", "", null
			},
			// Patrón del telefono erroneo -> false
			{
				"dancerTest", "dancerTest", "dancerTest", "dancerTestName", "dancerTestSurname", "678574635", "dancerTest@dancerTest.com", "12345", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.dancerRegisterTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (Class<?>) testingData[i][8]);
	}

}
