package models;

import java.util.*;
import java.util.stream.*;

import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.db.ebean.Transactional;

@Entity
public class Map extends Model {

    @Id
    @Constraints.Min(10)
    public Integer id;

    @Constraints.Required
    public Double latitude;

	@Constraints.Required
    public Double longitude;

    @Constraints.Required
    public Float percentage;

    @Enumerated(EnumType.STRING)
    @Constraints.Required
    public Type done;

    public enum Type {
    	credito,
    	prestamo;
    }

    public static void delete(Integer id) {
        find.ref(id).delete();
    } 

    public static List<Map> findByType(Type type) {
        List<Map> maps = find.all();

        return maps.filter(map -> map.Type == type).collect(Collectors.toList());
    }

    public static final Finder<Integer, Map> find = new Finder<>(Map.class);
}