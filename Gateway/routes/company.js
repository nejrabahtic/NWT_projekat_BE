var express = require('express');
var router = express.Router();
const request = require('request-promise-native');
const proxy = require('express-http-proxy');
const services = require('../config/services.json');
var Auth = require('../sevices/Auth.js');

router.get('/profile', (req, res, next) => {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent!' });
    }
    Auth
    .getAuthId(req.headers.authorization)
    .then( id => {
        request({
            method: "GET",
            uri: "http://localhost:8084/companies/authid/" + id
        })
        .then(response => {
            console.log("Success: ", response);
            res.status(200).json(response);
        })
        .catch(error => {
            res.status(400).json(error);    
        });
    })
    .catch(error => {
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
        request({
          method: "POST",
          uri: "http://localhost:8084/companies/" + id + "/change",
          body: req.body,
          json: true
        })
        .then(response => {
            console.log(response);
            const { companyemail, companyinfo, companyname, companyphonenumber } = response;
  
            res.status(200).json({
              companyemail,
              companyinfo,
              companyname,
              companyphonenumber
            });  
        })
        .catch(error => {
          console.log(error);
          res.status(400).json(error);
        });
      })
      .catch(error => {
        res.status(400).json(error);
      })
  });
module.exports = router;