
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Dancer extends Actor {

	//Attributes 
	// =================================================================

	//Relationships
	// =======================================================

	private Collection<Apply>		applies;
	private Collection<Curriculum>	curricula;


	@Valid
	@OneToMany(mappedBy = "dancer")
	public Collection<Apply> getApplies() {
		return this.applies;
	}

	public void setApplies(final Collection<Apply> applies) {
		this.applies = applies;
	}

	@Valid
	@OneToMany(mappedBy = "dancer")
	public Collection<Curriculum> getCurricula() {
		return this.curricula;
	}

	public void setCurricula(final Collection<Curriculum> curricula) {
		this.curricula = curricula;
	}
}
