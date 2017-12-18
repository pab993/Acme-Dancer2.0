
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Dancer;

@Repository
public interface DancerRepository extends JpaRepository<Dancer, Integer> {

	
	@Query("select d from Dancer d where d.userAccount.id = ?1")
	Dancer findByUserAccountId(int userAccountId);
	
	@Query("select (select count(d1)*1.0 from Dancer d1 where d1.curricula.size>=1)/count(d)*1.0 from Dancer d")
	Double ratioDancerWithCurricula();
}



