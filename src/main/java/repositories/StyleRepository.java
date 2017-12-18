
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {



	@Query("select s from Style s order by s.courses.size DESC")
	Collection<Style> findStylesOrderedByNumOfCourses();
	
	@Query("select sr.reference, count(sr.curriculum.dancer) from StyleRecord sr group by sr.reference")
	Collection<Object[]> findStylesOrderedByNumOfDancers();


}
