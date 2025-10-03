package ma.ensa.test_stage_projet.Dtos;

import java.util.Collections;
import java.util.Map;

public record ErrorResponse(String code, String message, Object details) {

    public static ErrorResponse validation(String msg, Map<String, String> details) {
        return new ErrorResponse("VALIDATION_ERROR", msg, details);
    }

    public static ErrorResponse business(String code, String msg) {
        return new ErrorResponse(code, msg, Collections.emptyList());
    }

    public static ErrorResponse internal(String msg) {
        return new ErrorResponse("INTERNAL_ERROR", msg, Collections.emptyList());
    }
}
