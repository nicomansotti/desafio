package com.asistentevirtual.caller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.asistentevirtual.response.DatosRespuestaDTO;
import com.datosasistente.config.FeignConfig;

@FeignClient(name = "DATOS-ASISTENTE", configuration = FeignConfig.class)
public interface DatosAsistenteCaller {

    @GetMapping("/api/v1/asistente/datos")
    DatosRespuestaDTO obtenerDatos();
}