
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DancerService;
import domain.Dancer;
import forms.DancerForm;

@Controller
@RequestMapping("/dancer")
public class DancerController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private DancerService	dancerService;


	//Constructors
	// ============================================================================

	public DancerController() {
		super();
	}

	// Edition Profile
	//=============================================================================

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		Dancer dancer;

		dancer = this.dancerService.findByPrincipal();
		Assert.notNull(dancer);

		result = this.createEditModelAndView2(dancer);

		return result;
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid final Dancer dancer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView2(dancer);
		else
			try {
				this.dancerService.saveProfile(dancer);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {

				result = this.createEditModelAndView2(dancer, "dancer.commit.error");
			}
		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("dancer/edit");

		result.addObject("dancerForm", new DancerForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final DancerForm dancerForm, final BindingResult binding) {
		ModelAndView result;
		Dancer dancer;

		try {
			dancer = this.dancerService.reconstruct(dancerForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(dancerForm, "dancer.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				dancer = this.dancerService.save(dancer);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(dancerForm, "dancer.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	//	private ModelAndView createEditModelAndView(final DancerForm dancerForm) {
	//
	//		return this.createEditModelAndView(dancerForm, null);
	//	}

	private ModelAndView createEditModelAndView(final DancerForm dancerForm, final String message) {

		final ModelAndView resul = new ModelAndView("dancer/edit");

		resul.addObject("dancerForm", dancerForm);
		resul.addObject("message", message);
		return resul;
	}

	private ModelAndView createEditModelAndView2(final Dancer dancer) {

		return this.createEditModelAndView2(dancer, null);
	}

	private ModelAndView createEditModelAndView2(final Dancer dancer, final String message) {

		final ModelAndView resul = new ModelAndView("dancer/editProfile");

		resul.addObject("dancer", dancer);
		resul.addObject("message", message);

		return resul;
	}
}
