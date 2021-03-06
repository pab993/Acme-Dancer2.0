
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("select c from Course c where c.academy.id = ?1")
	Collection<Course> findAllByAcademy(int academyId);

	@Query("select c from Course c where c.style.id = ?1")
	Collection<Course> findAllByStyle(int styleId);

	@Query("select c from Course c where c.title like %?1% or c.style.name like %?1% or c.style.description like %?1%")
	Collection<Course> searchCourseByWords(String keyWord);

	@Query("select min(c.courses.size) from Academy c")
	Double minCoursesPerAcademy();

	@Query("select avg(c.courses.size) from Academy c")
	Double avgCoursesPerAcademy();

	@Query("select max(c.courses.size) from Academy c")
	Double maxCoursesPerAcademy();

	@Query("select stddev(c.courses.size) from Academy c")
	Double stddevCoursesPerAcademy();

	@Query("select (select 1.0*count(c1) from Course c1 where c1.tracksons is not empty)/count(*) from Course c")
	Double ratioCoursesWithATrackson();
}
