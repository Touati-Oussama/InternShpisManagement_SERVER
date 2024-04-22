package tn.enicarthage.internshipsmanagement.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByEnabled(Boolean enabled);

    @Query("SELECT u FROM User u WHERE u.role = ?1")
    List<User> findByRole(ERole role);

    @Query("select e from User e join SFE s on s.etudiant = e where s.sujet = ?1")
    User findBySfe(String msg);

    @Query("SELECT u FROM User u WHERE u.department.id = :departmentId AND u.role = 'ENSEIGNANT'")
    List<User> findAllTeachersByDepartment(int departmentId);


}
