package controllers;

import play.mvc.*;
import play.libs.*;

import models.*;

import java.io.*;
import java.lang.*;
import java.util.concurrent.*;

import com.fasterxml.jackson.databind.*;

import com.google.gson.*;

public class HeatMapController extends Controller {

	public Result insurance() {
		Map.findByType(Map.Type.prestamo);

		return ok("Ok");
	}

	public Result credit() {
		Map.findByType(Map.Type.credito);
		
		return ok("Ok");
	}

}
