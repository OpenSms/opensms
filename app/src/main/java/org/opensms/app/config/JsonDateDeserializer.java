/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chamikara
 */
@Component
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    private static final Logger LOGGER = Logger.getLogger(JsonDateSerializer.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
