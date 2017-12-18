
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Academy;

@Component
@Transactional
public class AcademyToStringConverter implements Converter<Academy, String> {

	@Override
	public String convert(final Academy academy) {

		String result;

		if (academy == null)
			result = null;
		else
			result = String.valueOf(academy.getId());
		return result;
	}

}
