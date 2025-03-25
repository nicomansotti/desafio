package com.datosasistente.response;

public class DatosResponse {

	private String fecha;
	private String clima;
	private String error;

	public DatosResponse(String error) {
		this.error = error;
	}

	public DatosResponse() {
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
