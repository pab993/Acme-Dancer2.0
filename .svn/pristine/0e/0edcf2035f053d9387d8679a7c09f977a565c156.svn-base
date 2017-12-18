
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Academy;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Integer> {

	@Query("select a from Academy a where a.userAccount.id = ?1")
	Academy findByUserAccountId(int userAccountId);

	@Query("select a from Academy a join a.courses c where c.id = ?1")
	Academy findOneByCourse(int offerId);

}
