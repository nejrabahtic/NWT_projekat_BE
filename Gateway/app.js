var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var proxy = require('express-http-proxy');
var cors = require('cors');

var usersRouter = require('./routes/users');

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

// Auth
app.use('/', proxy("http://localhost:8081", {
  filter: (req, res) => 
            (req.method === "POST" && req.path == "/auth/login") ||
            (req.method === "POST" && req.path == "/auth/register")
}));



// Users
app.use('/', proxy("http://localhost:8082", {
  filter: (req, res) => (req.method === "GET" && req.path == "/users") 
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
