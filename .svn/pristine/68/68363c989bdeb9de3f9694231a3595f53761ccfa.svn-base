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
import services.CustomRecordService;
import domain.Curriculum;
import domain.CustomRecord;

@Controller
@RequestMapping("/customRecord")
public class CustomRecordController extends AbstractController {

	// Constructors 
	// =============================================================================

	public CustomRecordController() {
		super();
	}


	// Services
	// ============================================================================

	@Autowired
	private CustomRecordService	customRecordService;

	@Autowired
	private CurriculumService	curriculumService;


	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculumId) {
		ModelAndView result;
		final CustomRecord customRecord = new CustomRecord();

		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		customRecord.setCurriculum(curriculum);

		result = this.createEditModelAndView(customRecord);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int customRecordId) {
		ModelAndView result;
		final CustomRecord customRecord = this.customRecordService.findOne(customRecordId);

		result = this.createEditModelAndView(customRecord);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CustomRecord customRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			final CustomRecord customRecordRec = this.customRecordService.reconstruct(customRecord, binding);
			if (binding.hasErrors())
				result = this.createEditModelAndView(customRecord, "customRecordRec.save.error");
			else {
				result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + customRecord.getCurriculum().getId());

				this.customRecordService.save(customRecordRec);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(customRecord, "customRecord.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final CustomRecord customRecord) {

		return this.createEditModelAndView(customRecord, null);
	}

	private ModelAndView createEditModelAndView(final CustomRecord customRecord, final String message) {

		final ModelAndView resul = new ModelAndView("customRecord/edit");

		resul.addObject("customRecord", customRecord);
		resul.addObject("message", message);
		return resul;
	}
}
