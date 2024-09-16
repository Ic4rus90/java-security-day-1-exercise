package com.booleanuk.api.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please verify that all the required fields are entered.");
        }

        User user = userMapper.toEntity(userDTO);
        User savedUser = this.userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOS = new ArrayList<>();
        this.userRepository.findAll()
                .forEach(user ->
                        userDTOS.add(userMapper.toDTO(user)));

        return userDTOS;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable (name = "id") int id){

        User user = this.userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        return userMapper.toDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable (name = "id") int id, @RequestBody UserDTO userDTO){

        User updatedUser = userMapper.toEntity(userDTO);

        return this.userRepository.findById(id).map(user -> {
            if (updatedUser.getFullName() != null)
                user.setFullName(updatedUser.getFullName());

            User savedUser = this.userRepository.save(user);

            return userMapper.toDTO(savedUser);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with the provided id does not exist"));
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable (name = "id") int id){
        return this.userRepository.findById(id).map(user -> {
            this.userRepository.delete(user);
            return userMapper.toDTO(user);
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Video game with the provided id does not exist"));
    }
}
