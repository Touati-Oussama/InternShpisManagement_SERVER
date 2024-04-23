package tn.enicarthage.internshipsmanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.User;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class authController {

    private final AuthenticationService authService;


  @GetMapping("/user/{id}")
  public ResponseEntity<User> get(@PathVariable("id") Long id){
      return ResponseEntity.ok(authService.getUser(id));
  }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<User> getByUsernAME(@PathVariable("username") String username){
        return ResponseEntity.ok(authService.getByUsername(username));
    }

    @PostMapping("/account/activate/{id}")
    public ResponseEntity<AuthenticationResponse> activerCompte(@PathVariable("id") Long id){
        return ResponseEntity.ok(authService.activate(id));
    }

    @PostMapping("/account/unactivate/{id}")
    public ResponseEntity<AuthenticationResponse> DesactiverCompte(@PathVariable("id") Long id){
        return ResponseEntity.ok(authService.unactivate(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest userRequest){
        System.out.println(" The role is:  " + userRequest);
        return ResponseEntity.ok(authService.register(userRequest));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authService.authentication(authRequest));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AuthenticationResponse> update(@RequestBody UserRequest userRequest,
                                                         @PathVariable("id") Long id){
        return ResponseEntity.ok(authService.update(userRequest,id));
    }

}
