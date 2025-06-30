package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.ImageRequestDto;
import nl.benjamin.muziekmarktplaats.dto.ImageResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.ImageMapper;
import nl.benjamin.muziekmarktplaats.model.Image;
import nl.benjamin.muziekmarktplaats.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository repos;
    private final ImageMapper mapper;

    public ImageService(ImageRepository repos, ImageMapper mapper) {
        this.repos = repos;
        this.mapper = mapper;
    }

    public ImageResponseDto saveImage(ImageRequestDto imageRequestDto) {
        Image image = mapper.toEntity(imageRequestDto);

        Image savedImage = repos.save(image);

        ImageResponseDto dto = mapper.toResponseDto(savedImage);

        return dto;
    }

    public List<ImageResponseDto> getAllImages() {
        List<Image> imageList = repos.findAll();
        List<ImageResponseDto> imageDtoList = new ArrayList<>();

        for(Image image : imageList) {
            ImageResponseDto dto = mapper.toResponseDto(image);
            imageDtoList.add(dto);
        }

        return imageDtoList;
    }

    public ImageResponseDto getImageById(Long id) {
        Optional<Image> image = repos.findById(id);

        if (image.isPresent()) {
            return mapper.toResponseDto(image.get());
        } else {
            throw new RecordNotFoundException("No image with id " + id);
        }
    }

    public ImageResponseDto updateImage(Long id, ImageRequestDto imageRequestDto) {
        Optional<Image> imageOptional = repos.findById(id);

        if(imageOptional.isPresent()) {
            Image image = imageOptional.get();
            image.setName(imageRequestDto.getName());

            Image returnImage = repos.save(image);

            return mapper.toResponseDto(returnImage);
        } else {
            throw new RecordNotFoundException("No image with id " + id);
        }
    }
}
