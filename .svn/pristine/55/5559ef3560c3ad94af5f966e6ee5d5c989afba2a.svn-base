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
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AcademyService;
import services.AdministratorService;
import services.ApplyService;
import services.CourseService;
import services.DancerService;
import services.StyleService;
import domain.Administrator;
import domain.Style;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}


	// Services
	// =============================================================================

	@Autowired
	private ApplyService			applyService;

	@Autowired
	private CourseService			courseService;

	@Autowired
	private AcademyService			academyService;

	@Autowired
	private AdministratorService	administratorService;
	
	@Autowired
	private DancerService	dancerService;
	
	@Autowired
	private StyleService	styleService;


	// Edition Profile
	//=============================================================================

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		result = this.createEditModelAndView2(administrator);

		return result;
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEditProfile(@ModelAttribute @Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView2(administrator);
		else
			try {
				this.administratorService.saveProfile(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {

				result = this.createEditModelAndView2(administrator, "administrator.commit.error");
			}
		return result;
	}
	// Dashboard
	// =============================================================================

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		String minAvgDevMaxCoursesPerAcademy;
		String minAvgDevMaxApplicationsPerCourse;
		Double ratioDancerWithCurricula;
		Collection<Style> findStylesOrderedByNumOfCourses;
		Collection<Object[]> findStylesOrderedByNumOfDancers;
		
		
		if (this.academyService.findAll().isEmpty() || this.courseService.findAll().isEmpty())
			minAvgDevMaxCoursesPerAcademy = "Min: 0, Avg: 0, Max: 0, Stddev: 0";
		else
			minAvgDevMaxCoursesPerAcademy = this.courseService.getMinAvgDevMaxCoursesPerAcademy();

		if (this.applyService.findAll().isEmpty() || this.courseService.findAll().isEmpty())
			minAvgDevMaxApplicationsPerCourse = "Min: 0, Avg: 0, Max: 0, Stddev: 0";
		else
			minAvgDevMaxApplicationsPerCourse = this.applyService.getMinAvgDevMaxApplicationsPerCourse();

		ratioDancerWithCurricula = dancerService.ratioDancerWithCurricula();
		findStylesOrderedByNumOfCourses = styleService.findStylesOrderedByNumOfCourses();
		findStylesOrderedByNumOfDancers = styleService.findStylesOrderedByNumOfDancers();
		
		result = new ModelAndView("administrator/dashboard");

		result.addObject("minAvgDevMaxCoursesPerAcademy", minAvgDevMaxCoursesPerAcademy);
		result.addObject("minAvgDevMaxApplicationsPerCourse", minAvgDevMaxApplicationsPerCourse);
		result.addObject("ratioDancerWithCurricula", ratioDancerWithCurricula);
		result.addObject("findStylesOrderedByNumOfCourses", findStylesOrderedByNumOfCourses);
		result.addObject("findStylesOrderedByNumOfDancers", findStylesOrderedByNumOfDancers);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView2(final Administrator administrator) {

		return this.createEditModelAndView2(administrator, null);
	}

	private ModelAndView createEditModelAndView2(final Administrator administrator, final String message) {

		final ModelAndView resul = new ModelAndView("administrator/editProfile");

		resul.addObject("administrator", administrator);
		resul.addObject("message", message);

		return resul;
	}
}
