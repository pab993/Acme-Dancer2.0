
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.EndorserRecord;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class EndorserRecordServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private EndorserRecordService	endorserRecordService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an endorserRecord, the system must check every field of the edit form.
	 * 
	 * En este test, se comprueba si la validez de los endorserRecord, así el sistema debe validar que los campos introducidos son correctos
	 */

	/*
	 * Edit a endorserRecord or create a new one.
	 * 
	 * En este caso de uso un candidato puede crear/editar un nuevo endorserRecord siempre y cuando el curriculum le pertenezca.
	 */

	public void editEndorserRecordTest(final String username, final int endorserRecordId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			final EndorserRecord endorserRecord = this.endorserRecordService.findOne(endorserRecordId);

			this.endorserRecordService.save(endorserRecord);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverEditEndorserRecordTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 52, IllegalArgumentException.class
			},
			// Edición por parte del propietario del curriculum -> true
			{
				"dancer1", 52, null
			},
			// Edición por alguien que no es el propietario -> false
			{
				"dancer1", 54, IllegalArgumentException.class
			},
			// Edición por alguien que no es 'dancer' -> false
			{
				"academy1", 52, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editEndorserRecordTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
