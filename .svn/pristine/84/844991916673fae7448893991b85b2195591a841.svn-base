
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Apply extends DomainEntity {

	//Attributes 
	// =================================================================

	private Date	createMoment;
	private String	status;


	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCreateMoment() {
		return this.createMoment;
	}

	public void setCreateMoment(final Date createMoment) {
		this.createMoment = createMoment;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships
	// =================================================================

	private Dancer		dancer;
	private Course		course;
	private Curriculum	curriculum;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Dancer getDancer() {
		return this.dancer;
	}

	public void setDancer(final Dancer dancer) {
		this.dancer = dancer;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(final Course course) {
		this.course = course;
	}

	@Valid
	@ManyToOne(optional = true)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
