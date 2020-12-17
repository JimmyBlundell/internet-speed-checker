const mongoose = require('mongoose');

const historySchema = mongoose.Schema({
    time : {
        type : Date,
        required : true
    },
    speed : {
        type : Object,
        required : true
    }
});

const History = module.exports = mongoose.model('history', historySchema);

module.exports.addTest = function (newTest, callback) {
    newTest.save(callback)
}

module.exports.getTestHistory = function (callback) {
    History.find({}, (err, tests) => {
        if(err) {
            throw err
        }
        else {
            allTests = []
            tests.forEach(test => {
                temp = {
                    time : test.time,
                    speed : test.speed
                }

                allTests.push(temp) 
            })
            callback(allTests)
        }
    })
}