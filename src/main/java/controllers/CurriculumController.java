
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CurriculumService;
import services.DancerService;
import domain.Curriculum;
import domain.CustomRecord;
import domain.Dancer;
import domain.EndorserRecord;
import domain.StyleRecord;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private DancerService		dancerService;


	// Constructors
	// ============================================================================

	public CurriculumController() {
		super();
	}

	// List
	// ============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Curriculum> curricula;

		curricula = this.curriculumService.findAllByDancerId(this.actorService.findByPrincipal().getId());

		result = new ModelAndView("curriculum/list");
		result.addObject("curricula", curricula);
		result.addObject("requestURI", "curriculum/list.do");

		return result;
	}

	// Display
	// ============================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final Integer curriculumId) {

		ModelAndView result;
		Curriculum curriculum;
		Collection<StyleRecord> styleRecords;
		Collection<CustomRecord> customRecords;
		Collection<EndorserRecord> endorserRecords;

		final Dancer principal = this.dancerService.findByPrincipal();

		curriculum = this.curriculumService.findOne(curriculumId);
		styleRecords = curriculum.getStyleRecords();
		customRecords = curriculum.getCustomRecords();
		endorserRecords = curriculum.getEndorserRecords();

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculum);
		result.addObject("styleRecords", styleRecords);
		result.addObject("customRecords", customRecords);
		result.addObject("endorserRecords", endorserRecords);
		result.addObject("principal", principal);

		return result;
	}

	// Create
	// =================================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Curriculum curriculum = this.curriculumService.create();

		result = this.createEditModelAndView(curriculum);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculumId) {
		ModelAndView result;
		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		result = this.createEditModelAndView(curriculum);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curriculum curriculum, final BindingResult binding) {
		ModelAndView result;
		String whatsappNumber;
		String email;
		Boolean isCorrectWhatsappNumberOrEmail;

		whatsappNumber = curriculum.getWhatsappNumber();
		email = curriculum.getEmail();

		isCorrectWhatsappNumberOrEmail = this.curriculumService.comprobarPhoneAndEmail(whatsappNumber, email);
		try {

			if (binding.hasErrors() || isCorrectWhatsappNumberOrEmail == false)
				result = this.createEditModelAndView(curriculum, "curriculum.save.error2");
			else {
				result = new ModelAndView("redirect:/curriculum/list.do");
				this.curriculumService.save(curriculum);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(curriculum, "curriculum.save.error2");
		}

		return result;
	}

	//Deleting -------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Curriculum curriculum, final BindingResult binding) {
		ModelAndView result;

		try {
			this.curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:/curriculum/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(curriculum, "curriculum.save.error");
		}
		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Curriculum curriculum) {

		return this.createEditModelAndView(curriculum, null);
	}

	private ModelAndView createEditModelAndView(final Curriculum curriculum, final String message) {

		final ModelAndView resul = new ModelAndView("curriculum/edit");

		resul.addObject("curriculum", curriculum);
		resul.addObject("message", message);
		return resul;
	}
}
