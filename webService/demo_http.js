var express = require('express');
var app = express();
var mysql = require('mysql');

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.get('/creditos', function (req, res) {
	con.query("SELECT * FROM map WHERE type = 'credito' ", function (err, result, fields) {
    		if (err) throw err;
    res.send(result);
  });
})

app.get('/seguros', function (req, res) {
	con.query("SELECT * FROM map where type = 'seguro' ", function (err, result, fields) {
    		if (err) throw err;
    res.send(result);
  });
})

var server = app.listen(8081, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "steel267{sir",
  database: "hack_user_data"
});

con.connect(function(err) {
  	if (err) throw err;
  	console.log("Connected to DB");
});

