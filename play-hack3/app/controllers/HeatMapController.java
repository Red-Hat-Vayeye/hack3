package controllers;

import play.mvc.*;
import play.libs.*;

import models.*;

import java.io.*;
import java.lang.*;
import java.util.concurrent.*;
import java.util.List;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.google.gson.*;

public class HeatMapController extends Controller {

	public Result insurance() {
		List<Map> maps = Map.findByType(Map.Type.seguro);

		String msg = "";
		ObjectMapper mapper = new ObjectMapper();
        
    	for(Map map : maps) {
            try {
            	String json = mapper.writeValueAsString(map);
            	msg += json + "\n";
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
    	}
        
		return ok(msg);
	}

	public Result credit() {

		List<Map> maps = Map.findByType(Map.Type.credito);

		String msg = "";
        ObjectMapper mapper = new ObjectMapper();
        
        for(Map map : maps) {
            try {
                String json = mapper.writeValueAsString(map);
                msg += json + "\n";
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        
        return ok(msg);
	}

}
