
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	@Query("select c from Curriculum c where c.dancer.id = ?1")
	Collection<Curriculum> findAllByDancerId(int dancerId);
}
