
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.TracksonRepository;
import domain.Trackson;

@Component
@Transactional
public class StringToTracksonConverter implements Converter<String, Trackson> {

	@Autowired
	TracksonRepository	tracksonRepository;


	@Override
	public Trackson convert(String text) {
		Trackson result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = tracksonRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
