
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.StyleRecord;

@Component
@Transactional
public class StyleRecordToStringConverter implements Converter<StyleRecord, String> {

	@Override
	public String convert(final StyleRecord styleRecord) {

		String result;

		if (styleRecord == null)
			result = null;
		else
			result = String.valueOf(styleRecord.getId());
		return result;
	}

}
