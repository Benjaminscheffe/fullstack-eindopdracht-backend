package nl.benjamin.muziekmarktplaats.service;

import jakarta.transaction.Transactional;
import nl.benjamin.muziekmarktplaats.dto.BeatRequestDto;
import nl.benjamin.muziekmarktplaats.dto.BeatResponseDto;
import nl.benjamin.muziekmarktplaats.exception.RecordNotFoundException;
import nl.benjamin.muziekmarktplaats.mapper.BeatMapper;
import nl.benjamin.muziekmarktplaats.model.Beat;
import nl.benjamin.muziekmarktplaats.model.Image;
import nl.benjamin.muziekmarktplaats.model.User;
import nl.benjamin.muziekmarktplaats.repository.BeatRepository;
import nl.benjamin.muziekmarktplaats.repository.ImageRepository;
import nl.benjamin.muziekmarktplaats.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeatService {

    private final BeatRepository repos;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final BeatMapper mapper;
    private final ImageService imageService;

    public BeatService(BeatRepository repos, ImageRepository imageRepository, UserRepository userRepository, BeatMapper mapper, ImageService imageService) {
        this.repos = repos;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.imageService = imageService;
    }

    public BeatResponseDto saveBeat(BeatRequestDto beatRequestDto) {
        Optional<User> optionalUser = userRepository.findById(beatRequestDto.userId);

        if (optionalUser.isEmpty()) {
            throw new  RecordNotFoundException("User not found!!!");
        }
        User user = optionalUser.get();

        Beat beat = mapper.toEntityWithUser(beatRequestDto, user);

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

    @Transactional
    public Beat assignImageToBeat(String fileName, Long id) {
        Optional<Beat> optionalBeat = repos.findById(id);
        Optional<Image> optionalImage = imageRepository.findById(fileName);

        if(optionalBeat.isPresent() && optionalImage.isPresent()) {
            Beat television = optionalBeat.get();
            Image image = optionalImage.get();

            // Beat owner dus daarin moet het gezet/gesaved worden
            television.setImage(image);

            return repos.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Transactional
    public Resource getPhotoFromBeat(Long beatId){
        Optional<Beat> optionalBeat = repos.findById(beatId);
        if(optionalBeat.isEmpty()){
            throw new RecordNotFoundException("Student with student number " + beatId + " not found.");
        }
        Image photo = optionalBeat.get().getImage();
        if(photo == null){
            throw new RecordNotFoundException("Student " + beatId + " had no photo.");
        }
        return imageService.downLoadFile(photo.getFileName());
    }

    public void assignUserToBeat(Long id, Long userId) {
        Optional<Beat> optionalBeat = repos.findById(id);
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalBeat.isPresent() && optionalUser.isPresent()) {
            Beat beat = optionalBeat.get();
            User user = optionalUser.get();

            beat.setUser(user);

            repos.save(beat);
        }
    }
}
