
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CustomRecord;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomRecordServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private CustomRecordService	customRecordService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an customRecord, the system must check every field of the edit form.
	 * 
	 * En este test, se comprueba si la validez de los customRecord, así el sistema debe validar que los campos introducidos son correctos
	 */

	/*
	 * Edit a customRecord or create a new one.
	 * 
	 * En este caso de uso un candidato puede crear/editar un nuevo customRecord siempre y cuando el curriculum le pertenezca.
	 */

	public void editCustomRecordTest(final String username, final int customRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final CustomRecord customRecord = this.customRecordService.findOne(customRecordId);

			this.customRecordService.save(customRecord);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverEditCustomRecordTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 46, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"dancer1", 46, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"dancer1", 49, IllegalArgumentException.class
			},
			// Edición por alguien que no es 'dancer' -> false
			{
				"academy1", 46, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editCustomRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
