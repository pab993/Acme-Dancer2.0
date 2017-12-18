
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.Contact;
import domain.Dancer;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;

	@Autowired
	private DancerService				dancerService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public EndorserRecord findOne(final int endorserRecordId) {
		EndorserRecord result;

		result = this.endorserRecordRepository.findOne(endorserRecordId);

		return result;
	}

	public Collection<EndorserRecord> findAll() {
		Collection<EndorserRecord> result;

		result = this.endorserRecordRepository.findAll();

		return result;
	}

	public EndorserRecord save(final EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		EndorserRecord result;

		final Dancer principal = this.dancerService.findByPrincipal();
		Assert.isTrue(principal.equals(endorserRecord.getCurriculum().getDancer()));

		Assert.isTrue(this.checkContact(endorserRecord.getContact()));

		result = this.endorserRecordRepository.save(endorserRecord);

		return result;
	}

	public void delete(final EndorserRecord endorserRecord) {
		Assert.notNull(endorserRecord);
		Dancer principal;

		principal = this.dancerService.findByPrincipal();
		Assert.notNull(principal);

		this.endorserRecordRepository.delete(endorserRecord);
	}

	// Other Business Methods
	// =========================================================================

	public boolean checkContact(final Contact contact) {
		Assert.notNull(contact);

		boolean result = true;

		if (contact.getMeans().equals("phone number")) {
			if (!(contact.getValue().matches("^[+]([0-9]{1,3})?[0-9]{9}$")))
				result = false;
		} else if (contact.getMeans().equals("postal address")) {
			if (!(contact.getValue().matches("^[0-9]{5}$")))
				result = false;
		} else if (contact.getMeans().equals("email"))
			if (!(contact.getValue().matches("^[a-zA-Z0-9]{4,25}@[a-zA-Z]{5,7}.[a-zA-Z]{2,5}$")))
				result = false;

		return result;
	}
}
