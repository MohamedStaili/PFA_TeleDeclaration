package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateAdminDTO;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseAdminDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.services.AdminService;
import ma.ensa.test_stage_projet.services.AdminServiceImpl;
import ma.ensa.test_stage_projet.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody @Valid CreateAdminDTO createAdminDTO) {
        Map<String,Object> map = new HashMap<>();
        try{
            ResponseAdminDTO responseUtilisateurDTO = adminService.addAdmin(createAdminDTO);
            map.put("message", "new admin created");
            map.put("admin", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        }catch (NotFoundProfileException e){
            map.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        Map<String,Object> map = new HashMap<>();
        try{
            adminService.deleteAdmin(id);
            //map.put("message", "utilisateur deleted");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundUtilisateur | NotAdminException e) {
            map.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        Map<String,Object> map = new HashMap<>();
        try{
            ResponseAdminDTO responseUtilisateurDTO = adminService.getAdmin(id);
            map.put("admin", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (NotFoundUtilisateur e) {
            map.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdmins(@RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        Map<String,Object> map = new HashMap<>();
        List<ResponseAdminDTO> responseUtilisateurDTOS = adminService.getAdmins(page, size);
        map.put("admins", responseUtilisateurDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping
    public ResponseEntity<?> getAdminByEmail(@RequestParam String email) {
        Map<String,Object> map = new HashMap<>();
        try{
            ResponseAdminDTO responseUtilisateurDTO = adminService.getAdminByEmail(email);
            map.put("admin", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (NotFoundUtilisateur e) {
            map.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }
}
