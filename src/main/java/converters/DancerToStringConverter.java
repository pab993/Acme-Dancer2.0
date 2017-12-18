package converters;


import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Dancer;
@Component
@Transactional
public class DancerToStringConverter implements Converter<Dancer, String>{

	@Override
	public String convert(Dancer dancer) {
		
		String result;
		if(dancer == null){
			result = null;
		}else{
			result = String.valueOf(dancer.getId());
		}
		return result;
	}

}