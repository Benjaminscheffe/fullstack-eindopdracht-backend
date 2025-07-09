package nl.benjamin.muziekmarktplaats.controller;

import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import nl.benjamin.muziekmarktplaats.service.BeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/beats")
public class BeatController {

    private final BeatService service;

    public BeatController(BeatService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BeatResponseDto> addBeat(@RequestBody BeatRequestDto beatRequestDto) {
        BeatResponseDto beat = service.saveBeat(beatRequestDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + beat.id).toUriString());

        return ResponseEntity.created(uri).body(beat);
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

    @PutMapping("/{id}/image/{imageId}")
    public void assignImageToBeat(@PathVariable("id") Long id, @PathVariable("imageId") Long imageId) {
        service.assignImageToBeat(id, imageId);
    }

    @PutMapping("/{id}/user/{userId}")
    public void assignUserToBeat(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        service.assignUserToBeat(id, userId);
    }


}
