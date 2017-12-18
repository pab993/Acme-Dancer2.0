
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	//Managed Repository =============================================================================

	@Autowired
	private ActorRepository	actorRepository;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	//Other Business Methods =========================================================================

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		try {
			userAccount = LoginService.getPrincipal();
			result = this.findByUserAccount(userAccount);
		} catch (Throwable exc) {
			result = null;
		}

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
