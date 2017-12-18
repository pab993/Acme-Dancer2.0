
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class CustomRecord extends DomainEntity {

	//Attributes 
	// =================================================================

	private String				title;
	private String				pieceOfText;
	private Collection<String>	links;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPieceOfText() {
		return this.pieceOfText;
	}

	public void setPieceOfText(final String pieceOfText) {
		this.pieceOfText = pieceOfText;
	}

	@URL.List(value = {})
	@ElementCollection
	public Collection<String> getLinks() {
		return this.links;
	}

	public void setLinks(final Collection<String> links) {
		this.links = links;
	}


	//Relationships
	// =================================================================

	private Curriculum	curriculum;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
