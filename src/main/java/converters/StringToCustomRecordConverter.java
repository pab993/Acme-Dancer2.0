
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.CustomRecordRepository;
import domain.CustomRecord;

@Component
@Transactional
public class StringToCustomRecordConverter implements Converter<String, CustomRecord> {

	@Autowired
	CustomRecordRepository	customRecordRepository;


	@Override
	public CustomRecord convert(final String text) {
		CustomRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.customRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
