
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Apply;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer> {

	@Query("select a from Apply a where a.dancer.id = ?1")
	Collection<Apply> findAllByDancerId(int dancerId);

	@Query("select a from Apply a where a.course.academy.id = ?1")
	Collection<Apply> findAllByAcademyId(int academyId);

	@Query("select min(c.applies.size) from Course c")
	Double minApplicationsPerCourse();

	@Query("select avg(c.applies.size) from Course c")
	Double avgApplicationsPerCourse();

	@Query("select max(c.applies.size) from Course c")
	Double maxApplicationsPerCourse();
	
	@Query("select stddev(c.applies.size) from Course c")
	Double stddevApplicationsPerCourse();

}
