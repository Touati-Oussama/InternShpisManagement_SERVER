package tn.enicarthage.internshipsmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.Salle;
import tn.enicarthage.internshipsmanagement.entities.Soutenance;
import tn.enicarthage.internshipsmanagement.entities.Soutenance;
import tn.enicarthage.internshipsmanagement.repos.SalleRepository;
import tn.enicarthage.internshipsmanagement.repos.SoutenanceRepository;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO;
import tn.enicarthage.internshipsmanagement.services.SoutenanceServiceImp;
import tn.enicarthage.internshipsmanagement.services.SoutenanceServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SoutenanceServiceTest {

    @Mock
    private SoutenanceRepository SoutenanceRepository;

    @Mock
    private SalleRepository salleRepository;

    @Mock
    private SoutenanceRepository soutenanceRepository;

    @InjectMocks
    private SoutenanceServiceImp soutenanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll(){

        List<Soutenance> soutenanceList = new ArrayList<>();
        soutenanceList.add(new Soutenance());
        soutenanceList.add(new Soutenance());
        soutenanceList.add(new Soutenance());
        when(soutenanceRepository.findAll()).thenReturn(soutenanceList);
        List<Soutenance> soutenances = soutenanceRepository.findAll();
        System.out.println(soutenances.size());
        assertEquals(3, soutenances.size());

    }
}
