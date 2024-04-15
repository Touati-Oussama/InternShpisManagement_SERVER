package tn.enicarthage.internshipsmanagement.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.Department;
import tn.enicarthage.internshipsmanagement.entities.Salle;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.response.JSONResponse;
import tn.enicarthage.internshipsmanagement.services.DepartmentService;
import tn.enicarthage.internshipsmanagement.services.SalleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;



    @RequestMapping(method= RequestMethod.GET)
    List<Department> getDepartments(){
        return this.departmentService.getAll();
    }

    @RequestMapping(value = "/etudiants/{id}",method= RequestMethod.GET)
    List<User> getEtudiants(@PathVariable("id") int id){
        return this.departmentService.getAllEtudiants(id);
    }

    @RequestMapping(value = "/enseignants/{id}",method= RequestMethod.GET)
    List<User> getEnseignants(@PathVariable("id") int id){
        return this.departmentService.getAllEnseignants(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Department department) {;
        return ResponseEntity.ok(this.departmentService.save(department));
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Department  getDepartmentById(@PathVariable("id") int id) {
        return this.departmentService.getDepartment(id);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@RequestBody Department d, @PathVariable("id") int id ) {
        Department department = this.departmentService.getDepartment(id);
        department.setNom(d.getNom());
        return ResponseEntity.ok(this.departmentService.updateDepartment(department));
    }


    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") int id) {
        Department department = this.departmentService.getDepartment(id);
        JSONResponse res = new JSONResponse();
        res.setMsg("La départment est suppriméé");
        this.departmentService.deleteDepartment(department);
        return ResponseEntity.ok(res);
    }
}
