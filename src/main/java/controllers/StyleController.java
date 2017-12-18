/* StyleController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
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

import services.StyleService;
import domain.Style;

@Controller
@RequestMapping("/style")
public class StyleController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private StyleService styleService;

	// Constructors
	// ============================================================================

	public StyleController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listStyle() {
		ModelAndView result;
		Collection<Style> styles;

		styles = this.styleService.findAll();

		result = new ModelAndView("style/list");
		result.addObject("styles", styles);
		result.addObject("requestURI", "style/list.do");

		return result;
	}

	// Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Style style;

		style = styleService.create();

		result = this.createEditModelAndView(style);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int styleId) {
		ModelAndView result;
		final Style style = this.styleService.findOne(styleId);

		result = this.createEditModelAndView(style);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Style style,
			final BindingResult binding) {
		ModelAndView result;

		try {
		      final Style styleRec = this.styleService.reconstruct(style, binding);
		      if (binding.hasErrors())
		        result = this.createEditModelAndView(styleRec, "style.save.error");
		      else {
		        result = new ModelAndView("redirect:/style/list.do");
		        this.styleService.save(styleRec);

		      }
		    } catch (final Throwable oops) {
		      result = this.createEditModelAndView(style, "style.save.error");
		    }

		return result;
	}

	// Deleting
	// ===========================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Style style, BindingResult binding) {
		ModelAndView result;

		try {

			result = new ModelAndView("redirect:/style/list.do");
			styleService.delete(style);

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(style, "style.delete.error");
		}

		return result;

	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Style style) {

		return this.createEditModelAndView(style, null);
	}

	private ModelAndView createEditModelAndView(final Style style,
			final String message) {

		final ModelAndView resul = new ModelAndView("style/edit");

		resul.addObject("style", style);
		resul.addObject("message", message);
		return resul;
	}

}
