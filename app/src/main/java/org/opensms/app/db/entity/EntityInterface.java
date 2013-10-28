package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 9:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface EntityInterface <E>{


    @JsonIgnore
    public E getId();
}
