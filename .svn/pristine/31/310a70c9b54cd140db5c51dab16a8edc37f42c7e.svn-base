package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Academy extends Actor {

	//Attributes 
	// =================================================================

	private String	comercialName;
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getComercialName() {
		return this.comercialName;
	}

	public void setComercialName(String comercialName) {
		this.comercialName = comercialName;
	}
	
	//Relationships
	// =======================================================

	private Collection<Course>	courses;


	@Valid
	@OneToMany(mappedBy = "academy")
	public Collection<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(final Collection<Course> courses) {
		this.courses = courses;
	}
}
