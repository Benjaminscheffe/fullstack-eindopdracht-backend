package nl.benjamin.muziekmarktplaats.service;

import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.BeatMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeatService {

    private final BeatRepository repos;
    private final BeatMapper mapper;

    public BeatService(BeatRepository repos, BeatMapper mapper) {
        this.repos = repos;
        this.mapper = mapper;
    }

    public BeatResponseDto saveBeat(BeatRequestDto beatRequestDto) {
        Beat beat = mapper.toEntity(beatRequestDto);

        Beat savedBeat = repos.save(beat);

        BeatResponseDto dto = mapper.toResponseDto(savedBeat);

        return dto;
    }

    public List<BeatResponseDto> getAllBeats() {
        List<Beat> beatList = repos.findAll();
        List<BeatResponseDto> beatDtoList = new ArrayList<>();

        for(Beat beat : beatList) {
            BeatResponseDto dto = mapper.toResponseDto(beat);
            beatDtoList.add(dto);
        }

        return beatDtoList;
    }

    public BeatResponseDto getBeatById(Long id) {
        Optional<Beat> beat = repos.findById(id);

        if (beat.isPresent()) {
            return mapper.toResponseDto(beat.get());
        } else {
            throw new RecordNotFoundException("No beat with id " + id);
        }
    }

    public BeatResponseDto updateBeat(Long id, BeatRequestDto beatRequestDto) {
        Optional<Beat> beatOptional = repos.findById(id);

        if(beatOptional.isPresent()) {
            Beat beat = beatOptional.get();
            beat.setTitle(beatRequestDto.getTitle());

            Beat returnBeat = repos.save(beat);

            return mapper.toResponseDto(returnBeat);
        } else {
            throw new RecordNotFoundException("No beat with id " + id);
        }
    }
}
