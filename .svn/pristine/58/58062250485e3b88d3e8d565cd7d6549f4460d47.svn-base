
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.CustomRecord;
import domain.Dancer;
import domain.EndorserRecord;
import domain.StyleRecord;

@Service
@Transactional
public class CurriculumService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private CurriculumRepository	curriculumRepository;

	@Autowired
	private DancerService			dancerService;

	@Autowired
	private CustomRecordService		customRecordService;

	@Autowired
	private EndorserRecordService	endorserRecordService;

	@Autowired
	private StyleRecordService		styleRecordService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public Curriculum findOne(final int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);

		return result;
	}

	public Collection<Curriculum> findAll() {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findAll();

		return result;
	}

	public Curriculum create() {
		Curriculum result;
		Dancer principal;

		result = new Curriculum();
		principal = this.dancerService.findByPrincipal();

		result.setDancer(principal);

		return result;
	}

	public Curriculum save(final Curriculum curriculum) {
		Assert.notNull(curriculum);
		Curriculum result;
		String whatsappNumber;
		String email;

		final Dancer principal = this.dancerService.findByPrincipal();
		Assert.isTrue(principal.equals(curriculum.getDancer()));

		whatsappNumber = curriculum.getWhatsappNumber();
		email = curriculum.getEmail();
		this.comprobarPhoneAndEmail(whatsappNumber, email);

		result = this.curriculumRepository.save(curriculum);
		System.out.print(result.getId());

		return result;
	}

	public void delete(final Curriculum curriculum) {
		Assert.notNull(curriculum);
		Dancer principal;
		Collection<CustomRecord> customRecords;
		Collection<EndorserRecord> endorserRecords;
		Collection<StyleRecord> styleRecords;

		principal = this.dancerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(curriculum.getDancer()));
		Assert.isInstanceOf(Dancer.class, principal);

		customRecords = curriculum.getCustomRecords();
		endorserRecords = curriculum.getEndorserRecords();
		styleRecords = curriculum.getStyleRecords();

		if (customRecords != null)
			for (final CustomRecord customRecord : customRecords)
				this.customRecordService.delete(customRecord);

		if (endorserRecords != null)
			for (final EndorserRecord endorserRecord : endorserRecords)
				this.endorserRecordService.delete(endorserRecord);

		if (styleRecords != null)
			for (final StyleRecord styleRecord : styleRecords)
				this.styleRecordService.delete(styleRecord);

		this.curriculumRepository.delete(curriculum);
	}

	// Other Business Methods
	// =========================================================================

	public Collection<Curriculum> findAllByDancerId(final int dancerId) {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findAllByDancerId(dancerId);

		return result;
	}

	public boolean comprobarPhoneVacio(final String phone) {
		boolean result;

		if (phone == null || phone.isEmpty())
			result = true;
		else
			result = false;

		return result;
	}

	public boolean comprobarPhone(final String phone) {
		boolean result;

		result = false;

		if (this.comprobarPhoneVacio(phone) == false)
			if (phone.matches("^[+]([0-9]{1,3})?[0-9]{9}$"))
				result = true;
			else
				result = false;

		return result;
	}

	public boolean comprobarEmail(final String email) {
		boolean result;

		if (email == null || email.isEmpty())
			result = false;
		else
			result = true;

		return result;
	}

	public Boolean comprobarPhoneAndEmail(final String phone, final String email) {
		Boolean result;

		result = false;

		if (this.comprobarPhone(phone) == false && this.comprobarEmail(email) == false)
			result = false;
		else{
			result = true;
		}

		return result;
	}
}
