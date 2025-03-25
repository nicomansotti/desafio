package com.datosasistente.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseConfig {
	private List<ResponseMapping> mappings = new ArrayList<>();

	public static class ResponseMapping {
		private List<String> keywords;
		private String response;

		public ResponseMapping() {
		}

		public List<String> getKeywords() {
			return keywords;
		}

		public String getResponse() {
			return response;
		}

		public void setKeywords(List<String> keywords) {
			this.keywords = keywords;
		}

		public void setResponse(String response) {
			this.response = response;
		}
	}

	public List<ResponseMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<ResponseMapping> mappings) {
		this.mappings = mappings;
	}
}