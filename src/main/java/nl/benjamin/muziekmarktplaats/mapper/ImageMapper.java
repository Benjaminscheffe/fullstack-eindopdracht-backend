package nl.benjamin.muziekmarktplaats.mapper;

import nl.benjamin.muziekmarktplaats.dto.ImageRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ImageResponseDto;
import nl.benjamin.muziekmarktplaats.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public Image toEntity(ImageRequestDto imageRequestDto) {
        return new Image(imageRequestDto.name);
    }

    public ImageResponseDto toResponseDto(Image image) {
        ImageResponseDto imageDto = new ImageResponseDto();
        imageDto.id = image.getId();
        imageDto.name = image.getName();

        return imageDto;
    }
}
