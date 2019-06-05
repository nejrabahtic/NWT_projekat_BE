var express = require('express');
var router = express.Router();
const request = require('request-promise-native');
const proxy = require('express-http-proxy');
const services = require('../config/services.json');
var Auth = require('../sevices/Auth.js');
const cors = require('cors');
/* GET users listing. */
// router.get('/all', (req, res, next) => {
// });
router.all('*', cors());

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
          console.log("Success: ", response);
          res.status(200).json(response);  
      })
      .catch(error => {
        console.log("error1: ", error);
        res.status(400).json(error);
      });
    })
    .catch( error => {
      console.log("error2: ", error);
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
      request({
        method: "POST",
        uri: "http://localhost:8082/users/" + id + "/change",
        body: req.body,
        json: true
      })
      .then(response => {
          console.log(response);
          const { userEmail, userInfo, userName, userPhoneNumber } = response;

          res.status(200).json({
            userEmail,
            userInfo,
            userName,
            userPhoneNumber
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

router.post('/addskills', (req, res, next) => {
  if (!req.headers.authorization) {
    return res.status(403).json({ error: 'No credentials sent!' });
  }
  Auth
    .getAuthId(req.headers.authorization)
    .then(id => {
      request({
        method: "POST",
        uri: "http://localhost:8082/users/" + id + "/skills",
        body: req.body,
        json: true
      })
      .then(response => {
        console.log(response);
        res.status(200).json(response);
      })
      .catch(error => {
        console.log(error);
        res.status(400).json(error);
      })
    })   
    .catch(error => {
      res.status(400).json(error);
    })
});

router.delete('/removeskill', (req, res, next) => {
  console.log("HIT")
  if (!req.headers.authorization) {
    return res.status(403).json({ error: 'No credentials sent!' });
  }
  Auth
  .getAuthId(req.headers.authorization)
  .then(id => {
    request({
      method: "DELETE",
      uri: "http://localhost:8082/users/" + id + "/skill",
      body: req.body,
      json: true
    })
    .then(response => {
      console.log(response);
      res.status(200).json(response);
    })
    .catch(error => {
      console.log(error);
      res.status(400).json(error);
    })
  })   
  .catch(error => {
    res.status(400).json(error);
  })
})
module.exports = router;
