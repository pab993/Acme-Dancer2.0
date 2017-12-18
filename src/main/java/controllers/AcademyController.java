
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AcademyService;
import domain.Academy;
import forms.AcademyForm;

@Controller
@RequestMapping("/academy")
public class AcademyController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private AcademyService	academyService;


	//	@Autowired
	//	private CourseService	courseService;

	//Constructors
	// ============================================================================

	public AcademyController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Academy> academies;

		academies = this.academyService.findAll();

		result = new ModelAndView("academy/list");
		result.addObject("academies", academies);
		result.addObject("requestURI", "academy/list.do");

		return result;
	}

	// Display Academy
	// =============================================================================

	@RequestMapping(value = "/displayAcademy", method = RequestMethod.GET)
	public ModelAndView seeAcademy(@RequestParam final int courseId) {
		ModelAndView result;
		Academy academy;

		academy = this.academyService.findOneByCourse(courseId);

		result = new ModelAndView("academy/displayAcademy");
		result.addObject("academy", academy);
		result.addObject("requestURI", "course/displayAcademy.do");

		return result;
	}

	// Edition Profile
	//=============================================================================

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		Academy academy;

		academy = this.academyService.findByPrincipal();
		Assert.notNull(academy);

		result = this.createEditModelAndView2(academy);

		return result;
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid final Academy academy, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView2(academy);
		else
			try {
				this.academyService.saveProfile(academy);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {

				result = this.createEditModelAndView2(academy, "academy.commit.error");
			}
		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("academy/edit");

		result.addObject("academyForm", new AcademyForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AcademyForm academyForm, final BindingResult binding) {
		ModelAndView result;
		Academy academy;

		try {
			academy = this.academyService.reconstruct(academyForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(academyForm, "academy.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				academy = this.academyService.save(academy);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(academyForm, "academy.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	//	private ModelAndView createEditModelAndView(final AcademyForm academyForm) {
	//
	//		return this.createEditModelAndView(academyForm, null);
	//	}

	private ModelAndView createEditModelAndView(final AcademyForm academyForm, final String message) {

		final ModelAndView resul = new ModelAndView("academy/edit");

		resul.addObject("academyForm", academyForm);
		resul.addObject("message", message);
		return resul;
	}

	private ModelAndView createEditModelAndView2(final Academy academy) {

		return this.createEditModelAndView2(academy, null);
	}

	private ModelAndView createEditModelAndView2(final Academy academy, final String message) {

		final ModelAndView resul = new ModelAndView("academy/editProfile");

		resul.addObject("academy", academy);
		resul.addObject("message", message);

		return resul;
	}
}
