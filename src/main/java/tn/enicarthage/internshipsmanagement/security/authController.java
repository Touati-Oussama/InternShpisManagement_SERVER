package tn.enicarthage.internshipsmanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.DemandeInscription;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.services.DemandeInscriptionService;
import tn.enicarthage.internshipsmanagement.services.DepartmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class authController {

    private final AuthenticationService authService;
    private final DemandeInscriptionService demandeInscriptionService;

    /*@PostMapping("/register/etudiant")
    public ResponseEntity<AuthenticationResponse> registerEtudiant(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authService.registerEtudiant(userRequest));
    }

    @PostMapping("/register/direction")
    public ResponseEntity<AuthenticationResponse> registerDirection(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authService.registerDirection(userRequest));
    }
    @PostMapping("/register/enseignant")
    public ResponseEntity<AuthenticationResponse> registerEnseignant(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authService.registerEnseignant(userRequest));
    }*/

    @PostMapping("/account/activate/{id}")
    public ResponseEntity<AuthenticationResponse> activerCompte(@PathVariable("id") Long id){
        return ResponseEntity.ok(authService.activate(id));
    }

    @PostMapping("/account/unactivate/{id}")
    public ResponseEntity<AuthenticationResponse> DesactiverCompte(@PathVariable("id") Long id){
        return ResponseEntity.ok(authService.unactivate(id));
    }

    @PostMapping("/register/{type}")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest userRequest,@PathVariable("type") int type){
        switch (type){
            case 0:
                userRequest.setRole(ERole.ETUDIANT.name());
                break;
            case 1:
                userRequest.setRole(ERole.ENSEIGNANT.name());
                break;
            case 2:
                userRequest.setRole(ERole.DIRECTION.name());
                break;
        }
        System.out.println(userRequest.getRole());
        return ResponseEntity.ok(authService.register(userRequest));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authService.authentication(authRequest));
    }
}
