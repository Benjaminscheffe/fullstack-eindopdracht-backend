package nl.benjamin.muziekmarktplaats.controller;

import jakarta.servlet.http.HttpServletRequest;
import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseUserDto;
import nl.benjamin.muziekmarktplaats.mapper.BeatMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import nl.benjamin.muziekmarktplaats.service.BeatFileService;
import nl.benjamin.muziekmarktplaats.service.BeatService;
import nl.benjamin.muziekmarktplaats.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

@CrossOrigin
@RestController
@RequestMapping("/beats")
public class BeatController {

    private final BeatService service;
    private final ImageService imageService;
    private final BeatMapper mapper;
    private final BeatFileService beatFileService;

    public BeatController(BeatService service, ImageService imageService, BeatMapper mapper, BeatFileService beatFileService) {
        this.service = service;
        this.imageService = imageService;
        this.mapper = mapper;
        this.beatFileService = beatFileService;
    }

    @PostMapping
    public ResponseEntity<BeatResponseDto> addBeat(@RequestBody BeatRequestDto beatRequestDto) {
        BeatResponseDto beat = service.saveBeat(beatRequestDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + beat.id).toUriString());

        return ResponseEntity.created(uri).body(beat);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<BeatResponseUserDto> addImageToBeat(@PathVariable("id") Long beatId,
                                                     @RequestBody MultipartFile file)
            throws IOException {
        System.out.println(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/beats/")
                .path(Objects.requireNonNull(beatId.toString()))
                .path("/image")
                .toUriString();
        String fileName = imageService.storeFile(file);
        BeatResponseUserDto beat = mapper.toResponseUserDto(service.assignImageToBeat(fileName, beatId));

        return ResponseEntity.created(URI.create(url)).body(beat);

    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getBeatPhoto(@PathVariable("id") Long beatId, HttpServletRequest request){
        Resource resource = service.getPhotoFromBeat(beatId);

        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            /*
            "application/octet-steam" is de generieke mime type voor byte data.
            Het is beter om een specifiekere mimetype te gebruiken, zoals "image/jpeg".
            Mimetype is nodig om de frontend te laten weten welke soort data het is.
            Met de juiste MimeType en Content-Disposition, kun je de plaatjes of PDFs die je upload
            zelfs in de browser weergeven.
             */
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @PostMapping("/{id}/file")
    public ResponseEntity<BeatResponseDto> addFileToBeat(@PathVariable("id") Long beatId,
                                                         @RequestBody MultipartFile file)
            throws IOException {
        System.out.println(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/beats/")
                .path(Objects.requireNonNull(beatId.toString()))
                .path("/file")
                .toUriString();
        String fileName = beatFileService.storeFile(file);
        BeatResponseDto beat = mapper.toResponseDto(service.assignFileToBeat(fileName, beatId));

        return ResponseEntity.created(URI.create(url)).body(beat);

    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getBeatFile(@PathVariable("id") Long beatId, HttpServletRequest request){
        Resource resource = service.getFileFromBeat(beatId);

        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {

            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @GetMapping
    public ResponseEntity<List<BeatResponseDto>> getAllBeats() {
        List<BeatResponseDto> beats = service.getAllBeats();

        return ResponseEntity.ok().body(beats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeatResponseDto> getBeatById(@PathVariable("id") Long id) {
        BeatResponseDto beat = service.getBeatById(id);

        return ResponseEntity.ok().body(beat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeatResponseDto> updateBeat(@PathVariable("id") Long id, @RequestBody BeatRequestDto beatRequestDto) {
        BeatResponseDto dto = service.updateBeat(id, beatRequestDto);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/user/{userId}")
    public void assignUserToBeat(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        service.assignUserToBeat(id, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteBeat(@PathVariable("id") Long id) {
        service.deleteBeat(id);
    }

}
