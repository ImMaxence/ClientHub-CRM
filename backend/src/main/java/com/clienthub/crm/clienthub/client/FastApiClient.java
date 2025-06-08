package com.clienthub.crm.clienthub.client;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FastApiClient {
    private static final Logger log = LoggerFactory.getLogger(FastApiClient.class);

    private final RestTemplate rest;
    private final String baseUrl;
    private final String token;

    public FastApiClient(RestTemplate rest,
            @Value("${file.service.base-url}") String baseUrl,
            @Value("${file.service.token}") String token) {
        this.rest = rest;
        // On retire un slash final pour concat propre
        this.baseUrl = baseUrl.endsWith("/")
                ? baseUrl.substring(0, baseUrl.length() - 1)
                : baseUrl;
        this.token = token;
    }

    /**
     * 1) POST /generate-upload-url?filename=…
     * 2) POST multipart/form-data vers l’URL signée
     * 3) Récupère “url” et renvoie l’URL publique absolue
     */
    public String uploadOneShot(MultipartFile file) {
        log.info("uploadOneShot pour '{}'", file.getOriginalFilename());
        try {
            // --- 1) Générer l’URL signée ---
            String filenameEnc = URLEncoder.encode(
                    file.getOriginalFilename(),
                    StandardCharsets.UTF_8);
            URI genUri = URI.create(baseUrl + "/generate-upload-url?filename=" + filenameEnc);
            log.debug("POST {}", genUri);

            HttpHeaders genH = new HttpHeaders();
            genH.setBearerAuth(token);
            HttpEntity<Void> genReq = new HttpEntity<>(genH);

            ResponseEntity<Map<String, String>> genResp = rest.exchange(
                    genUri,
                    HttpMethod.POST,
                    genReq,
                    new ParameterizedTypeReference<>() {
                    });
            log.debug("generate-upload-url → {} {}", genResp.getStatusCode(), genResp.getBody());

            Map<String, String> genBody = genResp.getBody();
            if (genBody == null || !genBody.containsKey("upload_url")) {
                throw new IllegalStateException("Réponse FastAPI invalide: " + genBody);
            }
            String uploadPath = genBody.get("upload_url");

            // --- 2) Uploader le fichier ---
            String rawUrl = uploadPath.startsWith("http")
                    ? uploadPath
                    : baseUrl + uploadPath;
            // encode les espaces, accents, apostrophes…
            URI uploadUri = UriComponentsBuilder
                    .fromUriString(rawUrl)
                    .encode()
                    .build()
                    .toUri();
            log.debug("Envoi fichier vers {}", uploadUri);

            HttpHeaders upH = new HttpHeaders();
            upH.setContentType(MediaType.MULTIPART_FORM_DATA);

            ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", resource);

            HttpEntity<MultiValueMap<String, Object>> upReq = new HttpEntity<>(body, upH);

            ResponseEntity<Map<String, String>> upResp = rest.exchange(
                    uploadUri,
                    HttpMethod.POST,
                    upReq,
                    new ParameterizedTypeReference<>() {
                    });
            log.debug("upload → {} {}", upResp.getStatusCode(), upResp.getBody());

            Map<String, String> upBody = upResp.getBody();
            if (upBody == null || !upBody.containsKey("url")) {
                throw new IllegalStateException("Pas de clé 'url' dans la réponse upload: " + upBody);
            }
            String rel = upBody.get("url");
            String publicUrl = rel.startsWith("http") ? rel : baseUrl + rel;
            log.info("uploadOneShot terminé: {}", publicUrl);
            return publicUrl;

        } catch (HttpStatusCodeException hce) {
            log.error("FastAPI {} {}", hce.getStatusCode(), hce.getResponseBodyAsString(), hce);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur FastAPI: " + hce.getStatusCode(),
                    hce);
        } catch (Exception ex) {
            log.error("uploadOneShot KO", ex);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur interne upload vers FastAPI: " + ex.getMessage(),
                    ex);
        }
    }
}
