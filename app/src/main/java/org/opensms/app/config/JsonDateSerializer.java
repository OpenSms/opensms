/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;


import org.springframework.stereotype.Component;

/**
 *
 * @author chamikara
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final Logger LOGGER = Logger.getLogger(JsonDateSerializer.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);
    }
}