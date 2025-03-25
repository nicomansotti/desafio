package com.datosasistente.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.datosasistente.caller.ClimaCaller;
import com.datosasistente.response.DatosResponse;

@Service
public class DatosService {

	private final ClimaCaller climaCaller;

	public DatosService(ClimaCaller climaCaller) {
		this.climaCaller = climaCaller;
	}

	public DatosResponse obtenerDatos() {
		
		try {

		String clima = climaCaller.obtenerClima("Buenos aires");

		DatosResponse response = new DatosResponse();
		response.setClima(clima);
		response.setFecha(new Date().toString());
		return response;
		}
		catch (Exception e) {
			throw new RuntimeException("Hubo un error al consultar los datos: " + e.getMessage(), e);	        
		}
	}
}