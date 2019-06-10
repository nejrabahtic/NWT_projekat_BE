var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var proxy = require('express-http-proxy');
var cors = require('cors');

var usersRouter = require('./routes/users');
var companyROuter = require('./routes/company');
var matchRouter = require('./routes/match');
var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


app.use(cors());

app.use("/user", usersRouter);
app.use("/company", companyROuter);
app.use("/match", matchRouter)

let baseUrl = 'localhost'
// let baseUrl = '192.168.1.8'

// Auth
app.use('/', proxy("http://"+baseUrl+":8081", {
  filter: (req, res) => 
            (req.method === "POST" && req.path === "/auth/login") ||
            (req.method === "POST" && req.path === "/auth/register")
}));


// Users
app.use('/users', proxy("http://"+baseUrl+":8082", {
  filter: (req, res) => 
            (req.method === "GET" && req.path === "/users") ||
            (req.method === "GET" && req.path === "/skills")
}));

// Match
app.use('/matches', proxy("http://"+baseUrl+":8083", {
  filter: (req, res) => 
            (req.method === "GET" && req.path === "/matches") ||
            (req.method === "GET" && req.path === "/skills")
}));

// Company
app.use('/companies', proxy("http://"+baseUrl+":8084", {
  filter: (req, res) => 
            (req.method === "GET" && req.path === "/companies") ||
            (req.method === "GET" && req.path === "/skills")
}));



// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
