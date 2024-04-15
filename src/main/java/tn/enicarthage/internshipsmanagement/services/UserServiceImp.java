package tn.enicarthage.internshipsmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.UserRepos;

import java.util.List;

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
    public List<User> getAllDirections() {
        return userRepos.findByRole(ERole.DIRECTION);
    }

    @Override
    public List<User> getAllEnseignants() {
        return userRepos.findByRole(ERole.ENSEIGNANT);
    }

    @Override
    public List<User> getAllEtudiants() {
        return userRepos.findByRole(ERole.ETUDIANT);
    }

    @Override
    public User findBySfe(String msg) {
        return userRepos.findBySfe(msg);
    }
}
