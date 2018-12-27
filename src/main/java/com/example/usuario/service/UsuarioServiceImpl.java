package com.example.usuario.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.usuario.dto.TelefonoDTO;
import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.exception.SystemException;
import com.example.usuario.model.TelefonoModel;
import com.example.usuario.model.UsuarioModel;
import com.example.usuario.repository.TelefonoRepository;
import com.example.usuario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger Logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    TelefonoRepository telefonoRepository;

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {
        UsuarioDTO response = new UsuarioDTO();
        try{
        	if(!usuarioExiste(dto.getEmail())) {
	        	UsuarioModel usuario = guardarUsuario(dto);
	        	List<TelefonoModel> listaTelefono = new ArrayList<>();
	        	for (TelefonoDTO telefono : dto.getPhones()){
	        		listaTelefono.add(guardarTelefono(telefono, usuario));
	            }
	        	usuario = usuarioRepository.findByEmail(dto.getEmail());
	        	response = crearObjetoResponse(usuario, listaTelefono);
        	}else {
                throw new SystemException("El correo ya registrado ", HttpStatus.CONFLICT.toString(), null);
        	}        	
        }catch (Exception e){
            Logger.error("Error al guardar usuario ",e);
        }
        return response;
    }


    private UsuarioDTO crearObjetoResponse(UsuarioModel model, List<TelefonoModel> listaTelefono){
        if (model!=null){
            UsuarioDTO dto = new UsuarioDTO();
            List<TelefonoDTO> listaTelefonos = new ArrayList<>(); 
            dto.setIdUsuario(model.getIdUsuario());
            dto.setEmail(model.getEmail());
            dto.setName(model.getEmail());
            dto.setPassword(model.getPassword());
            dto.setCreated(model.getCreated());
            if(!listaTelefono.isEmpty()) {
            	for (TelefonoModel telefono : listaTelefono){
            		TelefonoDTO telefonoDto = new TelefonoDTO();
            		telefonoDto.setCitycode(telefono.getCitycode());
            		telefonoDto.setContrycode(telefono.getContrycode());
            		telefonoDto.setNumber(telefono.getNumero());
            		listaTelefonos.add(telefonoDto);
                }
            	dto.setPhones(listaTelefonos);
            }
            return dto;
        }
        return null;
    }

    private UsuarioModel guardarUsuario(UsuarioDTO dto){
        UsuarioModel model = new UsuarioModel();
        model.setEmail(dto.getEmail());
        model.setName(dto.getEmail());
        model.setPassword(dto.getPassword());
        model.setCreated(new Date());
        usuarioRepository.save(model);
        return model;
    }
    
    private TelefonoModel guardarTelefono(TelefonoDTO dto, UsuarioModel usuarioModel){
        TelefonoModel model = new TelefonoModel();
        model.setCitycode(dto.getCitycode());
        model.setContrycode(dto.getContrycode());
        model.setNumero(dto.getNumber());
        model.setUsuarioModel(usuarioModel);
        telefonoRepository.save(model);
        return model;
    }
    
    private boolean usuarioExiste(String email) {
    	boolean existe = false;
    	UsuarioModel usuario = usuarioRepository.findByEmail(email);
    	if(usuario != null) {
    		existe = true;
    	}
    	return existe;
    }

}
