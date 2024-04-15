package tn.enicarthage.internshipsmanagement.services;

import tn.enicarthage.internshipsmanagement.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User updateUser(User user);
    void deleteUser(User user);
    void deleteUserById(Long id);
    User getUser(Long id);
    List<User> getAllDirections();

    List<User> getAllEnseignants();

    List<User> getAllEtudiants();

    User findBySfe(String msg);

}
