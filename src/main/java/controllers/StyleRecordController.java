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

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.StyleRecordService;
import services.StyleService;
import domain.Curriculum;
import domain.Style;
import domain.StyleRecord;

@Controller
@RequestMapping("/styleRecord")
public class StyleRecordController extends AbstractController {

	// Constructors 
	// =============================================================================

	public StyleRecordController() {
		super();
	}


	// Services
	// ============================================================================

	@Autowired
	private StyleRecordService	styleRecordService;

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private StyleService styleService;

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculumId) {
		ModelAndView result;
		final StyleRecord styleRecord = new StyleRecord();

		final Curriculum curriculum = this.curriculumService.findOne(curriculumId);

		styleRecord.setCurriculum(curriculum);

		result = this.createEditModelAndView(styleRecord);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int styleRecordId) {
		ModelAndView result;
		final StyleRecord styleRecord = this.styleRecordService.findOne(styleRecordId);

		result = this.createEditModelAndView(styleRecord);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final StyleRecord styleRecord, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(styleRecord, "styleRecord.save.error");
			else {
				result = new ModelAndView("redirect:/curriculum/display.do?curriculumId=" + styleRecord.getCurriculum().getId());

				this.styleRecordService.save(styleRecord);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(styleRecord, "styleRecord.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final StyleRecord styleRecord) {

		return this.createEditModelAndView(styleRecord, null);
	}

	private ModelAndView createEditModelAndView(final StyleRecord styleRecord, final String message) {

		Collection<Style> styles = styleService.findAll();
		final ModelAndView resul = new ModelAndView("styleRecord/edit");

		resul.addObject("styleRecord", styleRecord);
		resul.addObject("styles", styles);
		resul.addObject("message", message);
		return resul;
	}
}
