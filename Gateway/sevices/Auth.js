const request = require('request-promise-native');

let baseUrl = 'localhost'
// let baseUrl = '192.168.1.8'

module.exports = {
    getAuthId: token => 
        request({
            method: "POST",
            uri: "http://"+baseUrl+":8081/auth/token",
            body: token
        })
}