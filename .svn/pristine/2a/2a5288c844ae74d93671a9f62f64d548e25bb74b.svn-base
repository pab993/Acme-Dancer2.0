package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.StyleRepository;
import domain.Actor;
import domain.Administrator;
import domain.Course;
import domain.Style;

@Service
@Transactional
public class StyleService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private StyleRepository styleRepository;

	@Autowired
	private ActorService actorService;

	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public Style create() {
		Actor principal = actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		Collection<Course> courses = new ArrayList<Course>();

		Style result = new Style();
		result.setCourses(courses);

		return result;
	}

	public Style save(final Style style) {
		Assert.notNull(style);

		Style result;

		Actor principal = actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		result = this.styleRepository.save(style);

		return result;
	}

	public void delete(Style style) {

		Assert.notNull(style);

		Actor principal = actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Assert.isTrue(style.getCourses().isEmpty());

		styleRepository.delete(style);

	}

	public Collection<Style> findAll() {
		Collection<Style> result;

		result = this.styleRepository.findAll();

		return result;
	}

	public Style findOne(final int styleId) {
		Style result;

		result = this.styleRepository.findOne(styleId);

		return result;
	}

	// Other Business Methods
	// =========================================================================

	public Collection<Style> findStylesOrderedByNumOfCourses() {

		Collection<Style> res = styleRepository
				.findStylesOrderedByNumOfCourses();

		return res;
	}
	
	public Collection<Object[]> findStylesOrderedByNumOfDancers() {

		Collection<Object[]> res = styleRepository
				.findStylesOrderedByNumOfDancers();
	
		return res;
	}

	public boolean checkUrlVideos(final Collection<String> videos,
			final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		for (final String s : videos)
			if (!s.matches("^(?:http(s)?:\\/\\/)?[a-zA-Z0-9_.-]+(?:[.][a-zA-Z0-9_\\.-]+)+[a-zA-Z0-9-._~:\\/?#[/]@!/$&'/(/)/*/+,;=.]+?$")) {
				result = true;
				break;
			}

		if (result) {
			codigos = new String[1];
			codigos[0] = "style.videos.error";
			error = new FieldError("style", "videos", videos, false, codigos,
					null, "");
			binding.addError(error);
		}

		return result;
	}

	public boolean checkUrlPictures(final Collection<String> pictures,
			final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result = false;

		for (final String s : pictures)
			if (!s.matches("^(?:http(s)?:\\/\\/)?[a-zA-Z0-9_.-]+(?:[.][a-zA-Z0-9_\\.-]+)+[a-zA-Z0-9-._~:\\/?#[/]@!/$&'/(/)/*/+,;=.]+?$")) {
				result = true;
				break;
			}

		if (result) {
			codigos = new String[1];
			codigos[0] = "style.pictures.error";
			error = new FieldError("style", "pictures", pictures, false,
					codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	public Style reconstruct(final Style style, final BindingResult binding) {

		this.checkUrlPictures(style.getPictures(), binding);
		this.checkUrlVideos(style.getVideos(), binding);

		return style;
	}
}
