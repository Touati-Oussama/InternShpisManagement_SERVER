package tn.enicarthage.internshipsmanagement.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String password;
    private String role;
    private int department;
}
