package com.booleanuk.api.videoGames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/videogames")
public class VideoGameController {

    @Autowired
    VideoGamesRepository videoGamesRepository;

    @Autowired
    VideoGameMapper videoGameMapper;

    @PostMapping
    public VideoGameDTO addVideoGame(@RequestBody VideoGameDTO videoGameDTO, BindingResult result){

        try {
            if (result.hasErrors()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.getFieldErrors().toString());
            }

            VideoGame videoGame = videoGameMapper.toEntity(videoGameDTO);
            this.videoGamesRepository.save(videoGame);

            return videoGameMapper.toDTO(videoGame);
        } catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), e.getMessage());
        }
    }

    @GetMapping
    public List<VideoGameDTO> getAllVideoGames(){
        List<VideoGameDTO> videoGameDTOS = new ArrayList<>();
        this.videoGamesRepository.findAll()
                .forEach(vg ->
                        videoGameDTOS.add(videoGameMapper.toDTO(vg)));

        return videoGameDTOS;
    }

    @GetMapping("/{id}")
    public VideoGameDTO getVideoGameById(@PathVariable (name = "id") int id){

        VideoGame videoGame = this.videoGamesRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Video game not found."));

        return videoGameMapper.toDTO(videoGame);
    }

    @PutMapping("/{id}")
    public VideoGameDTO updateVideoGame(@PathVariable (name = "id") int id, @RequestBody VideoGame videoGame){
        return this.videoGamesRepository.findById(id).map(vg -> {
            if (videoGame.getTitle() != null)
                vg.setTitle(videoGame.getTitle());

            if (videoGame.getGameStudio() != null)
                vg.setGameStudio(videoGame.getGameStudio());

            if (videoGame.getGenre() != null)
                vg.setGenre(videoGame.getGenre());

            if (videoGame.getNumberOfPlayers() != null)
                vg.setNumberOfPlayers(videoGame.getNumberOfPlayers());

            if (videoGame.getAgeRating() != null)
                vg.setAgeRating(videoGame.getAgeRating());
            this.videoGamesRepository.save(vg);

            return videoGameMapper.toDTO(vg);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video game with the provided id does not exist"));
    }

    @DeleteMapping("/{id}")
    public VideoGameDTO deleteVideoGame(@PathVariable (name = "id") int id){
        return this.videoGamesRepository.findById(id).map(vg -> {
            this.videoGamesRepository.delete(vg);
            return videoGameMapper.toDTO(vg);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video game with the provided id does not exist"));
    }
}
