package com.asistentevirtual.response;

public class PreguntaResponse {

	private String respuesta;

	private Contexto contexto;

	private String error;

	public PreguntaResponse() {
	}

	public PreguntaResponse(String error) {
		this.error = error;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
