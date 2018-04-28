package nl.hu.bep.friendspammer;

public class Email {
    private String to;
    private String from;
    private String subject;
    private String messageBody;
    private boolean asHtml;
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
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
}
