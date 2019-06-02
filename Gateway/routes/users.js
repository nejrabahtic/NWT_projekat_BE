var express = require('express');
var router = express.Router();
const request = require('request-promise-native');
const proxy = require('express-http-proxy');
const services = require('../config/services.json');
var Auth = require('../sevices/Auth.js');

/* GET users listing. */
// router.get('/all', (req, res, next) => {
// });


router.get('/profile', (req, res, next) => {
  if (!req.headers.authorization) {
    return res.status(403).json({ error: 'No credentials sent!' });
  }
  Auth
    .getAuthId(req.headers.authorization)
    .then( id => {
      request({
        method: "GET",
        uri: "http://localhost:8082/users/authid/" + id
      })
      .then(response => {
          res.staus(200).json(response);  
      })
      .catch(error => {
        res.status(400).json(error);
      });
    })
    .catch( error => {
      res.status(400).json(error);
    })

});

router.post('/change', (req, res, next) => {
  if (!req.headers.authorization) {
    return res.status(403).json({ error: 'No credentials sent!' });
  }
  Auth
    .getAuthId(req.headers.authorization)
    .then( id => {
      console.log(req.body);
      // request({
      //   method: "POST",
      //   uri: "http://localhost:8082/users/+" + id + "+/change",
      //   body: req.body
      // })
      // .then(response => {
      //     res.staus(200).json(response);  
      // })
      // .catch(error => {
      //   res.status(400).json(error);
      // });
    })
    .catch( error => {
      res.status(400).json(error);
    })
});

module.exports = router;
