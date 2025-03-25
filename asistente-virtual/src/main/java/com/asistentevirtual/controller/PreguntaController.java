	package com.asistentevirtual.controller;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.asistentevirtual.request.PreguntaRequest;
	import com.asistentevirtual.response.PreguntaResponse;
	import com.asistentevirtual.service.PreguntaService;
	
	@RestController
	@RequestMapping("/api/v1/asistente")
	public class PreguntaController {
	
		private static final Logger LOGGER = LoggerFactory.getLogger(PreguntaController.class);
	
		private final PreguntaService preguntaService;
	
		public PreguntaController(PreguntaService preguntaService) {
			this.preguntaService = preguntaService;
		}
	
		@PostMapping("/preguntar")
		public ResponseEntity<PreguntaResponse> hacerPregunta(@RequestBody PreguntaRequest request) {
			try {
				PreguntaResponse respuesta = preguntaService.hacerPregunta(request.getPregunta());
				return ResponseEntity.ok(respuesta);
			} catch (Exception e) {
				LOGGER.error("Error en el servicio de pregunta", e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PreguntaResponse(e.getMessage()));
			}
		}
	}