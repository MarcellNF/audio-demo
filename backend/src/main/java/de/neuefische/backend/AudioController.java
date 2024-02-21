package de.neuefische.backend;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("/api/audio")
public class AudioController {
    @GetMapping("/stream")
    public void streamRadio(HttpServletResponse response) {
        try {
            // URL des Radio-Streams
            URI url = new URI("http://stream.dancewave.online:8080/dance.mp3");
            // Verbindung öffnen
            InputStream stream = url.toURL().openStream();

            // Chunked Transfer Encoding aktivieren
            response.setContentType("audio/mpeg");
            response.setHeader("Transfer-Encoding", "chunked");

            // Stream lesen und in Chunks senden
            byte[] buffer = new byte[1024]; // Größe des Chunks
            int length;
            while ((length = stream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, length);
                response.flushBuffer(); // Wichtig, um den Chunk sofort zu senden
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Fehlerbehandlung
        }
        finally {
            // Verbindung schließen
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
