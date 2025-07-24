package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.model.BeatFile;
import nl.benjamin.muziekmarktplaats.repository.BeatFileRepository;
import nl.benjamin.muziekmarktplaats.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@Service
public class BeatFileService {
    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final BeatFileRepository repos;


    public BeatFileService(@Value("${my.upload_location2}") String fileStorageLocation, BeatFileRepository repos) throws IOException {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.repos = repos;

        Files.createDirectories(fileStoragePath);
    }

    public String storeFile(MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        repos.save(new BeatFile(fileName));
        return fileName;
    }

    public Resource downLoadFile(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }
}













//private final ImageRepository repos;
//private final ImageMapper mapper;
//
//public ImageService(ImageRepository repos, ImageMapper mapper) {
//    this.repos = repos;
//    this.mapper = mapper;
//}
//
//public ImageResponseDto saveImage(ImageRequestDto imageRequestDto) {
//    Image image = mapper.toEntity(imageRequestDto);
//
//    Image savedImage = repos.save(image);
//
//    ImageResponseDto dto = mapper.toResponseDto(savedImage);
//
//    return dto;
//}
//
//public List<ImageResponseDto> getAllImages() {
//    List<Image> imageList = repos.findAll();
//    List<ImageResponseDto> imageDtoList = new ArrayList<>();
//
//    for(Image image : imageList) {
//        ImageResponseDto dto = mapper.toResponseDto(image);
//        imageDtoList.add(dto);
//    }
//
//    return imageDtoList;
//}
//
//public ImageResponseDto getImageById(Long id) {
//    Optional<Image> image = repos.findById(id);
//
//    if (image.isPresent()) {
//        return mapper.toResponseDto(image.get());
//    } else {
//        throw new RecordNotFoundException("No image with id " + id);
//    }
//}
//
//public ImageResponseDto updateImage(Long id, ImageRequestDto imageRequestDto) {
//    Optional<Image> imageOptional = repos.findById(id);
//
//    if(imageOptional.isPresent()) {
//        Image image = imageOptional.get();
//        image.setFileName(imageRequestDto.getName());
//
//        Image returnImage = repos.save(image);
//
//        return mapper.toResponseDto(returnImage);
//    } else {
//        throw new RecordNotFoundException("No image with id " + id);
//    }
//}
