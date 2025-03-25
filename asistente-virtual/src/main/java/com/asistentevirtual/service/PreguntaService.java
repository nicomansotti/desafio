package com.asistentevirtual.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.asistentevirtual.helper.AsistenteHelper;
import com.asistentevirtual.response.Contexto;
import com.asistentevirtual.response.PreguntaResponse;
import com.datosasistente.config.ResponseConfig;
import com.datosasistente.config.ResponseConfig.ResponseMapping;

@Service
public class PreguntaService {

	@Autowired
	private AsistenteHelper helper;

	public PreguntaResponse hacerPregunta(String pregunta) {

		try {

			String respuesta = "Lo siento, no puedo responder en este momento.";

			String preguntaLower = pregunta.toLowerCase();

			final ResponseConfig responseConfig = helper.createHardcodedConfig();

			// Buscar coincidencia
			for (ResponseMapping mapping : responseConfig.getMappings()) {
				for (String keyword : mapping.getKeywords()) {
					if (preguntaLower.contains(keyword.toLowerCase())) {
						respuesta = helper.processResponse(mapping.getResponse());
					}
				}
			}

			PreguntaResponse response = new PreguntaResponse();
			Contexto contexto = new Contexto();
			contexto.setId(UUID.randomUUID().toString());
			contexto.setTimestamp(Instant.now().toString());
			contexto.setDatoExtra("dato extra");
			response.setRespuesta(respuesta);
			response.setContexto(contexto);
			return response;

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new RuntimeException("Error al obtener los datos del servicio externo: " + e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException("Ocurrió un error inesperado. Volvé a intentar más tarde: " + e.getMessage(), e);
		}

	}

}
