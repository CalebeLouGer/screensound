package br.com.alura.screensound.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaChatGPT {
    private static final String API_URL = "https://api.openai.com/v1/responses";

    public String obterInformacao(String texto) {

        String apiKey = System.getenv("OPENAI_APIKEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("OPENAI_APIKEY não encontrada nas variáveis de ambiente");
        }

        String json = """
    {
      "model": "gpt-5.2",
      "input": "Me conte quem é: %s"
    }
    """.formatted(texto);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
