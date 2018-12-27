package com.example.usuario.exception;

public class SystemException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
    private String codigo;

    public SystemException(String msg, String cod, Throwable e) {
        super(msg,e);
        this.setMensaje(msg);
        this.setCodigo(cod);
    }

	public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
