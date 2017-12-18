
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Style extends DomainEntity {

	// Attributes
	// =================================================================

	private String				name;
	private String				description;
	private Collection<String>	pictures;
	private Collection<String>	videos;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL.List(value = {})
	@ElementCollection
	public Collection<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<String> pictures) {
		this.pictures = pictures;
	}

	@URL.List(value = {})
	@ElementCollection
	public Collection<String> getVideos() {
		return this.videos;
	}

	public void setVideos(final Collection<String> videos) {
		this.videos = videos;
	}


	// Relationships
	// =================================================================

	private Collection<Course>	courses;


	@Valid
	@OneToMany(mappedBy = "style")
	public Collection<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(final Collection<Course> courses) {
		this.courses = courses;
	}

}
