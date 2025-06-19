package com.clienthub.crm.clienthub.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Gestion des erreurs liées aux réponses HTTP via ResponseStatusException
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());

        logger.error("ResponseStatusException - {}: {}", status.value(), ex.getReason(), ex);

        return new ResponseEntity<>(generateErrorBody(status, ex.getReason(), request), status);
    }

    /**
     * Gestion des accès refusés (403 Forbidden)
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        logger.warn("AccessDeniedException: {}", ex.getMessage());
        return new ResponseEntity<>(
                generateErrorBody(HttpStatus.FORBIDDEN, "Vous n'avez pas les droits nécessaires", request),
                HttpStatus.FORBIDDEN);
    }

    /**
     * Gestion des erreurs d'authentification (401 Unauthorized)
     */
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationCredentialsNotFoundException ex, WebRequest request) {
        logger.warn("AuthenticationCredentialsNotFoundException: {}", ex.getMessage());
        return new ResponseEntity<>(
                generateErrorBody(HttpStatus.UNAUTHORIZED, "Token d'authentification manquant ou invalide", request),
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * Gestion des erreurs de validation (exemple : @Valid dans les DTOs)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex,
            WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        logger.warn("Validation Error: {}", validationErrors);

        Map<String, Object> errorBody = generateErrorBody(HttpStatus.BAD_REQUEST, "Validation failed", request);
        errorBody.put("errors", validationErrors);

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gestion des erreurs de validation JPA (entités)
     */
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleJpaConstraintViolation(jakarta.validation.ConstraintViolationException jcv, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        jcv.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            validationErrors.put(field, violation.getMessage());
        });
        logger.warn("JPA Validation Error: {}", validationErrors);
        Map<String, Object> errorBody = generateErrorBody(HttpStatus.BAD_REQUEST, "Validation failed", request);
        errorBody.put("errors", validationErrors);
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gestion des erreurs d'intégrité de données (ex: unicité)
     */
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(org.springframework.dao.DataIntegrityViolationException ex, WebRequest request) {
        logger.warn("DataIntegrityViolationException: {}", ex.getMessage());
        Map<String, Object> errorBody = generateErrorBody(HttpStatus.BAD_REQUEST, "Violation d'intégrité des données", request);
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gestion des erreurs de validation wrapées dans une TransactionSystemException
     */
    @ExceptionHandler(org.springframework.transaction.TransactionSystemException.class)
    public ResponseEntity<Map<String, Object>> handleTransactionSystemException(org.springframework.transaction.TransactionSystemException ex, WebRequest request) {
        Throwable cause = ex;
        while (cause != null) {
            if (cause instanceof jakarta.validation.ConstraintViolationException jcv) {
                Map<String, String> validationErrors = new HashMap<>();
                jcv.getConstraintViolations().forEach(violation -> {
                    String field = violation.getPropertyPath().toString();
                    validationErrors.put(field, violation.getMessage());
                });
                logger.warn("Transaction Validation Error: {}", validationErrors);
                Map<String, Object> errorBody = generateErrorBody(HttpStatus.BAD_REQUEST, "Validation failed", request);
                errorBody.put("errors", validationErrors);
                return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
            }
            cause = cause.getCause();
        }
        logger.error("TransactionSystemException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(
                generateErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur inattendue est survenue", request),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Gestion globale des exceptions génériques
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex, WebRequest request) {
        logger.error("Unexpected Exception ({}): {}", ex.getClass().getName(), ex.getMessage(), ex);
        // Log toute la chaîne des causes
        Throwable cause = ex;
        while (cause != null) {
            logger.error("CAUSE CHAIN: {} - {}", cause.getClass().getName(), cause.getMessage());
            cause = cause.getCause();
        }
        return new ResponseEntity<>(
                generateErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur inattendue est survenue", request),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Génère une réponse d'erreur formatée pour la cohérence des messages
     */
    private Map<String, Object> generateErrorBody(HttpStatus status, String message, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return body;
    }
}
