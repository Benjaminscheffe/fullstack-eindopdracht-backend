//package nl.benjamin.muziekmarktplaats.controller;
//
//import nl.benjamin.muziekmarktplaats.dto.ImageRequestDto;
//import nl.benjamin.muziekmarktplaats.dto.ImageResponseDto;
//import nl.benjamin.muziekmarktplaats.service.ImageService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/images")
//public class ImageController {
//
//    private final ImageService service;
//
//    public ImageController(ImageService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public ResponseEntity<ImageResponseDto> addImage(@RequestBody ImageRequestDto imageRequestDto) {
//        ImageResponseDto image = service.saveImage(imageRequestDto);
//
//        URI uri = URI.create(ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/" + image.id).toUriString());
//
//        return ResponseEntity.created(uri).body(image);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ImageResponseDto>> getAllImages() {
//        List<ImageResponseDto> images = service.getAllImages();
//
//        return ResponseEntity.ok().body(images);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ImageResponseDto> getImageById(@PathVariable("id") Long id) {
//        ImageResponseDto image = service.getImageById(id);
//
//        return ResponseEntity.ok().body(image);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ImageResponseDto> updateImage(@PathVariable("id") Long id, @RequestBody ImageRequestDto imageRequestDto) {
//        ImageResponseDto dto = service.updateImage(id, imageRequestDto);
//
//        return ResponseEntity.ok().body(dto);
//    }
//
//
//}
