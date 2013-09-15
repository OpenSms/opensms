package org.opensms.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created with IntelliJ IDEA. User: dewmalpc Date: 6/21/13 Time: 1:33 PM To
 * change this template use File | Settings | File Templates.
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        hm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);
        registerModule(hm);
        configure(SerializationFeature.INDENT_OUTPUT, true);
    }
}
