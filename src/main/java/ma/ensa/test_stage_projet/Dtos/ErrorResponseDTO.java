package ma.ensa.test_stage_projet.Dtos;

import java.util.List;

public record ErrorResponseDTO(
        String code,
        String message,
        List<String> details
) {
    public ErrorResponseDTO(String code, String message) {
        this(code, message, null);
    }
}
