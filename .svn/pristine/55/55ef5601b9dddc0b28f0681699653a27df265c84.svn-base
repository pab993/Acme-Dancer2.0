
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.CustomRecordRepository;
import domain.CustomRecord;
import domain.Dancer;

@Service
@Transactional
public class CustomRecordService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private CustomRecordRepository	customRecordRepository;

	@Autowired
	private DancerService			dancerService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public CustomRecord findOne(final int customRecordId) {
		CustomRecord result;

		result = this.customRecordRepository.findOne(customRecordId);

		return result;
	}

	public Collection<CustomRecord> findAll() {
		Collection<CustomRecord> result;

		result = this.customRecordRepository.findAll();

		return result;
	}

	public CustomRecord save(final CustomRecord customRecord) {
		Assert.notNull(customRecord);
		CustomRecord result;

		final Dancer principal = this.dancerService.findByPrincipal();
		Assert.isTrue(principal.equals(customRecord.getCurriculum().getDancer()));

		result = this.customRecordRepository.save(customRecord);

		return result;
	}

	public void delete(final CustomRecord customRecord) {
		Assert.notNull(customRecord);
		Dancer principal;

		principal = this.dancerService.findByPrincipal();
		Assert.notNull(principal);

		this.customRecordRepository.delete(customRecord);
	}

	// Other Business Methods
	// =========================================================================

	public CustomRecord reconstruct(final CustomRecord customRecord, final BindingResult binding) {

		this.checkUrlLinks(customRecord.getLinks(), binding);

		return customRecord;
	}

	public boolean checkUrlLinks(final Collection<String> links, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		for (final String s : links)
			if (!s.matches("^(?:http(s)?:\\/\\/)?[a-zA-Z0-9_.-]+(?:[.][a-zA-Z0-9_\\.-]+)+[a-zA-Z0-9-._~:\\/?#[/]@!/$&'/(/)/*/+,;=.]+?$")) {
				result = true;
				break;
			}

		if (result) {
			codigos = new String[1];
			codigos[0] = "customRecord.links.error";
			error = new FieldError("customRecord", "links", links, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

}
