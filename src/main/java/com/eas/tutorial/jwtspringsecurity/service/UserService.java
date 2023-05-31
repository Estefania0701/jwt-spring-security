package com.eas.tutorial.jwtspringsecurity.service;

import com.eas.tutorial.jwtspringsecurity.dto.UserDTO;
import com.eas.tutorial.jwtspringsecurity.model.User;
import com.eas.tutorial.jwtspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username)  {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RequestRejectedException("No existe un usuario con ese username"));
    }

    public UserDTO getUserById(Long id) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RequestRejectedException("No existe el usuario con el ID " + id));

        return new UserDTO(userEntity);
    }

    public List<UserDTO> searchUsers (String country) {
        if(country==null) {
            return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
        }

        return userRepository.findByCountry(country).stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
