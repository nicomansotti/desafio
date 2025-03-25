package com.asistentevirtual.helper;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.asistentevirtual.caller.DatosAsistenteCaller;
import com.asistentevirtual.response.DatosRespuestaDTO;
import com.datosasistente.config.ResponseConfig;
import com.datosasistente.config.ResponseConfig.ResponseMapping;

@Configuration 
public class AsistenteHelper {
	
	@Autowired
	private DatosAsistenteCaller datosAsistenteCaller;

	public String processResponse(String response) {
		if (response.contains("{hora_actual}")) {
			DatosRespuestaDTO datos = datosAsistenteCaller.obtenerDatos();
			return response.replace("{hora_actual}", datos.getFecha());
		}
		if (response.contains("{clima_actual}")) {
			DatosRespuestaDTO datos = datosAsistenteCaller.obtenerDatos();
			return response.replace("{clima_actual}", datos.getClima());
		}
		return response;
	}

	public ResponseConfig createHardcodedConfig() {
		ResponseConfig config = new ResponseConfig();
		
		ResponseMapping climaMapping = new ResponseMapping();
		climaMapping.setKeywords(Arrays.asList("clima", "tiempo", "temperatura"));
		climaMapping.setResponse("El clima está: {clima_actual}.");

		ResponseMapping horaMapping = new ResponseMapping();
		horaMapping.setKeywords(Arrays.asList("hora", "horario", "qué hora es"));
		horaMapping.setResponse("La fecha y hora actual es: {hora_actual}.");

		ResponseMapping saludoMapping = new ResponseMapping();
		saludoMapping.setKeywords(Arrays.asList("hola", "saludo", "buenos días"));
		saludoMapping.setResponse("¡Hola! ¿En qué puedo ayudarte?");

		ResponseMapping despedidaMapping = new ResponseMapping();
		despedidaMapping.setKeywords(Arrays.asList("adiós", "chau", "hasta luego"));
		despedidaMapping.setResponse("¡Hasta luego! Que tengas un buen día.");

		config.setMappings(Arrays.asList(climaMapping, horaMapping, saludoMapping, despedidaMapping));

		return config;
	}
}
