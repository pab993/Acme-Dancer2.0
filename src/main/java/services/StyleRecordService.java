
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StyleRecordRepository;
import domain.Dancer;
import domain.StyleRecord;

@Service
@Transactional
public class StyleRecordService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private StyleRecordRepository	styleRecordRepository;

	@Autowired
	private DancerService			dancerService;


	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public StyleRecord findOne(final int styleRecordId) {
		StyleRecord result;

		result = this.styleRecordRepository.findOne(styleRecordId);

		return result;
	}

	public Collection<StyleRecord> findAll() {
		Collection<StyleRecord> result;

		result = this.styleRecordRepository.findAll();

		return result;
	}

	public StyleRecord save(final StyleRecord styleRecord) {
		Assert.notNull(styleRecord);
		StyleRecord result;

		final Dancer principal = this.dancerService.findByPrincipal();
		Assert.isTrue(principal.equals(styleRecord.getCurriculum().getDancer()));

		result = this.styleRecordRepository.save(styleRecord);

		return result;
	}
	
	public void delete(StyleRecord styleRecord) {
		Assert.notNull(styleRecord);
		Dancer principal;
		
		principal = dancerService.findByPrincipal();
		Assert.notNull(principal);	
		
		styleRecordRepository.delete(styleRecord);		
	}

	// Other Business Methods
	// =========================================================================

}
