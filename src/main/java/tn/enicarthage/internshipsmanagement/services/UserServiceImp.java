package tn.enicarthage.internshipsmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.UserRepos;
import tn.enicarthage.internshipsmanagement.response.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements  UserService{


    @Autowired
    private UserRepos userRepos;


    @Override
    public User save(User user) {
        return userRepos.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepos.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepos.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepos.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepos.findById(id).get();
    }

    @Override
    public List<UserDTO> getAllDirections() {
        return this.userRepos.findByRole(ERole.DIRECTION).stream().map(user -> {
            UserDTO tmp = new UserDTO();
            tmp.setUserId(user.getUserId());
            tmp.setNom(user.getNom());
            tmp.setPrenom(user.getPrenom());
            tmp.setTelephone(user.getTelephone());
            tmp.setEmail(user.getEmail());
            tmp.setEnabled(user.isEnabled());
            tmp.setDepartment(user.getDepartment().getNom());
            tmp.setRole(user.getRole());
            tmp.setUsername(user.getUsername());
            tmp.setPassword(user.getPassword());
            return  tmp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllEnseignants() {
        return this.userRepos.findByRole(ERole.ENSEIGNANT).stream().map(user -> {
            UserDTO tmp = new UserDTO();
            tmp.setUserId(user.getUserId());
            tmp.setNom(user.getNom());
            tmp.setPrenom(user.getPrenom());
            tmp.setTelephone(user.getTelephone());
            tmp.setEmail(user.getEmail());
            tmp.setEnabled(user.isEnabled());
            tmp.setDepartment(user.getDepartment().getNom());
            tmp.setRole(user.getRole());
            tmp.setUsername(user.getUsername());
            tmp.setPassword(user.getPassword());
            return  tmp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllEtudiants() {
        return this.userRepos.findByRole(ERole.ETUDIANT).stream().map(user -> {
            UserDTO tmp = new UserDTO();
            tmp.setUserId(user.getUserId());
            tmp.setNom(user.getNom());
            tmp.setPrenom(user.getPrenom());
            tmp.setTelephone(user.getTelephone());
            tmp.setEmail(user.getEmail());
            tmp.setEnabled(user.isEnabled());
            tmp.setDepartment(user.getDepartment().getNom());
            tmp.setRole(user.getRole());
            tmp.setUsername(user.getUsername());
            tmp.setPassword(user.getPassword());
            return  tmp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getDemandes() {
        return this.userRepos.findByEnabled(false).stream().map(user -> {
            UserDTO tmp = new UserDTO();
            tmp.setUserId(user.getUserId());
            tmp.setNom(user.getNom());
            tmp.setPrenom(user.getPrenom());
            tmp.setTelephone(user.getTelephone());
            tmp.setEmail(user.getEmail());
            tmp.setEnabled(user.isEnabled());
            tmp.setDepartment(user.getDepartment().getNom());
            tmp.setRole(user.getRole());
            tmp.setUsername(user.getUsername());
            tmp.setPassword(user.getPassword());
            return  tmp;
        }).collect(Collectors.toList());
    }

    @Override
    public User findBySfe(String msg) {
        return userRepos.findBySfe(msg);
    }

    @Override
    public List<UserDTO> getAllEnseignantsByDepartment(Long id) {
        User  u = getUser(id);
        return this.userRepos.findAllTeachersByDepartment(u.getDepartment().getId()).stream()
                .map(user -> {
                    UserDTO tmp = new UserDTO();
                    tmp.setUserId(user.getUserId());
                    tmp.setNom(user.getNom());
                    tmp.setPrenom(user.getPrenom());
                    tmp.setTelephone(user.getTelephone());
                    tmp.setEmail(user.getEmail());
                    tmp.setEnabled(user.isEnabled());
                    tmp.setDepartment(user.getDepartment().getNom());
                    tmp.setRole(user.getRole());
                    tmp.setUsername(user.getUsername());
                    tmp.setPassword(user.getPassword());
                    return  tmp;
                }).collect(Collectors.toList());
    }
}
