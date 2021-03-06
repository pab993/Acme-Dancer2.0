package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplyRepository;
import domain.Academy;
import domain.Apply;
import domain.Course;
import domain.Dancer;

@Service
@Transactional
public class ApplyService {

	// Managed Repository
	// =============================================================================

	@Autowired
	private ApplyRepository applyRepository;

	// Supporting Services
	// =============================================================================

	@Autowired
	private DancerService dancerService;

	@Autowired
	private AcademyService academyService;

	// Constructor methods
	// ============================================================================

	// Simple CRUD methods
	// ============================================================================

	public Apply create(final Course course) {
		Apply result;
		Dancer principal;
		Date moment;

		principal = this.dancerService.findByPrincipal();
		Assert.notNull(principal);

		moment = new Date(System.currentTimeMillis());

		result = new Apply();
		result.setDancer(principal);
		result.setCourse(course);
		result.setCreateMoment(moment);
		result.setStatus("PENDING");

		return result;
	}

	public Apply save(final Apply apply) {
		Assert.notNull(apply);

		Apply result;
		Dancer principal;
		Date moment;

		moment = new Date(System.currentTimeMillis());
		Assert.isTrue(moment.before(apply.getCourse().getEndDate()));

		principal = this.dancerService.findByPrincipal();
		Assert.notNull(principal);

		for (final Apply a : principal.getApplies())
			if (a.getCourse().equals(apply.getCourse()))
				Assert.isTrue(a.getStatus().equals("REJECTED"));

		result = this.applyRepository.save(apply);

		return result;
	}

	public Apply saveTeacher(final Apply apply) {
		Assert.notNull(apply);
		Assert.notNull(apply.getCurriculum());

		Apply result;

		Dancer principal;
		principal = this.dancerService.findByPrincipal();
		Assert.notNull(principal);

		final Calendar moment = Calendar.getInstance();

		final Calendar startOneWeekBefore = Calendar.getInstance();
		startOneWeekBefore.setTime(apply.getCourse().getStartDate());
		startOneWeekBefore.add(Calendar.DAY_OF_YEAR, -7);

		Assert.isTrue(moment.before(startOneWeekBefore));

		for (final Apply a : principal.getApplies())
			if (a.getCourse().equals(apply.getCourse()))
				Assert.isTrue(a.getStatus().equals("REJECTED"));

		result = this.applyRepository.save(apply);

		return result;
	}

	public Apply saveStatus(final Apply apply) {
		Assert.notNull(apply);

		Apply result;
		Academy principal;

		principal = this.academyService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getCourses().contains(apply.getCourse()));

		result = this.applyRepository.save(apply);

		return result;
	}

	public Apply findOne(final int applyId) {
		Apply result;

		result = this.applyRepository.findOne(applyId);

		return result;
	}

	public Collection<Apply> findAll() {
		Collection<Apply> result;

		result = this.applyRepository.findAll();

		return result;
	}

	// Other Business Methods
	// =========================================================================

	public Collection<Apply> findAllByDancer(final Dancer dancer) {
		Collection<Apply> result;

		result = this.applyRepository.findAllByDancerId(dancer.getId());

		return result;
	}

	public Collection<Apply> findAllByAcademy(final Academy academy) {
		Collection<Apply> result;

		result = this.applyRepository.findAllByAcademyId(academy.getId());

		return result;
	}

	public String getMinAvgDevMaxApplicationsPerCourse() {
		String result;

		result = "Min: " + this.applyRepository.minApplicationsPerCourse()
				+ " Avg: " + this.applyRepository.avgApplicationsPerCourse()
				+ " Max" + this.applyRepository.maxApplicationsPerCourse()
				+ " Stddev "
				+ this.applyRepository.stddevApplicationsPerCourse();

		return result;
	}
}
