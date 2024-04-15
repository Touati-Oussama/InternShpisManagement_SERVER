package tn.enicarthage.internshipsmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.Department;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.DepartmentRepos;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepos departmentRepos;


    @Override
    public Department save(Department department) {
        return departmentRepos.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepos.save(department);
    }

    @Override
    public void deleteDepartment(Department department) {
            departmentRepos.delete(department);
    }

    @Override
    public void deleteDepartmentById(int id) {
            departmentRepos.deleteById(id);
    }

    @Override
    public Department getDepartment(int id) {
        return departmentRepos.findById(id).get();
    }

    @Override
    public List<Department> getAll() {
        return departmentRepos.findAll();
    }



    @Override
    public List<User> getAllEnseignants(int id) {
        return departmentRepos.findUsersByRole(id,ERole.ENSEIGNANT);
    }

    @Override
    public List<User> getAllEtudiants(int id) {
        return departmentRepos.findUsersByRole(id,ERole.ETUDIANT);
    }
}
