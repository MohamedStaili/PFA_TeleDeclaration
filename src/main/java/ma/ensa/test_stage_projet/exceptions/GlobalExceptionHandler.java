package ma.ensa.test_stage_projet.exceptions;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.test_stage_projet.Dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (m1, m2) -> m1 + "; " + m2)
                );

        ErrorResponse response = ErrorResponse.validation("Validation failed", fieldErrors);
        return ResponseEntity.badRequest().body(response);
    }

    /* ---------- Duplicate Keys ---------- */
    private static final Map<Class<? extends Exception>, HttpStatus> DUPLICATE_MAP =
            Map.of(
                    DuplicateEmailException.class,    HttpStatus.CONFLICT,
                    DuplicateCodeException.class,     HttpStatus.CONFLICT,
                    DuplicateDesignationException.class, HttpStatus.CONFLICT,
                    DuplicateNomException.class,      HttpStatus.CONFLICT
            );

    @ExceptionHandler({
            DuplicateEmailException.class,
            DuplicateCodeException.class,
            DuplicateDesignationException.class,
            DuplicateNomException.class
    })
    public ResponseEntity<ErrorResponse> handleDuplicates(RuntimeException ex) {
        HttpStatus status = DUPLICATE_MAP.getOrDefault(ex.getClass(), HttpStatus.CONFLICT);
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    /*Not-Found  */
    private static final Map<Class<? extends Exception>, HttpStatus> NOT_FOUND_MAP =
            Map.of(
                    NotFoundSEException.class,        HttpStatus.NOT_FOUND,
                    NotFoundVilleException.class,     HttpStatus.NOT_FOUND,
                    NotFoundUtilisateur.class,        HttpStatus.NOT_FOUND,
                    NotFoundArticleException.class,   HttpStatus.NOT_FOUND,
                    NotFoundCategorieException.class , HttpStatus.NOT_FOUND ,
                    NotFoundOperateurException.class , HttpStatus.NOT_FOUND ,
                    NotFoundPortException.class , HttpStatus.NOT_FOUND ,
                    NotFoundProfileException.class , HttpStatus.NOT_FOUND ,
                    NotFoundRegimeException.class , HttpStatus.NOT_FOUND

            );

    @ExceptionHandler({
            NotFoundSEException.class,
            NotFoundVilleException.class,
            NotFoundUtilisateur.class,
            NotFoundArticleException.class,
            NotFoundCategorieException.class ,
            NotFoundOperateurException.class ,
            NotFoundPortException.class  ,
            NotFoundProfileException.class  ,
            NotFoundRegimeException.class
    })

    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {
        HttpStatus status = NOT_FOUND_MAP.getOrDefault(ex.getClass(), HttpStatus.NOT_FOUND);
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(NotUtilisatuerException.class)
    public ResponseEntity<ErrorResponse> handleNotUtilisatuer(NotUtilisatuerException ex) {
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(TokenExperedException.class)
    public ResponseEntity<ErrorResponse> handleTokenExpered(TokenExperedException ex) {
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    @ExceptionHandler(AddresseAlreadyADD.class)
    public ResponseEntity<ErrorResponse> handleAddresseAlreadyADD(AddresseAlreadyADD ex) {
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(DateExpiredException.class)
    public ResponseEntity<ErrorResponse> handleExceptionDateExpired(DateExpiredException ex) {
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(DateTuMuchException.class)
    public ResponseEntity<ErrorResponse> handleExceptionDateTuMuch(DateTuMuchException ex) {
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler({ AuthorizationDeniedException.class, AccessDeniedException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handle403(Exception ex){
        ErrorResponse response = ErrorResponse.business(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        ErrorResponse response = ErrorResponse.internal("An unexpected error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}