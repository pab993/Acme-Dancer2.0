
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Trackson;

@Service
@Transactional
public class AdministratorService {

	//Managed Repository =============================================================================

	@Autowired
	private AdministratorRepository	administratorRepository;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	//Other Business Methods =========================================================================

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Administrator result;

		result = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Administrator saveProfile(final Administrator administrator) {
		Assert.notNull(administrator);
		Administrator result;

		result = this.administratorRepository.save(administrator);

		return result;
	}

	public Administrator findByDalem(Trackson dalem) {
		Administrator result;

		result = administratorRepository.findByDalemId(dalem.getId());

		return result;
	}

}
