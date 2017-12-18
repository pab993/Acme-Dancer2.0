
package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CourseService;
import services.TracksonService;
import domain.Academy;
import domain.Actor;
import domain.Course;
import domain.Dancer;
import domain.Trackson;

@Controller
@RequestMapping("/trackson")
public class TracksonController extends AbstractController {

	// Services
	// ===============================================================================

	@Autowired
	private TracksonService	tracksonService;

	@Autowired
	private CourseService	courseService;

	//	@Autowired
	//	private AdministratorService	administratorService;

	@Autowired
	private ActorService	actorService;


	// Constructor
	// ============================================================================

	public TracksonController() {
		super();
	}

	// Listing
	// ============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Actor principal = this.actorService.findByPrincipal();
		//		Collection<Authority> myAuth = principal.getUserAccount().getAuthorities();
		//		
		//		Collection<Authority> auts = new ArrayList<Authority>();
		//		Authority au = new Authority();
		//		Authority au2 = new Authority();
		//		au.setAuthority("CHORBI");
		//		au2.setAuthority("MANAGER");
		//		auts.add(au);
		//		auts.add(au2);
		//		
		Assert.notNull(principal);

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis());

		Collection<Trackson> tracksons;

		tracksons = this.tracksonService.findAll();

		result = new ModelAndView("trackson/list");

		result.addObject("tracksons", tracksons);
		result.addObject("currentMoment", currentMoment);
		result.addObject("principal", principal);
		//		result.addObject("auts", auts);
		//		result.addObject("myAuth", myAuth);

		result.addObject("requestURI", "trackson/list.do");

		return result;
	}

	@RequestMapping(value = "/listByCourse", method = RequestMethod.GET)
	public ModelAndView listByEvent(@RequestParam final int courseId) {
		ModelAndView result;
		Boolean cancel = false;
		Collection<Trackson> tracksons;

		final Actor principal = this.actorService.findByPrincipal();
		Boolean actorVar = false;
		if (principal instanceof Dancer || principal instanceof Academy || principal == null)
			actorVar = true;

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis());

		tracksons = this.tracksonService.findByCourse(courseId);

		if (tracksons.isEmpty())
			cancel = true;

		result = new ModelAndView("trackson/list");

		result.addObject("tracksons", tracksons);
		result.addObject("currentMoment", currentMoment);
		result.addObject("principal", principal);
		result.addObject("actorVar", actorVar);
		result.addObject("cancel", cancel);
		result.addObject("requestURI", "trackson/listByCourse.do");

		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int courseId) {
		ModelAndView result;
		Trackson trackson;
		final Course course = this.courseService.findOne(courseId);

		trackson = this.tracksonService.create(course);

		result = this.createEditModelAndView(trackson);

		return result;
	}

	//Editing
	// ===============================================================================

	@RequestMapping(value = "/editCancel", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tracksonId) {
		ModelAndView result;
		Trackson trackson;

		trackson = this.tracksonService.findOne(tracksonId);

		result = this.createCancelEditModelAndView(trackson);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Trackson trackson, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(trackson);
		else
			try {

				this.tracksonService.save(trackson);
				result = new ModelAndView("redirect:/trackson/list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(trackson, "trackson.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/editCancel", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCancel(@Valid final Trackson trackson, final BindingResult binding) {
		ModelAndView result;

		//		trackson.setAdministrator(this.administratorService.findByTrackson(trackson));
		tracksonService.checkJustification(trackson, binding);

		if (binding.hasErrors())
			result = this.createCancelEditModelAndView(trackson);
		else
			try {

				this.tracksonService.cancelTrackson(trackson);
				result = new ModelAndView("redirect:/trackson/list.do");

			} catch (final Throwable oops) {

				result = this.createCancelEditModelAndView(trackson, "trackson.commit.error");

			}

		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final Trackson trackson) {
		ModelAndView result;

		result = this.createEditModelAndView(trackson, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Trackson trackson, final String message) {
		ModelAndView result;

		result = new ModelAndView("trackson/edit");

		result.addObject("trackson", trackson);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createCancelEditModelAndView(final Trackson trackson) {
		ModelAndView result;

		result = this.createCancelEditModelAndView(trackson, null);
		return result;
	}

	protected ModelAndView createCancelEditModelAndView(final Trackson trackson, final String message) {
		ModelAndView result;

		result = new ModelAndView("trackson/editCancel");

		result.addObject("trackson", trackson);
		result.addObject("message", message);

		return result;
	}

}
