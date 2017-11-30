package frontend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Message {

    private String message;

    @JsonCreator
    public Message(@JsonProperty("message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
