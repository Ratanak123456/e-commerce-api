package co.istad.productapi.service.implement;

import co.istad.productapi.dto.tag.request.TagRequest;
import co.istad.productapi.dto.tag.response.TagResponse;
import co.istad.productapi.mapper.TagMapper;
import co.istad.productapi.repository.TagRepository;
import co.istad.productapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TagServiceImplement implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagResponse createTag(TagRequest request) {
        var tag =  tagMapper.toEntity(request);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    @Override
    public Page<TagResponse> getAllTags(Pageable pageable) {
        return  tagRepository
                .findAll(pageable)
                .map(tagMapper::toResponse);
    }

}
