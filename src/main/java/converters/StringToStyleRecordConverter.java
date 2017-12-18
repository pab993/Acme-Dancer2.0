
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.StyleRecordRepository;
import domain.StyleRecord;

@Component
@Transactional
public class StringToStyleRecordConverter implements Converter<String, StyleRecord> {

	@Autowired
	StyleRecordRepository	styleRecordRepository;


	@Override
	public StyleRecord convert(final String text) {
		StyleRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.styleRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
