package controllers;

import models.*;

import play.mvc.*;
import play.libs.*;

import java.lang.*;
import java.util.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class WordClassifierController extends Controller {


/**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        String msg = "Error";
    	try {
    		msg = WordClassifier.test();
    	} catch(Exception e) {
    		e.printStackTrace();
    	} 

		return ok(msg);
	}
	
}