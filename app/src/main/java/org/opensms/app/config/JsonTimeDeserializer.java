/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author chamikara
 */
@Component
public class JsonTimeDeserializer extends JsonDeserializer<Date> {

    private static final Logger LOGGER = Logger.getLogger(JsonTimeDeserializer.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        Date parse = null;
        try {
            parse = dateFormat.parse(jp.getValueAsString());
        } catch (ParseException ex) {
            LOGGER.error(jp.getValueAsString(), ex);
        }
        LOGGER.info(jp.getValueAsString());
        LOGGER.info(parse);
        return parse;
    }
}
