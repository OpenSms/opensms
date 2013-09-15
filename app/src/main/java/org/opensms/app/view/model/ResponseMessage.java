package org.opensms.app.view.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dewmalpc
 * Date: 6/21/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMessage  implements Serializable{
    private final Type type;
    private final String text;

    public ResponseMessage(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        success, warn, error, info;
    }
}
