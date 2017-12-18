
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

import repositories.AcademyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Academy;
import domain.Course;
import forms.AcademyForm;

@Service
@Transactional
public class AcademyService {

	//Managed Repository =============================================================================

	@Autowired
	private AcademyRepository	academyRepository;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Academy findOne(final int AcademyId) {
		Academy result;

		result = this.academyRepository.findOne(AcademyId);

		return result;
	}
	
	
	public Collection<Academy> findAll() {
		Collection<Academy> result;

		result = this.academyRepository.findAll();

		return result;
	}

	public Academy create() {
		Academy result;
		UserAccount userAccount;
		Authority authority;
		Collection<Course> courses;

		authority = new Authority();
		userAccount = new UserAccount();
		courses = new HashSet<Course>();

		authority.setAuthority("ACADEMY");
		userAccount.addAuthority(authority);

		result = new Academy();

		result.setUserAccount(userAccount);
		result.setCourses(courses);

		return result;
	}

	public Academy save(final Academy academy) {
		Assert.notNull(academy);
		Assert.notNull(academy.getUserAccount());
		Academy result;

		result = this.academyRepository.save(academy);

		return result;
	}

	//Other Business Methods =========================================================================

	public Academy findOneByCourse(final int courseId) {
		Academy result;

		result = this.academyRepository.findOneByCourse(courseId);

		return result;
	}

	public Academy findByPrincipal() {
		Academy result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Academy findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Academy result;

		result = this.academyRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Academy saveProfile(final Academy academy) {
		Assert.notNull(academy);
		Academy result;

		result = this.academyRepository.save(academy);

		return result;
	}

	public Academy reconstruct(final AcademyForm academyForm, final BindingResult binding) {

		Academy result;

		result = this.create();
		result.getUserAccount().setUsername(academyForm.getUsername());
		result.setName(academyForm.getName());
		result.setSurname(academyForm.getSurname());
		result.setPhone(academyForm.getPhone());
		result.setEmail(academyForm.getEmail());
		result.setPostalAddress(academyForm.getPostalAddress());
		result.setComercialName(academyForm.getComercialName());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(academyForm.getPassword(), null));

		this.comprobarContrasena(academyForm.getPassword(), academyForm.getRepeatPassword(), binding);
		this.comprobarPhone(academyForm.getPhone(), binding);
		this.comprobarPostalAddress(academyForm.getPostalAddress(), binding);

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
			codigos[0] = "academy.password.mismatch";
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
				codigos[0] = "academy.postalAddress.error";
				error = new FieldError("academyForm", "postalAddress", postalAddress, false, codigos, null, "");
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
				codigos[0] = "academy.phone.error";
				error = new FieldError("academyForm", "phone", phone, false, codigos, null, "");
				binding.addError(error);
			}

		return result;
	}
}
