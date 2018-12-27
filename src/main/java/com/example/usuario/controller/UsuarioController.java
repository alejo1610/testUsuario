package com.example.usuario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.exception.SystemException;
import com.example.usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/service/usuario")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService service;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registroUsuario(@RequestBody UsuarioDTO dto){
        logger.info("Se invoca a UsuarioController");
        try{        	
            UsuarioDTO usuario = service.registrarUsuario(dto);
            if(usuario.getIdUsuario() == null) {
            	return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
            }else {
            	return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
            }
            
        }catch (Exception ex){
            throw new SystemException("Error al crear usuario ", HttpStatus.INTERNAL_SERVER_ERROR.toString(),ex);
        }
    }
}
