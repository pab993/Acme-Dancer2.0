
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	//Relationships
	// =======================================================

	private Collection<Trackson>	tracksons;


	@OneToMany(mappedBy = "administrator")
	public Collection<Trackson> getTracksons() {
		return this.tracksons;
	}

	public void setTracksons(Collection<Trackson> tracksons) {
		this.tracksons = tracksons;
	}
}
