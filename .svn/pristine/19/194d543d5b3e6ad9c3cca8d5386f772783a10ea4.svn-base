
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.CustomRecord;

@Component
@Transactional
public class CustomRecordToStringConverter implements Converter<CustomRecord, String> {

	@Override
	public String convert(final CustomRecord customRecord) {

		String result;

		if (customRecord == null)
			result = null;
		else
			result = String.valueOf(customRecord.getId());
		return result;
	}

}
