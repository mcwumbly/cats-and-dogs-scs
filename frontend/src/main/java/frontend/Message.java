package frontend;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Message {

    private String message;
    private boolean communicationFailure = false;

    @JsonCreator
    public Message(@JsonProperty("message") String message) {
        this.message = message;
    }

    public Message(String message, boolean communicationFailure) {
        this.message = message;
        this.communicationFailure = communicationFailure;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isCommunicationFailure() {
        return communicationFailure;
    }

}
