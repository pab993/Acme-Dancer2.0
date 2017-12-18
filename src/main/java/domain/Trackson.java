
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Trackson extends DomainEntity {

	// Attributes
	// =====================================================

	private String	name;
	private Date	displayMoment;
	private String	title;
	private String	description;
	private Integer	score;

	private String	justification;
	private boolean	cancel;


	// Constructor
	// =====================================================

	public Trackson() {
		super();
	}

	// Getters & Setters
	// =====================================================

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp = "^[a-zA-Z0-9_]{3}[0-9]{3}$")
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Size(min = 1, max = 20)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Size(min = 1, max = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getDisplayMoment() {
		return this.displayMoment;
	}

	public void setDisplayMoment(final Date displayMoment) {
		this.displayMoment = displayMoment;
	}

	@NotNull
	@Range(min = -1, max = 1)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getJustification() {
		return this.justification;
	}

	public void setJustification(final String justification) {
		this.justification = justification;
	}

	public boolean getCancel() {
		return this.cancel;
	}

	public void setCancel(final boolean cancel) {
		this.cancel = cancel;
	}


	// Relationships
	// ====================================================================================

	private Administrator	administrator;
	private Course			course;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	//	@NotFound(action = NotFoundAction.IGNORE)
	//	@JoinColumn(name = "administrator_id")
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(final Administrator administrator) {
		this.administrator = administrator;
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

	//import javax.persistence.FetchType;
	//import javax.persistence.JoinColumn;
	//import org.hibernate.annotations.NotFound;
	//import org.hibernate.annotations.NotFoundAction;
	//import org.hibernate.validator.constraints.Range;

}
