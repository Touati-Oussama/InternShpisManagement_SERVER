package tn.enicarthage.internshipsmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.*;
import tn.enicarthage.internshipsmanagement.security.*;
import tn.enicarthage.internshipsmanagement.services.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class EnseignantServiceTest {

    // Dépendances pour chaque service
    @Mock
    private UserRepos userRepos;

    @Mock
    private SfeRepository sfeRepo;

    @Mock
    private EntrepriseRepos entrepriseRepos;

    @Mock
    private DemnadeEncadrementRepos demandeEncadrementRepos;

    @Mock
    private SoutenanceRepository soutenanceRepos;

    @Mock
    private NoteRepository noteRepos;

    @Mock
    private FileDBRepository fileDBRepos;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private EntrepriseService entrepriseService;



    @InjectMocks
    private DemandeEncadrementServiceImp demandeEncadrementService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private SfeServiceImp sfeService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private SoutenanceServiceImp soutenanceService;

    @InjectMocks
    private NoteService noteService;

    @InjectMocks
    private FileStorageService fileStorageService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- Inscription d'un étudiant (register) ---
    @Test
    public void testInscriptionEtudiant() {
        UserRequest userRequest = new UserRequest("john_doe", "Doe", "john", "12345678", "johndoe@gmail.com", "123456", ERole.ENSEIGNANT.name(), 1);
        User user = User.builder()
                .nom(userRequest.getNom())
                .prenom(userRequest.getPrenom())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .telephone(userRequest.getTelephone())
                .role(ERole.valueOf(userRequest.getRole()))
                .build();

        when(userRepos.save(any(User.class))).thenReturn(user);

        AuthenticationResponse response = authenticationService.register(userRequest);

        assertEquals("La demande est envoyé !", response.getToken());
        verify(userRepos, times(1)).save(any(User.class));
    }


    // Connexion étudiant
    @Test
    public void testAuthentication() {
        // Créer un utilisateur fictif
        User user = new User();
        user.setUsername("slimen_iyed");
        user.setPassword("123456");

        // Simuler le comportement des mocks
        AuthenticationRequest authRequest = new AuthenticationRequest("slimen_iyed", "123456");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        when(userRepos.findByUsername("slimen_iyed")).thenReturn(java.util.Optional.of(user));

        String jwtToken = "faked-jwt-token";
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // Appeler la méthode que vous testez
        AuthenticationResponse response = authenticationService.authentication(authRequest);

        // Assertions
        assertEquals(jwtToken, response.getToken(), "Le token JWT doit correspondre");

        // Vérifier que les méthodes des mocks ont été appelées
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepos, times(1)).findByUsername("slimen_iyed");
        verify(jwtService, times(1)).generateToken(user);
    }
}
