package azure;

import java.io.*;
import java.net.*;
import java.util.*;

public class Documents {
    public List<Document> documents;

    public Documents() {
        this.documents = new ArrayList<Document>();
    }
    public void add(String id, String text) {
        this.documents.add(new Document (id, text));
    }
}
