package converters;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.DancerRepository;
import domain.Dancer;

@Component
@Transactional
public class StringToDancerConverter implements Converter<String, Dancer>{
@Autowired DancerRepository dancerRepository;

	@Override
	public Dancer convert(String text) {
		Dancer result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = dancerRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}