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

public class TwitterController extends Controller {

	public Result index() {
		List<Twit> twiters = Twit.find.all();

		String msg = "";
		ObjectMapper mapper = new ObjectMapper();
        
    	for(Twit twit : twiters) {
            try {
            	String json = mapper.writeValueAsString(twit);
            	msg += json + "\n";
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
    	}
        
		return ok(msg);
	}

}
