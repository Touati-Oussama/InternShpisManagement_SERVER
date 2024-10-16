package tn.enicarthage.internshipsmanagement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.internshipsmanagement.Primary_Keys.Etudiant_Encadreur_PK;
import tn.enicarthage.internshipsmanagement.entities.*;
import tn.enicarthage.internshipsmanagement.repos.*;
import tn.enicarthage.internshipsmanagement.response.NoteDTO;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO;
import tn.enicarthage.internshipsmanagement.security.*;
import tn.enicarthage.internshipsmanagement.services.*;

//@SpringBootTest
public class EtudiantServiceTest {

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
    private  SfeServiceImp sfeService;

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
        UserRequest userRequest = new UserRequest("john_doe", "Doe", "john", "12345678", "johndoe@gmail.com", "123456", ERole.ETUDIANT.name(), 1);
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
        user.setUsername("touati_oussama");
        user.setPassword("123456");

        // Simuler le comportement des mocks
        AuthenticationRequest authRequest = new AuthenticationRequest("touati_oussama", "123456");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        when(userRepos.findByUsername("touati_oussama")).thenReturn(java.util.Optional.of(user));

        String jwtToken = "faked-jwt-token";
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // Appeler la méthode que vous testez
        AuthenticationResponse response = authenticationService.authentication(authRequest);

        // Assertions
        assertEquals(jwtToken, response.getToken(), "Le token JWT doit correspondre");

