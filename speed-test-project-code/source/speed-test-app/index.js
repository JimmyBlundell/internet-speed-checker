const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const mongoose = require('mongoose');
const cors = require('cors');
const config = require('./config/database');

mongoose.connect(config.database);
mongoose.connection.on('connected',() => {
    console.log("Database connected "+config.database);
});
mongoose.connection.on('error', (err) => {
    console.log("Database Error "+err);
});


const app = express();

SpeedTest = require('./routes/speed-test');

app.use(cors());
app.use(bodyParser.json());

app.use(express.static(path.join(__dirname,'public')));

app.use('/speed-test', SpeedTest)

app.get('/',(req,res) => {
    res.sendFile('./public/index.html');
});


app.listen(3000, () => {
    console.log("Listening on port 3000");
})