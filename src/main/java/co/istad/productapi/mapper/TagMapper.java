package co.istad.productapi.mapper;

import co.istad.productapi.dto.tag.request.TagRequest;
import co.istad.productapi.dto.tag.response.TagResponse;
import co.istad.productapi.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagResponse toResponse(Tag tag);
    Tag toEntity(TagRequest tagRequest);
}