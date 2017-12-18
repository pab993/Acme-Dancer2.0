
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class Contact {

	//Attributes 
	// =================================================================

	private String	means;
	private String	value;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getMeans() {
		return this.means;
	}

	public void setMeans(final String means) {
		this.means = means;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
}
