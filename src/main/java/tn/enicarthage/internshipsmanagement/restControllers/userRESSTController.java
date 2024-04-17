package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tn.enicarthage.internshipsmanagement.response.CommentaireDTO;
import tn.enicarthage.internshipsmanagement.services.UserService;

import java.util.List;

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



}
