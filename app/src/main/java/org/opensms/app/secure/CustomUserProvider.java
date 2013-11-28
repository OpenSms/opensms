package org.opensms.app.secure;

import org.apache.log4j.Logger;
import org.opensms.app.db.controller.impl.UserDAOController;
import org.opensms.app.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/26/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "customUserService")
public class CustomUserProvider implements UserDetailsService {

    private static final Logger LOGGER=Logger.getLogger(CustomUserProvider.class);

    @Autowired
    private UserDAOController userDAOController;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUserName = userDAOController.getUserByUserName(username);

                         for(Object ab:userByUserName.getAuthorities()){
                             LOGGER.info(ab);
                         }


        return userByUserName;
    }
}
