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
		return ok( Map.findByType(Map.Type.prestamo) );
	}

	public Result credit() {
		return ok( Map.findByType(Map.Type.credito) );
	}

}
