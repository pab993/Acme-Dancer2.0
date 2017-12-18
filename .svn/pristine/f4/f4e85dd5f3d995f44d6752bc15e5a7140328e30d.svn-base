/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EndorserRecordService;
import domain.Curriculum;
import domain.EndorserRecord;

@Controller
@RequestMapping("/endorserRecord")
public class EndorserRecordController extends AbstractController {

	// Constructors 
	// =============================================================================

	public EndorserRecordController() {
		super();
	}


	// Services
	// ============================================================================

	@Autowired
	private EndorserRecordService	endorserRecordService;

	@Autowired
	private CurriculumService		curriculumService;


	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculumId) {
		ModelAndView result;
		final EndorserRecord endorserRecord = new EndorserRecord();

		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		endorserRecord.setCurriculum(curriculum);

		result = this.createEditModelAndView(endorserRecord);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int endorserRecordId) {
		ModelAndView result;
		final EndorserRecord endorserRecord = this.endorserRecordService.findOne(endorserRecordId);

		result = this.createEditModelAndView(endorserRecord);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EndorserRecord endorserRecord, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(endorserRecord, "endorserRecord.save.error");
			else {
				result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + endorserRecord.getCurriculum().getId());

				this.endorserRecordService.save(endorserRecord);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(endorserRecord, "endorserRecord.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final EndorserRecord endorserRecord) {

		return this.createEditModelAndView(endorserRecord, null);
	}

	private ModelAndView createEditModelAndView(final EndorserRecord endorserRecord, final String message) {

		final ModelAndView resul = new ModelAndView("endorserRecord/edit");

		resul.addObject("endorserRecord", endorserRecord);
		resul.addObject("message", message);
		return resul;
	}
}
