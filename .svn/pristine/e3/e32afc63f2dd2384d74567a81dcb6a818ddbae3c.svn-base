package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Style;




@Component
@Transactional
public class StyleToStringConverter implements Converter<Style, String>{
	
	@Override
	public String convert(Style style) {
		
		String result;
		
		if(style == null){
			result = null;
		}else{
			result = String.valueOf(style.getId());
		}
		return result;
	}

}
