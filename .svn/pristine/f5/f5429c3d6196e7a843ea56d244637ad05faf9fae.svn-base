
package services;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.DancerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Apply;
import domain.Dancer;
import forms.DancerForm;

@Service
@Transactional
public class DancerService {

	//Managed Repository =============================================================================

	@Autowired
	private DancerRepository	dancerRepository;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Dancer create() {
		Dancer result;
		UserAccount userAccount;
		Authority authority;
		Collection<Apply> applies;

		authority = new Authority();
		userAccount = new UserAccount();
		applies = new HashSet<Apply>();

		authority.setAuthority("DANCER");
		userAccount.addAuthority(authority);

		result = new Dancer();

		result.setUserAccount(userAccount);
		result.setApplies(applies);

		return result;
	}

	public Dancer save(final Dancer dancer) {
		Assert.notNull(dancer);
		Assert.notNull(dancer.getUserAccount());
		Dancer result;

		result = this.dancerRepository.save(dancer);

		return result;
	}

	//Other Business Methods =========================================================================

	public Dancer findByPrincipal() {
		Dancer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Dancer findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Dancer result;

		result = this.dancerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Dancer saveProfile(final Dancer dancer) {
		Assert.notNull(dancer);
		Dancer result;

		result = this.dancerRepository.save(dancer);

		return result;
	}

	public Dancer reconstruct(final DancerForm dancerForm, final BindingResult binding) {

		Dancer result;

		result = this.create();
		result.getUserAccount().setUsername(dancerForm.getUsername());
		result.setName(dancerForm.getName());
		result.setSurname(dancerForm.getSurname());
		result.setPhone(dancerForm.getPhone());
		result.setEmail(dancerForm.getEmail());
		result.setPostalAddress(dancerForm.getPostalAddress());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(dancerForm.getPassword(), null));

		this.comprobarContrasena(dancerForm.getPassword(), dancerForm.getRepeatPassword(), binding);
		this.comprobarPhone(dancerForm.getPhone(), binding);
		this.comprobarPostalAddress(dancerForm.getPostalAddress(), binding);

		return result;
	}

	private boolean comprobarContrasena(final String password, final String passwordRepeat, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordRepeat))
			result = password.equals(passwordRepeat);
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "dancer.password.mismatch";
			error = new FieldError("dancerForm", "password", password, false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	private boolean comprobarPostalAddress(final String postalAddress, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (postalAddress == null || postalAddress.isEmpty())
			result = true;
		else
			result = false;

		if (!result)
			if (postalAddress.matches("^[0-9]{5}$"))
				result = true;
			else {
				codigos = new String[1];
				codigos[0] = "company.postalAddress.error";
				error = new FieldError("dancerForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}

		return result;
	}

	private boolean comprobarPhone(final String phone, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (phone == null || phone.isEmpty())
			result = true;
		else
			result = false;

		if (!result)
			if (phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"))
				result = true;
			else {
				codigos = new String[1];
				codigos[0] = "dancer.phone.error";
				error = new FieldError("dancerForm", "phone", phone, false, codigos, null, "");
				binding.addError(error);
			}

		return result;
	}

	public Double ratioDancerWithCurricula(){
		
		Double res = dancerRepository.ratioDancerWithCurricula();
		
		return res;
		
	}
	
	
}
