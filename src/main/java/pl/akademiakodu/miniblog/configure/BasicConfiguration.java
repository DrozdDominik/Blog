package pl.akademiakodu.miniblog.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.akademiakodu.miniblog.model.dtos.PostDto;
import pl.akademiakodu.miniblog.model.dtos.TagDto;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.Tag;

@Configuration
public class BasicConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Post.class, PostDto.class)
                .addMapping(pst -> pst.getUser().getId(), PostDto::setIdOfUser)
                .addMapping(p -> p.getAudit().getAdded(), PostDto::setCreated);
        modelMapper.createTypeMap(Tag.class, TagDto.class)
                .addMapping(t -> t.getAudit().getAdded(), TagDto::setCreated);
        return modelMapper;
    }
}
