
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import repositories.TracksonRepository;
import domain.Administrator;
import domain.Course;
import domain.Trackson;

@Transactional
@Service
public class TracksonService {

	//Repository
	//======================================================================

	@Autowired
	private TracksonRepository		tracksonRepository;

	@Autowired
	private AdministratorService	administratorService;


	//Services
	//======================================================================

	// Constructor methods
	// =====================================================================

	public TracksonService() {
		super();
	}

	//CRUD methods
	//=======================================================================

	public Trackson findOne(final int id) {
		Assert.isTrue(this.tracksonRepository.exists(id));

		final Trackson trackson = this.tracksonRepository.findOne(id);
		Assert.notNull(trackson);

		return trackson;
	}

	public Collection<Trackson> findAll() {
		Collection<Trackson> result;

		result = this.tracksonRepository.findAll();

		return result;
	}

	//Other bussiness methods
	//=======================================================================

	public Trackson create(final Course course) {

		final Administrator principal = this.administratorService.findByPrincipal();

		Assert.notNull(principal);
		Assert.isInstanceOf(Administrator.class, principal);
		Assert.notNull(course);
		Assert.isTrue(course.getTracksons().isEmpty());

		final Trackson trackson = new Trackson();

		trackson.setDisplayMoment(new Date(System.currentTimeMillis() - 100));
		trackson.setName(this.codeGenerator());

		trackson.setAdministrator(principal);
		trackson.setCourse(course);
		trackson.setCancel(false);

		return trackson;
	}

	public Trackson save(final Trackson trackson) {
		// TODO Auto-generated method stub
		Assert.notNull(trackson);
		Assert.isInstanceOf(Administrator.class, this.administratorService.findByPrincipal());

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis());

		Assert.isTrue(trackson.getCourse().getEndDate().after(currentMoment) && trackson.getCourse().getStartDate().after(currentMoment));

		final Trackson saved = this.tracksonRepository.save(trackson);

		return saved;
	}

	//Other bussiness methods
	//=======================================================================

	public Trackson findOneByAdministratorAndCourse(final int administratorId, final int courseId) {
		// TODO Auto-generated method stub

		Assert.notNull(administratorId);
		Assert.notNull(courseId);

		final Trackson trackson = this.tracksonRepository.findOneByAdministratorAndCourse(administratorId, courseId);

		Assert.notNull(trackson);

		return trackson;
	}

	public Collection<Trackson> findByCourse(final int courseId) {
		Collection<Trackson> tracksons = new ArrayList<Trackson>();

		tracksons = this.tracksonRepository.findByCourse(courseId);

		return tracksons;
	}

	public void cancelTrackson(final Trackson trackson) {

		Assert.notNull(trackson);
		Assert.isInstanceOf(Administrator.class, this.administratorService.findByPrincipal());
		Assert.isTrue(!trackson.getJustification().isEmpty());
		trackson.setCancel(true);
		this.tracksonRepository.save(trackson);

	}

	public boolean checkJustification(Trackson trackson, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (StringUtils.isNotBlank(trackson.getJustification()))
			result = true;
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "justification.error";
			error = new FieldError("trackson", "justification", trackson.getJustification(), false, codigos, null, "");
			binding.addError(error);
		}

		return result;
	}

	public String codeGenerator() {
		String result = "";
		final String pattern = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890_";
		final String pattern2 = "0123456789";
		final Random rnd = new Random();
		//		final int nRnd = TracksonService.generaNumeroAleatorio(3);
		//		final int nRnd2 = TracksonService.generaNumeroAleatorio(3);

		for (int i = 0; i < 3; i++)
			result += pattern.charAt(rnd.nextInt(pattern.length()));
		for (int i = 0; i < 3; i++)
			result += pattern2.charAt(rnd.nextInt(pattern2.length()));

		final Trackson trackson = this.tracksonRepository.findByCode(result);

		if (trackson != null) {
			result = "";
			result = this.codeGenerator();
		}

		return result;
	}

	public static int generaNumeroAleatorio(final int minimo, final int maximo) {

		final int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
		return num;
	}


	//Reconstruct
	//=======================================================================

	@Autowired
	private Validator	validator;


	public Trackson reconstruct(final Trackson trackson, final BindingResult binding) {
		// TODO Auto-generated method stub

		Trackson resul;

		if (trackson.getId() == 0)
			resul = trackson;
		else
			resul = this.tracksonRepository.findOne(trackson.getId());

		this.validator.validate(resul, binding);

		return resul;
	}

}
