package tn.enicarthage.internshipsmanagement.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.internshipsmanagement.entities.ERole;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String nom;
    private String prenom;
    private String telephone;
    private String password;
    private ERole role;
    private boolean enabled;
    private String department;
}
