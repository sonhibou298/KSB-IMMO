package sn.ksb.immo.ksbimmo.application.configs;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Configuration
public class GeneralConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        //convertir du String vers java.util.Date
        mapper.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(MappingContext<String, Date> mappingContext) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(mappingContext.getSource());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return mapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
