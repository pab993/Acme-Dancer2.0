
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trackson;

@Repository
public interface TracksonRepository extends JpaRepository<Trackson, Integer> {

	@Query("select d from Trackson d where d.administrator.id = ?1 and d.course.id = ?2")
	Trackson findOneByAdministratorAndCourse(int actorId, int courseId);

	@Query("select d from Trackson d where d.course.id = ?1 and d.cancel = false")
	Collection<Trackson> findByCourse(int courseId);

	@Query("select d from Trackson d where d.course.id = ?1 and d.cancel = false")
	Collection<Trackson> findByCourse2(int courseId);

	@Query("select d from Trackson d where d.name = ?1")
	Trackson findByCode(String code);

}
