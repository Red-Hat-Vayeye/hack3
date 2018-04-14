package models;

import java.util.*;
import java.util.stream.*;

import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.db.ebean.Transactional;

@Entity
public class Twit extends Model {

    @Id
    @Constraints.Min(10)
    public Integer id;

    @Constraints.Required
    public String text;

    @Constraints.Required
    public String keywords;

    public static void delete(Integer id) {
        find.ref(id).delete();
    } 

    public static final Finder<Integer, Twit> find = new Finder<>(Twit.class);
}
