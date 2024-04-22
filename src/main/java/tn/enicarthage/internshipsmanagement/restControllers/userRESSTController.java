package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.services.UserService;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class userRESSTController {

    final UserService userService;

    @RequestMapping(value = "/etudiants", method= RequestMethod.GET)
    ResponseEntity<?> getEtudiants(){
        return ResponseEntity.ok(userService.getAllEtudiants());
    }

    @RequestMapping(value = "/enseignants", method= RequestMethod.GET)
    ResponseEntity<?> getEnseignants(){
        return ResponseEntity.ok(userService.getAllEnseignants());
    }

    @RequestMapping(value = "/enseignants/departments/{idEtud}", method= RequestMethod.GET)
    ResponseEntity<?> getEnseignantsByDeparmtent(@PathVariable("idEtud") Long id){
        return ResponseEntity.ok(userService.getAllEnseignantsByDepartment(id));
    }

    @RequestMapping(value = "/demandes", method= RequestMethod.GET)
    ResponseEntity<?> getDemandes(){
        return ResponseEntity.ok(userService.getDemandes());
    }

}
