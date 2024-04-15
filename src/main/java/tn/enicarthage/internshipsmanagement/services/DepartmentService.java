package tn.enicarthage.internshipsmanagement.services;

import tn.enicarthage.internshipsmanagement.entities.Department;
import tn.enicarthage.internshipsmanagement.entities.User;

import java.util.List;

public interface DepartmentService {

    Department save(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(Department department);
    void deleteDepartmentById(int id);
    Department getDepartment(int id);

    List<Department> getAll();


    List<User> getAllEnseignants(int id);

    List<User> getAllEtudiants(int id);


}
