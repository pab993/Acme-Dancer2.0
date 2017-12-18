
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Trackson;

@Component
@Transactional
public class TracksonToStringConverter implements Converter<Trackson, String> {

	@Override
	public String convert(Trackson trackson) {

		String result;
		if (trackson == null) {
			result = null;
		} else {
			result = String.valueOf(trackson.getId());
		}
		return result;
	}

}
