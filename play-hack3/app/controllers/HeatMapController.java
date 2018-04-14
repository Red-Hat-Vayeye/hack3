import controller;

import play.mvc.*;
import play.libs.*;

import model.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

import com.fasterxml.jackson.databind.*;

import com.google.gson.*;

public class HeatMapController extends Controller {

	public Result insurance() {
		Map.findByType(.prestamo);
	}

	public Result credit() {
		Map.findByType(.credito);
	}

}