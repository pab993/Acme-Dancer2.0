/*
 * applyController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
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
import services.ApplyService;
import services.CourseService;
import services.CurriculumService;
import domain.Academy;
import domain.Actor;
import domain.Apply;
import domain.Course;
import domain.Curriculum;
import domain.Dancer;

@Controller
@RequestMapping("/apply")
public class ApplyController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private ApplyService		applyService;

	@Autowired
	private CourseService		courseService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CurriculumService	curriculumService;


	// Constructors
	// ============================================================================

	public ApplyController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Apply> applies;
		Actor principal;

		principal = this.actorService.findByPrincipal();

		if (Dancer.class.isInstance(principal))
			applies = this.applyService.findAllByDancer((Dancer) principal);
		else
			applies = this.applyService.findAllByAcademy((Academy) principal);

		result = new ModelAndView("apply/list");
		result.addObject("applies", applies);
		result.addObject("requestURI", "apply/list.do");

		return result;
	}

	// Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int courseId) {
		ModelAndView result;
		Apply apply;
		Course course;

		course = this.courseService.findOne(courseId);
		apply = this.applyService.create(course);

		result = this.createEditModelAndViewCreate(apply);

		return result;
	}

	// Save Create
	// =============================================================================

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Apply apply, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndViewCreate(apply, "apply.create.error");
			else {
				result = new ModelAndView("redirect:/apply/list.do");
				this.applyService.save(apply);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreate(apply, "apply.create.error");
		}

		return result;
	}

	// Creating teacher
	// ===========================================================================

	@RequestMapping(value = "/createTeacher", method = RequestMethod.GET)
	public ModelAndView createTeacher(@RequestParam final int courseId) {
		ModelAndView result;
		Apply apply;
		Course course;

		course = this.courseService.findOne(courseId);
		apply = this.applyService.create(course);

		result = this.createEditModelAndViewCreateTeacher(apply);

		return result;
	}

	// Save Create
	// =============================================================================

	@RequestMapping(value = "/createTeacher", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreateTeacher(@Valid final Apply apply, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndViewCreateTeacher(apply, "apply.createTeacher.error");
			else {
				result = new ModelAndView("redirect:/apply/list.do");
				this.applyService.saveTeacher(apply);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndViewCreateTeacher(apply, "apply.createTeacher.error");
		}

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applyId) {
		ModelAndView result;
		final Apply apply = this.applyService.findOne(applyId);

		result = this.createEditModelAndView(apply);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Apply apply, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(apply, "apply.save.error");
			else {
				result = new ModelAndView("redirect:/apply/list.do");
				this.applyService.saveStatus(apply);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(apply, "apply.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndViewCreate(final Apply apply) {

		return this.createEditModelAndViewCreate(apply, null);
	}

	private ModelAndView createEditModelAndViewCreate(final Apply apply, final String message) {

		final ModelAndView result = new ModelAndView("apply/edit");

		result.addObject("apply", apply);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView createEditModelAndViewCreateTeacher(final Apply apply) {

		return this.createEditModelAndViewCreateTeacher(apply, null);
	}

	private ModelAndView createEditModelAndViewCreateTeacher(final Apply apply, final String message) {

		final ModelAndView result = new ModelAndView("apply/editTeacher");

		Collection<Curriculum> curriculums = new ArrayList<Curriculum>();
		curriculums = this.curriculumService.findAllByDancerId(apply.getDancer().getId());

		result.addObject("apply", apply);
		result.addObject("curriculums", curriculums);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView createEditModelAndView(final Apply apply) {

		return this.createEditModelAndView(apply, null);
	}

	private ModelAndView createEditModelAndView(final Apply apply, final String message) {

		final ModelAndView result = new ModelAndView("apply/editStatus");

		result.addObject("apply", apply);
		result.addObject("message", message);

		return result;
	}

}
