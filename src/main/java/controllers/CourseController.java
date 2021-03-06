/*
 * courseController.java
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
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CourseService;
import services.StyleService;
import services.TracksonService;
import domain.Actor;
import domain.Course;
import domain.Style;

@Controller
@RequestMapping("/course")
public class CourseController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private CourseService	courseService;

	@Autowired
	private StyleService	styleService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private TracksonService	tracksonService;


	// Constructors
	// ============================================================================

	public CourseController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Course> courses;

		courses = this.courseService.findAll();

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis());

		result = new ModelAndView("course/list");
		result.addObject("courses", courses);
		result.addObject("currentMoment", currentMoment);
		result.addObject("requestURI", "course/list.do");

		return result;
	}

	// My List
	// =============================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		Collection<Course> courses;
		final Actor principal = this.actorService.findByPrincipal();

		courses = this.courseService.findAllByAcademy(principal.getId());

		result = new ModelAndView("course/myList");
		result.addObject("courses", courses);
		result.addObject("requestURI", "course/myList.do");

		return result;
	}

	// List by Academy
	// =============================================================================

	@RequestMapping(value = "/listByAcademy", method = RequestMethod.GET)
	public ModelAndView listByAcademy(@RequestParam final int academyId) {
		ModelAndView result;
		Collection<Course> courses;

		courses = this.courseService.findAllByAcademy(academyId);

		result = new ModelAndView("course/list");
		result.addObject("courses", courses);
		result.addObject("requestURI", "course/listByAcademy.do");

		return result;
	}

	// List by Style
	// =============================================================================

	@RequestMapping(value = "/listByStyle", method = RequestMethod.GET)
	public ModelAndView listByStyle(@RequestParam final int styleId) {
		ModelAndView result;
		Collection<Course> courses;

		courses = this.courseService.findAllByStyle(styleId);

		result = new ModelAndView("course/list");
		result.addObject("courses", courses);
		result.addObject("requestURI", "course/listByStyle.do");

		return result;
	}

	//SearchForm ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Course> courses;
		Boolean firstTime;

		courses = this.courseService.findAll();
		firstTime = true;

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis());

		result = new ModelAndView("course/search");
		result.addObject("courses", courses);
		result.addObject("requestURI", "course/searchForm.do");
		result.addObject("currentMoment", currentMoment);
		result.addObject("firstTime", firstTime);

		return result;
	}

	//Search ==============================================================================	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") final String keyword) {
		ModelAndView result;
		Collection<Course> courses;

		courses = this.courseService.getCourseByKeyWord(keyword);
		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis());

		result = new ModelAndView("course/search");
		result.addObject("courses", courses);
		result.addObject("currentMoment", currentMoment);

		return result;
	}

	// Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Course course;

		course = this.courseService.create();

		result = this.createEditModelAndView(course);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int courseId) {
		ModelAndView result;
		final Course course = this.courseService.findOne(courseId);

		result = this.createEditModelAndView(course);
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Course course, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(course, "course.save.error");
			else {
				result = new ModelAndView("redirect:/course/myList.do");
				this.courseService.save(course);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(course, "course.save.error");
		}

		return result;
	}

	// Deleting
	// ===========================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Course course, final BindingResult binding) {
		ModelAndView result;

		try {

			result = new ModelAndView("redirect:/course/myList.do");
			this.courseService.delete(course);

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(course, "course.delete.error");
		}

		return result;

	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Course course) {

		return this.createEditModelAndView(course, null);
	}

	private ModelAndView createEditModelAndView(final Course course, final String message) {

		Collection<Style> styles;

		if (this.styleService.findAll().isEmpty())
			styles = new ArrayList<Style>();
		else
			styles = this.styleService.findAll();

		final ModelAndView resul = new ModelAndView("course/edit");

		resul.addObject("course", course);
		resul.addObject("message", message);
		resul.addObject("styles", styles);
		return resul;
	}

}
