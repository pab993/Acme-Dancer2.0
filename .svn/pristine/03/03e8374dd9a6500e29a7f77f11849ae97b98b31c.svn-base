
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CourseRepository;
import domain.Academy;
import domain.Actor;
import domain.Course;

@Service
@Transactional
public class CourseService {

	//Managed Repository =============================================================================

	@Autowired
	private CourseRepository	courseRepository;

	//Supporting Services =============================================================================

	@Autowired
	private ActorService		actorService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Course create() {
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Academy.class, principal);

		final Course result = new Course();

		result.setAcademy((Academy) principal);

		return result;
	}

	public Course save(final Course course) {
		Assert.notNull(course);

		Course result;

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Academy.class, principal);

		result = this.courseRepository.save(course);

		return result;
	}

	public void delete(final Course course) {

		Assert.notNull(course);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Academy.class, principal);

		Assert.isTrue(course.getApplies().isEmpty());

		this.courseRepository.delete(course);

	}

	public Collection<Course> findAll() {
		Collection<Course> result;

		result = this.courseRepository.findAll();

		return result;
	}

	public Course findOne(final int courseId) {
		Course result;

		result = this.courseRepository.findOne(courseId);

		return result;
	}

	//Other Business Methods =========================================================================

	public Collection<Course> findAllByAcademy(final int academyId) {
		Collection<Course> result;

		result = this.courseRepository.findAllByAcademy(academyId);

		return result;
	}

	public Collection<Course> findAllByStyle(final int styleId) {
		Collection<Course> result;

		result = this.courseRepository.findAllByStyle(styleId);

		return result;
	}

	public Collection<Course> getCourseByKeyWord(final String keyWord) {
		Assert.notNull(keyWord);
		Collection<Course> result;

		result = this.courseRepository.searchCourseByWords(keyWord);

		return result;
	}

	public String getMinAvgDevMaxCoursesPerAcademy() {
		String result;

		result = "Min: " + this.courseRepository.minCoursesPerAcademy() + " Avg: " + this.courseRepository.avgCoursesPerAcademy() + " Max" + this.courseRepository.maxCoursesPerAcademy() + " Stddev " + this.courseRepository.stddevCoursesPerAcademy();

		return result;
	}

}
