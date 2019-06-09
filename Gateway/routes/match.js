var express = require('express');
var router = express.Router();
const request = require('request-promise-native');
const proxy = require('express-http-proxy');
const services = require('../config/services.json');
var Auth = require('../sevices/Auth.js');

//let baseUrl = 'localhost'
let baseUrl = '192.168.1.8'

router.get('/profile', (req, res, next) => {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent!' });
    }
    Auth
        .getAuthId(req.headers.authorization)
        .then(id => {
            request({
                method: 'get',
                uri: 'http://"+baseUrl+":8082/users/authid/' + id
            })
                .then(response => {
                    console.log("Success: ", response);
                    request({
                        method: "GET",
                        uri: "http://" + baseUrl + ":8083/match/user/" + response.id
                    })
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
module.exports = router;