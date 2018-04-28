package nl.hu.bep.friendspammer.helper;

import java.util.Arrays;
import java.util.List;

public class Email {
    private List<String> to;
    private String from;
    private String subject;
    private String messageBody;
    private boolean asHtml;
    
    public List<String> getTo() {
        return to;
    }
    
    public void setTo(List<String> to) {
        this.to = to;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessageBody() {
        return messageBody;
    }
    
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    
    public boolean isAsHtml() {
        return asHtml;
    }
    
    public void setAsHtml(boolean asHtml) {
        this.asHtml = asHtml;
    }
    
    public static List<String> splitTo(String to) {
        if(to.contains(",")) {
            String[] toList = to.split(",");
            return Arrays.asList(toList);
        }
        
        return Arrays.asList(to);
    }
}
