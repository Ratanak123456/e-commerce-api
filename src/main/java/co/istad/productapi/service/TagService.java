package co.istad.productapi.service;

import co.istad.productapi.dto.tag.request.TagRequest;
import co.istad.productapi.dto.tag.response.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    TagResponse createTag (TagRequest request);
    Page<TagResponse> getAllTags (Pageable pageable);
}
