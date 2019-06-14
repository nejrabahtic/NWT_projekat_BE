var express = require('express');
var router = express.Router();
const request = require('request-promise-native');
const proxy = require('express-http-proxy');
const services = require('../config/services.json');
var Auth = require('../sevices/Auth.js');

let baseUrl = 'localhost'
// let baseUrl = '192.168.1.8'



router.get('/user', (req, res, next) => {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent!' });
    }
    Auth
        .getAuthId(req.headers.authorization)
        .then(id => {
            request({
                method: 'get',
                uri: 'http://'+baseUrl+':8082/users/authid/' + id
            })
            .then(response => {
                var userid = JSON.parse(response).id;

                request({
                    method: "GET",
                    uri: "http://" + baseUrl + ":8083/match/user/" + userid
                })
                .then(response => {
                    res.status(200).json(response);
                    console.log(response);
                })
                .catch(error => {
                    res.status(400).json(error);
                })
            })
            .catch(error => {
                res.status(400).json(error);
            });
        })
        .catch(error => {
            res.status(400).json(error);
        })
});
router.get('/company', (req, res, next) => {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent!' });
    }
    Auth
        .getAuthId(req.headers.authorization)
        .then(id => {
            request({
                method: 'get',
                uri: 'http://'+baseUrl+':8083/company/authid/' + id
            })
            .then(response => {
                console.log(response);
                
                var companyId = JSON.parse(response);
                    
                request({
                    method: "GET",
                    uri: "http://" + baseUrl + ":8083/match/company/" + userid
                })
                .then(response => {
                    res.status(200).json(response);
                    console.log(response);
                })
                .catch(error => {
                    res.status(400).json(error);
                })
            })
            .catch(error => {
                res.status(400).json(error);
            });
        })
        .catch(error => {
            res.status(400).json(error);
        })
});
router.get('/', (req, res, next) => {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent!' });
      }
    Auth
        .getAuthId(req.headers.authorization)
        .then(id => {
            var userRequest = request({
                method: "GET",
                uri: "http://"+baseUrl+":8082/users/authid/" + id
            })
            var companyRequest = request({
                method: "GET",
                uri: "http://"+baseUrl+":8084/companies"
            })

            Promise
                .all([userRequest, companyRequest])
                .then(values => {


                    var userResponse = JSON.parse(values[0]);
                    var companyResponse = JSON.parse(values[1]);
                    console.log(companyResponse);

                    var MatchDTO = {
                        userId: userResponse.id,
                        userName: userResponse.userName,
                        userSkills: userResponse.skills,
                        companies: companyResponse.map(company => ({
                            companyName: company.companyname,
                            companyId: company.id,
                            jobs: company.jobs.map(job => ({
                                jobId: job.id,
                                jobName: job.jobname,
                                jobSkills: job.skills.map( skill => ({ skillName: skill.skillName }))
                            }))
                        }))
                    }
                    request({
                        method: "POST",
                        uri: "http://"+baseUrl+":8083/match/make",
                        body: MatchDTO,
                        json: true
                    })
                    .then(response => {
                        var jobId = response.jobId;
                        var job = null;
                        for(var i = 0; i < companyResponse.length; i++){
                            for(var j = 0; j < companyResponse[i].jobs.length; j++){
                                if(companyResponse[i].jobs[j].id == jobId){
                                    job = companyResponse[i].jobs[j];
                                }
                            }
                        }
                        res.status(200).json({ job, ...response});
                    })
                    .catch(error => {
                        console.log(error);
                    })
                })
                .catch(error => {
                    console.log(error);
                    res.status(400).json(error);
                });
            
        })
        .catch(error => {
            res.status(500).json(error);
        })
})

router.post("/decide", (req, res, next) => {
    console.log(req.body);
    if(req.body.id === undefined || req.body.accepted === undefined ){
        res.status(400).json({error: "Invalid data sent."});
        return;
    }
    request({
        method: "POST",
        uri: "http://"+baseUrl+":8083/match/decide/"+ req.body.id,
        body: req.body.accepted ,
        json: true     
    })
    .then(response => {
        console.log(response);
        res.status(200).json(response);
    })
    .catch(error => {
        res.status(500).json(error);
    })
});

router.get("/applications", (req, res, next) => {
    if (!req.headers.authorization) {
        return res.status(403).json({ error: 'No credentials sent!' });
      }
    Auth
        .getAuthId(req.headers.authorization)
        .then( id => {
            request({
                method: "GET",
                uri: "http://"+baseUrl+":8084/companies/authid/" + id
            })
            .then(response => {
                const { id } = JSON.parse(response);
                console.log(id);
                request({
                    method: "GET",
                    uri: "http://"+baseUrl+":8083/match/company/" + id,
                })
                .then(matches => {
                    console.log(JSON.parse(matches));
                    res.status(200).json(matches);
                })
                .catch(error=>{
                    res.status(400).json(error);    
                })
            })
            .catch(error => {
                res.status(400).json(error);    
            });
        })
        .catch(error => {
            res.status(400).json(error);
        })
})

module.exports = router;