package tn.enicarthage.internshipsmanagement.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.DemandeInscription;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.Etat;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.DemandeInscriptionRepository;
import tn.enicarthage.internshipsmanagement.repos.UserRepos;
import tn.enicarthage.internshipsmanagement.services.DepartmentService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepos userRepos;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DemandeInscriptionRepository demandeInscriptionRepos;
    private final DepartmentService departmentService;



    public AuthenticationResponse register(UserRequest userRequest) {
        var user = User.builder()
                .nom(userRequest.getNom())
                .prenom(userRequest.getPrenom())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .telephone(userRequest.getTelephone())
                .role(ERole.valueOf(userRequest.getRole()))
                .department(departmentService.getDepartment(userRequest.getDepartment()))
                .enabled(false)
                .build();
        userRepos.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token("La demande est envoyé !")
                .build();
    }


    public AuthenticationResponse authentication(AuthenticationRequest authRequest) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            var user  = userRepos.findByUsername(authRequest.getUsername())
                    .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse inscription(UserRequest userRequest) {
        var user = DemandeInscription.builder()
                .nom(userRequest.getNom())
                .prenom(userRequest.getPrenom())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .telephone(Long.valueOf(userRequest.getTelephone()))
                .role(ERole.valueOf(userRequest.getRole()))
                .username(userRequest.getUsername())
                .department(departmentService.getDepartment(userRequest.getDepartment()))
                .etat(Etat.EN_COURS)
                .build();
        demandeInscriptionRepos.save(user);
        return AuthenticationResponse.builder()
                .token("Demnade envoyée")
                .build();
    }


    public AuthenticationResponse activate(Long id) {
        User user = userRepos.findById(id).get();
        user.setEnabled(true);
        userRepos.save(user);
        return  AuthenticationResponse.builder()
                .token("Compte Activé !")
                .build();
    }

    public AuthenticationResponse unactivate(Long id) {
        User user = userRepos.findById(id).get();
        user.setEnabled(false);
        userRepos.save(user);
        return  AuthenticationResponse.builder()
                .token("Compte Desactivé !")
                .build();
    }
}
