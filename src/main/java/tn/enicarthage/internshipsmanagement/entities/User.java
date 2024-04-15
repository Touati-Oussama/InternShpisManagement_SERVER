package tn.enicarthage.internshipsmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public  class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String nom;
    private String prenom;
    private String telephone;
    private String password;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private boolean enabled;

    @OneToOne(mappedBy = "etudiant")
    @JsonIgnore
    private SFE sfe;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "encadreur")
    @JsonIgnore
    private List<SFE> sfes;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "president")
    @JsonIgnore
    private List<Soutenance> soutenances_presidents;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "rapporteur")
    @JsonIgnore
    private List<Soutenance> soutenances_rapporteurs;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



}
