POST http://localhost:8080/api/v1/asistente/preguntar

REQUEST BODY:
{
    "pregunta": "como esta el clima"
}

RESPONSE:
{
    "respuesta": "El clima está: Partly cloudy +23°C.",
    "contexto": {
        "id": "d388bb62-69b0-4244-ba7e-412edc6ac5c2",
        "timestamp": "2025-03-25T19:17:56.406Z",
        "datoExtra": "dato extra"
    },
    "error": null
}
