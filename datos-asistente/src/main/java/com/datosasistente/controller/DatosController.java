package com.datosasistente.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datosasistente.response.DatosResponse;
import com.datosasistente.service.DatosService;

@RestController
@RequestMapping("/api/v1/asistente")
public class DatosController {
	
	   private static final Logger LOGGER = LoggerFactory.getLogger(DatosController.class);

    private final DatosService preguntaService;

    public DatosController(DatosService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping("/datos")
    public ResponseEntity<DatosResponse> obtenerDatos() {
        try {
        	DatosResponse respuesta = preguntaService.obtenerDatos();
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {        	
        	LOGGER.error("Error en el servicio de obtener datos", e);        	
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DatosResponse(e.getMessage()));
        }
    }
}