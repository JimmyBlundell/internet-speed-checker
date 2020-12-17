const router = require('express').Router()
const FastSpeedtest = require("fast-speedtest-api");
const speedTest = require('speedtest-net');

const History = require('../models/speed-history')

token = "https://api.fast.com/netflix/speedtest/v2?https=true&token=YXNkZmFzZGxmbnNkYWZoYXNkZmhrYWxm&urlCount=5";


router.get('/speed', async (req, res, next) => {
    try {
        let ip = '127.0.0.1';
        let test = await speedTest({acceptLicense: true})
        let data = {
            time : test.timestamp,
            speed : {
                ping : test.ping,
                download : test.download, 
                upload : test.upload
            }
        }

        let newTest = new History(data);
        History.addTest(newTest, ()=> {
            res.json({success : true, speed : data.speed})
        })
        // console.log(test.ping, test.download, test.upload, test.timestamp);
        
    } catch (err) {
        console.log(err.message);
        res.json({success : false, err : err.message})
    }
});

router.get('/history', (req, res, next) => {
    History.getTestHistory((history) => {
        if(history) {
            res.json({success : true, history : history});
        }
        else {
            res.json({success : false, msg : "History not retrievrd."})
        }
    })
})

module.exports = router