package com.asistentevirtual;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.asistentevirtual.caller.DatosAsistenteCaller;
import com.asistentevirtual.request.PreguntaRequest;
import com.asistentevirtual.response.DatosRespuestaDTO;
import com.asistentevirtual.response.PreguntaResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HacerPreguntaTests {

	private static final String URL = "/api/v1/asistente/preguntar";

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private DatosAsistenteCaller datosAsistenteCaller;

	@Test
	void hacerPreguntaOk() {

		DatosRespuestaDTO mockResponse = new DatosRespuestaDTO();
		mockResponse.setFecha("2025-03-25 10:00:00");
		mockResponse.setClima("soleado");
		when(datosAsistenteCaller.obtenerDatos()).thenReturn(mockResponse);

		ResponseEntity<PreguntaResponse> re = restTemplate.exchange(URL, HttpMethod.POST, getRequest(),
				PreguntaResponse.class);

		PreguntaResponse response = re.getBody();

		assertNotNull(response);
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals("La fecha y hora actual es: 2025-03-25 10:00:00.", response.getRespuesta());

	}

	@Test
	void hacerPreguntaError() {

		ResponseEntity<PreguntaResponse> re = restTemplate.exchange(URL, HttpMethod.POST, getRequest(),
				PreguntaResponse.class);

		PreguntaResponse response = re.getBody();

		assertNotNull(response.getError());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());

	}

	private HttpEntity<PreguntaRequest> getRequest() {
		HttpHeaders headers = new HttpHeaders();

		String pregunta = "¿Qué hora es?";
		PreguntaRequest request = new PreguntaRequest();
		request.setPregunta(pregunta);

		return new HttpEntity<>(request, headers);
	}

}
