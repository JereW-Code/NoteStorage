package uk.ac.ucl.model;


import java.util.List;

public class Note {
    private String name;
    private String textId;
    private List<String> attachments;

    public Note(){
        this.name = null;
        this.textId = null;
        this.attachments = null;
    }
    public Note(String name, String textId, List<String> attachments){
        this.name = name;
        this.textId = textId;
        this.attachments = attachments;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTextId(String textId){
        this.textId = textId;
    }

    public void setAttachments(List<String> attachments){
        this.attachments = attachments;
    }

    public String getName() {
        return name;
    }

    public String getTextId() {
        return textId;
    }

    public List<String> getAttachments() {
        return attachments;
    }
}