        // Vérifier que les méthodes des mocks ont été appelées
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepos, times(1)).findByUsername("touati_oussama");
        verify(jwtService, times(1)).generateToken(user);
    }


    // --- Consultation des entreprises ---
    @Test
    public void testConsultationEntreprises() {
        // Créer des objets Entreprise simulés
        Entreprise entreprise1 = new Entreprise(1, "Vermeg", "La bourse, Tunis 1053 lac 2", "Informatique", 147258, "Salah Hammemi", "vermeg@gmail.com");
        Entreprise entreprise2 = new Entreprise(2, "EY", "Centre Urbain, Tunis", "Informatique", 25814712, "Yahya Soula", "ey@gmail.com");

        // Simuler la méthode getALL() du service
        when(entrepriseService.getALL()).thenReturn(List.of(entreprise1, entreprise2));

        // Appeler la méthode du service
        List<Entreprise> entreprises = entrepriseService.getALL();

        // Vérifier que la liste des entreprises n'est pas nulle
        assertNotNull(entreprises);

        // Vérifier que la taille de la liste correspond bien à 2 entreprises
        assertEquals(2, entreprises.size());

        // Vérifier que la méthode getALL() du service a été appelée exactement une fois
        verify(entrepriseService, times(1)).getALL();
    }


    /*@Test
    public void testStore() throws IOException {
        // Création d'un fichier simulé
        MultipartFile file = new MockMultipartFile("report", "report.pdf", "application/pdf", "Ceci est le contenu du rapport".getBytes());

        // Création d'un étudiant et d'un encadreur simulés
        User etudiant = new User(1L, "touati_oussama", "oussama.touati.178@gmail.com", "touati", "oussama", "12345678", "123456", ERole.ETUDIANT, true);
        User encadreur = new User(2L, "slimen_iyed", "iyed@gmail.com", "Ben Slimen", "Iyed", "12345678", "123456", ERole.ENSEIGNANT, true);

        // Création d'un SFE simulé
        SFE sfe = new SFE();
        sfe.setId(1); // Assurez-vous que l'ID est 1
        sfe.setSujet("Application Web");
        sfe.setEtudiant(etudiant);
        sfe.setEncadreur(encadreur);

        // Simuler le comportement de sfeService pour qu'il retourne le SFE simulé
        when(sfeService.getSFE(1)).thenReturn(sfe);

        // Simuler le comportement de fileDBRepository
        FileDB mockFileDB = new FileDB();
        mockFileDB.setId(String.valueOf(1)); // ID doit correspondre au type approprié (assurez-vous qu'il soit de type int)
        when(fileDBRepos.save(any(FileDB.class))).thenReturn(mockFileDB);

        // Appeler la méthode à tester
        FileDB storedFileDB = fileStorageService.store(file, 1); // ID doit correspondre à celui simulé

        // Assertions
        assertEquals(mockFileDB.getId(), storedFileDB.getId(), "L'ID du fichier doit correspondre");
        assertEquals("report.pdf", storedFileDB.getName(), "Le nom du fichier doit correspondre");
        assertEquals("application/pdf", storedFileDB.getType(), "Le type du fichier doit correspondre");

        // Vérifier que les méthodes des mocks ont été appelées
        verify(sfeService).getSFE(1); // Vérifiez que getSFE a été appelée avec l'ID correct
        verify(fileDBRepos).save(any(FileDB.class)); // Vérifiez que save a été appelée
    }
    */


    // --- Choix d’un encadreur ---
    @Test
    public void testChoixEncadreur() {

        // Création de l'étudiant et de l'encadreur
        User etudiant = new User(1L, "touati_oussama", "oussama.touati.178@gmail.com", "touati", "oussama", "12345678", "123456", ERole.ETUDIANT, true);
        User encadreur = new User(2L, "slimen_iyed", "iyed@gmail.com", "Ben Slimen", "Iyed", "12345678", "123456", ERole.ENSEIGNANT, true);

        // Création de la clé primaire composite
        Etudiant_Encadreur_PK pk = new Etudiant_Encadreur_PK(etudiant.getUserId(), encadreur.getUserId());

        DemandeEncadrement demande = new DemandeEncadrement(pk, "Application Web CRM",Etat.EN_COURS,etudiant,encadreur );

        when(demandeEncadrementRepos.save(any(DemandeEncadrement.class))).thenReturn(demande);

        DemandeEncadrement savedDemande = demandeEncadrementService.saveDemandeEncadrement(demande);

        assertEquals(etudiant, savedDemande.getEtudiant());
        verify(demandeEncadrementRepos, times(1)).save(any(DemandeEncadrement.class));
    }


    // --- Consultation des soutenances ---
    @Test
    public void testConsultationSoutenance() {
        // Création de l'étudiant, encadreur, et membres du jury
        User etudiant = new User(1L, "touati_oussama", "oussama.touati.178@gmail.com", "touati", "oussama", "12345678", "123456", ERole.ETUDIANT, true);
        User encadreur = new User(2L, "slimen_iyed", "iyed@gmail.com", "Ben Slimen", "Iyed", "12345678", "123456", ERole.ENSEIGNANT, true);
        User president = new User(3L, "jaidi_faouzi", "jaid@gmail.com", "Jaidi", "Faouzi", "12345678", "123456", ERole.ENSEIGNANT, true);
        User rapporteur = new User(4L, "daasi_olfa", "daasi@gmail.com", "Daasi", "Olfa", "12345678", "123456", ERole.ENSEIGNANT, true);

        Salle salle = new Salle(1, "C100", null);
        SFE sfe = new SFE(1, "Application Web", etudiant, encadreur);
        LocalDateTime dateSoutenance = LocalDateTime.of(2024, 12, 12, 10, 0);
        Soutenance soutenance = new Soutenance(1, dateSoutenance, salle, president, rapporteur, sfe, null);

        // Simuler le retour de la méthode findByEtudId
        when(soutenanceRepos.findByEtudId(anyInt())).thenReturn(Optional.of(soutenance));

        // Appeler la méthode du service
        SoutenanceDTO soutenanceDTO = soutenanceService.getByEtudId(1); // L'ID doit correspondre

        // Assertions
        assertNotNull(soutenanceDTO, "Le DTO ne doit pas être null");
        assertEquals(dateSoutenance, soutenanceDTO.getDate(), "La date doit correspondre");
        assertEquals("Application Web", soutenanceDTO.getSfe(), "Le sujet doit correspondre");
        assertEquals("Ben Slimen Iyed", soutenanceDTO.getEncadreur(), "L'encadreur doit correspondre");
        assertEquals("Jaidi Faouzi", soutenanceDTO.getPresident(), "Le président doit correspondre");
        assertEquals("Daasi Olfa", soutenanceDTO.getRapporteur(), "Le rapporteur doit correspondre");
        assertEquals("C100", soutenanceDTO.getSalle(), "La salle doit correspondre");

        // Vérifier que la méthode a été appelée une fois
        verify(soutenanceRepos, times(1)).findByEtudId(anyInt());
    }


    // --- Consultation de note ---
    @Test
    public void testConsultationNote() {
        // Création de soutenance
        User etudiant = new User(1L, "touati_oussama", "oussama.touati.178@gmail.com", "touati", "oussama", "12345678", "123456", ERole.ETUDIANT, true);
        User encadreur = new User(2L, "slimen_iyed", "iyed@gmail.com", "Ben Slimen", "Iyed", "12345678", "123456", ERole.ENSEIGNANT, true);
        User president = new User(3L, "jaidi_faouzi", "jaid@gmail.com", "Jaidi", "Faouzi", "12345678", "123456", ERole.ENSEIGNANT, true);
        User rapporteur = new User(4L, "daasi_olfa", "daasi@gmail.com", "Daasi", "Olfa", "12345678", "123456", ERole.ENSEIGNANT, true);

        Salle salle = new Salle(1, "C100", null);
        SFE sfe = new SFE(1, "Application Web", etudiant, encadreur);
        LocalDateTime dateSoutenance = LocalDateTime.of(2024, 12, 12, 10, 0);
        Soutenance soutenance = new Soutenance(1, dateSoutenance, salle, president, rapporteur, sfe, null);

        // Création de Note
        Note note = new Note();
        note.setId(1);
        note.setNote(15);
        note.setSoutenance(soutenance);

        // Simuler la méthode findByEtudId
        when(noteRepos.findByEtudId(anyInt())).thenReturn(note);

        // Appel de la méthode que vous testez
        NoteDTO noteDTO = noteService.findByEtudId(1); // L'ID doit correspondre

        // Assertions
        assertNotNull(noteDTO, "Le DTO ne doit pas être null");
        assertEquals(15, noteDTO.getNote(), "La note doit correspondre");
        verify(noteRepos, times(1)).findByEtudId(anyInt()); // Vérification que la méthode a été appelée une fois
    }



}
