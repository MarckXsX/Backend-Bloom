package com.HospitalBloom.BackendNotas.controller;

import com.HospitalBloom.BackendNotas.dto.GenericResponseDto;
import com.HospitalBloom.BackendNotas.entities.User;
import com.HospitalBloom.BackendNotas.enums.StatusUser;
import com.HospitalBloom.BackendNotas.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/details")
    public ResponseEntity<?> getAuthenticatedUser() {
        User user = userService.getUserDetails();
        return ResponseEntity.ok(new GenericResponseDto<>(user, "Datos usuario", HttpStatus.OK.value()));
    }

    @GetMapping("/allDetails")
    public ResponseEntity<?> getUsers(){
        List<User> userList = userService.getUsersDetails();
        return ResponseEntity.ok(new GenericResponseDto<>(userList, "Datos de Usuarios", HttpStatus.OK.value()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userService.deleteUser(id);
        if (optionalUser.isPresent()){
            return ResponseEntity.ok(new GenericResponseDto<>(optionalUser.orElseThrow(),"Usuario Elimiando", HttpStatus.OK.value()));
        }
        return ResponseEntity.badRequest().body(new GenericResponseDto<>(null,"Usuario no Eliminado",HttpStatus.BAD_REQUEST.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody String statusUser){
        User user = userService.StatusUpdate(id, statusUser);
        if(user!= null){
            return ResponseEntity.ok(new GenericResponseDto<>(null, "Datos del Usuario actualizados", HttpStatus.OK.value()));
        }
        return ResponseEntity.badRequest().body(new GenericResponseDto<>(null, "Error en la actualizacion", HttpStatus.BAD_REQUEST.value()));
    }

}
