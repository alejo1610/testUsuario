package com.example.usuario.service;

import com.example.usuario.dto.UsuarioDTO;
import com.example.usuario.exception.SystemException;

public interface UsuarioService {

    UsuarioDTO registrarUsuario(UsuarioDTO dto) throws SystemException;
}
