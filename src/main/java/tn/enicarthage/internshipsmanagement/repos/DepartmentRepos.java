package tn.enicarthage.internshipsmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.enicarthage.internshipsmanagement.entities.Department;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;

import java.util.List;

public interface DepartmentRepos extends JpaRepository<Department, Integer> {

    @Query("SELECT d.users_departments FROM Department d JOIN d.users_departments u WHERE u.role = ?2 and d.id = ?1")
    List<User> findUsersByRole(int id, ERole role);

}
