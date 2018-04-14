package azure;

public class Document {
    public String id, language, text;

    public Document(String id, String text){
        this.id = id;
        this.language = "es";
        this.text = text;
    }
}
