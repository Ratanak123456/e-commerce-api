package co.istad.productapi.service;

import co.istad.productapi.dto.TagRequest;
import co.istad.productapi.dto.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    // request, response
    TagResponse createTag(TagRequest request);
    Page<TagResponse> getAllTags(Pageable pageable);
}
