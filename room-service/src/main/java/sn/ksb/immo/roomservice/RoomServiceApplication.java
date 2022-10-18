package sn.ksb.immo.roomservice;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import sn.ksb.immo.roomservice.rooms.enums.RoomType;

@SpringBootApplication(scanBasePackages = {"sn.ksb.immo.roomservice.rooms"})
public class RoomServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        //add a converter to convert a String to a RoomType
        mapper.addConverter(new Converter<String, RoomType>() {
            @Override
            public RoomType convert(MappingContext<String, RoomType> context) {
                try {
                    return RoomType.valueOf(context.getSource());
                } catch (Exception e) {
                    return null;
                }
            }
        });
        return mapper;
    }

}
