
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	//Attributes 
	// =================================================================

	private String	name;
	private String	email;
	private String	whatsappNumber;
	private String	linkToLinkedIn;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Email
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	//	@Pattern(regexp = "^[+]([0-9]{1,3})?[0-9]{9}$")
	public String getWhatsappNumber() {
		return this.whatsappNumber;
	}

	public void setWhatsappNumber(final String whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@URL
	public String getLinkToLinkedIn() {
		return this.linkToLinkedIn;
	}
	public void setLinkToLinkedIn(final String linkToLinkedIn) {
		this.linkToLinkedIn = linkToLinkedIn;
	}


	//Relationships
	// =======================================================

	private Dancer						dancer;
	private Collection<Apply>			applies;
	private Collection<StyleRecord>		styleRecords;
	private Collection<CustomRecord>	customRecords;
	private Collection<EndorserRecord>	endorserRecords;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Dancer getDancer() {
		return this.dancer;
	}

	public void setDancer(final Dancer dancer) {
		this.dancer = dancer;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<Apply> getApplies() {
		return this.applies;
	}

	public void setApplies(final Collection<Apply> applies) {
		this.applies = applies;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<StyleRecord> getStyleRecords() {
		return this.styleRecords;
	}

	public void setStyleRecords(final Collection<StyleRecord> styleRecords) {
		this.styleRecords = styleRecords;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<CustomRecord> getCustomRecords() {
		return this.customRecords;
	}

	public void setCustomRecords(final Collection<CustomRecord> customRecords) {
		this.customRecords = customRecords;
	}

	@Valid
	@OneToMany(mappedBy = "curriculum")
	public Collection<EndorserRecord> getEndorserRecords() {
		return this.endorserRecords;
	}

	public void setEndorserRecords(final Collection<EndorserRecord> endorserRecords) {
		this.endorserRecords = endorserRecords;
	}
}
