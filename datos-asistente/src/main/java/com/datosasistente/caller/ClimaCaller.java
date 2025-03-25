package com.datosasistente.caller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.datosasistente.config.FeignConfig;

@FeignClient(name = "climaCaller", url = "https://wttr.in", configuration = FeignConfig.class)
public interface ClimaCaller {

	@GetMapping("/{ciudad}?format=%C+%t")
	String obtenerClima(@PathVariable("ciudad") String ciudad);

}
