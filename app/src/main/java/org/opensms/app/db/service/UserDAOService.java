package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.controller.impl.RoleDAOController;
import org.opensms.app.db.controller.impl.UserDAOController;
import org.opensms.app.db.controller.impl.UserRoleDAOController;
import org.opensms.app.db.entity.*;
import org.opensms.app.db.utils.UserRoleDAOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/9/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class UserDAOService {
    @Autowired
    private UserDAOController userDAOController;

    @Autowired
    private RoleDAOController roleDAOController;

    @Autowired
    private UserRoleDAOController userRoleDAOController;

    @Autowired
    private EmployeeDAOController employeeDAOController;

    @Autowired
    private ContactDetailsDAOService contactDetailsDAOService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Integer saveUser(User user) {
        user.setCreatedate(Calendar.getInstance().getTime());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDAOController.save(user);
    }

    public List<Role> getAllRoles() {
        return roleDAOController.getAll();
    }

    public List<User> getAll() {
        return userDAOController.getAll();
    }

    public List<User> search(String query) {
        return userDAOController.search(query);
    }

    public User getUser(Integer userId) {
        return userDAOController.get(userId);
    }

    public User getUser(String username) {
        return userDAOController.getUserByUserName(username);
    }

    public List<UserRole> getUserRoles(Integer userId) {
        return userRoleDAOController.getUserRoles(userId);
    }

    public void updateUserAccountState(User user) {
        userDAOController.updateUserAccountState(user);
    }

    public boolean validatePassword(User user) {

        User u = userDAOController.getUserByUserName(user.getUsername());

        return passwordEncoder.matches(user.getPassword(), u.getPassword());
    }

    public void changePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAOController.changePassword(user);
    }

//    public User login(User user) {
//        return userDAOController.login(user);
//    }

    public List<User> search(String query, String type) {
        return userDAOController.search(query, type);
    }

    public String getUserType(Integer userId) {
        return userDAOController.getUserType(userId);
    }

    /**
     * Add administrator to the system.
     * @param password
     * @return
     */
    public boolean registerAdmin(String username, String password) {

        User admin = new User();

        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setCreatedate(Calendar.getInstance().getTime());
        admin.setAccountStatus(true);

//        List<UserRole> userRoles = new ArrayList<UserRole>();
//        for (Role role : roleDAOController.getAll()) {
//
//            UserRole ur = new UserRole(role.getRoleId(), admin.getUserId(), Calendar.getInstance().getTime());
//            ur.setActive(true);
//            userRoles.add(ur);
//        }
//        admin.setUserRoleList(userRoles);

        Integer userId = userDAOController.save(admin);

        Employee ae = new Employee(admin.getUserId());
        ae.setUser(admin);
        ae.setSurname("CHANGE_LATER");
        ae.setInitials("CHANGE_LATER");
        ae.setNameReferredByInitials("CHANGE_LATER");
        Integer empId = employeeDAOController.save(ae);


        UserContactDetail uc = new UserContactDetail(admin.getUserId());
        uc.setUser(admin);
        uc.setName("CHANGE_LATER"); uc.setCity("CHANGE_LATER"); uc.setProvince("CHANGE_LATER");
        uc.setPostalCode("CHANGE_LATER"); uc.setCountry("CHANGE_LATER");
        contactDetailsDAOService.saveContactDetails(uc);

        return (userId > 0 && empId > 0);
    }
}
