package ma.ensa.test_stage_projet.services;

public interface EmailService {
    void SendEmail(String to , String subject, String body);
}
