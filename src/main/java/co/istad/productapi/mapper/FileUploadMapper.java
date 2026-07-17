package co.istad.productapi.mapper;

import co.istad.productapi.dto.file.FileResponse;
import co.istad.productapi.entity.FileUpload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class FileUploadMapper {
    @Value("${file.base-url}")
    protected String baseUrl;

    @Mapping(target = "url", expression = "java(generateUrl(fileUpload))")
    public abstract FileResponse mapToResponse(FileUpload fileUpload);
    protected String generateUrl(FileUpload fileUpload) {
        // http://localhost:8080/files/image-name.png
        return baseUrl +
                fileUpload.getName() + "." + fileUpload.getExtension();
    }
}
