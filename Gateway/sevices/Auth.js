const request = require('request-promise-native');

module.exports = {
    getAuthId: token => 
        request({
            method: "POST",
            uri: "http://localhost:8081/auth/token",
            body: token
        })
}