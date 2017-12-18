
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.StyleRecord;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class StyleRecordServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private StyleRecordService	styleRecordService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an styleRecord, the system must check every field of the edit form.
	 * 
	 * En este test, se comprueba si la validez de los styleRecord, así el sistema debe validar que los campos introducidos son correctos
	 */

	/*
	 * Edit a styleRecord or create a new one.
	 * 
	 * En este caso de uso un candidato puede crear/editar un nuevo styleRecord siempre y cuando el curriculum le pertenezca.
	 */

	public void editStyleRecordTest(final String username, final int styleRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final StyleRecord styleRecord = this.styleRecordService.findOne(styleRecordId);

			this.styleRecordService.save(styleRecord);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverEditStyleRecordTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 41, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"dancer1", 41, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"dancer1", 44, IllegalArgumentException.class
			},
			// Edición por alguien que no es 'dancer' -> false
			{
				"academy1", 41, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editStyleRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
