package com.example.usuario.dto;

import java.util.Date;
import java.util.List;

public class UsuarioDTO {

	private Long idUsuario;
    private String name;
    private String email;
    private String password;
    private Date created;
    private Date modified;
    private Date last_login;
    private List<TelefonoDTO> phones;
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	public List<TelefonoDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<TelefonoDTO> phones) {
		this.phones = phones;
	}
	
    	
}
