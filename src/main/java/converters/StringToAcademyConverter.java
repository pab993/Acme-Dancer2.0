
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.AcademyRepository;
import domain.Academy;

@Component
@Transactional
public class StringToAcademyConverter implements Converter<String, Academy> {

	@Autowired
	AcademyRepository	academyRepository;


	@Override
	public Academy convert(final String text) {
		Academy result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.academyRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
