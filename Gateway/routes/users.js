var express = require('express');
var router = express.Router();
const request = require('request-promise-native');
const proxy = require('express-http-proxy');
const services = require('../config/services.json');

/* GET users listing. */
// router.get('/all', (req, res, next) => {
// });


router.get('/login', (req, res, next) => {
  request({
    method: "POST",
    uri: "http://localhost:8081/auth/login",
    body: {
      username: "MaoZeThong",
      password: "asdw2323"
    }
  })
  .then(response => {
    // console.log(response);
    res.end("Yay");
  })
  .catch(error => {
    res.end("Nay");
  })
});






module.exports = router;
