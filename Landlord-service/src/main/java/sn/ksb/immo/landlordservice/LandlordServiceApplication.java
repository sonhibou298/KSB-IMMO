package sn.ksb.immo.landlordservice;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.Date;

@SpringBootApplication(scanBasePackages = {"sn.ksb.immo.landlordservice.landlord"})
public class LandlordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandlordServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        //add a converter to convert a String to a Date
        mapper.addConverter(new Converter<String, Date>() {
            @Override
            public Date convert(MappingContext<String, Date> context) {
                try {
                    return DateFormat.getDateInstance().parse(context.getSource());
                } catch (Exception e) {
                    return null;
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
