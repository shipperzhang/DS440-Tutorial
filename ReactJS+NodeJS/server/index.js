const express = require('express');
const cors = require('cors');
const app = express();

const dbService = require('./dbservice');
const { response } = require('express');

app.use(cors());
app.use(express.json());
app.use(express.static('.'))

// test
app.get('/test', (req, res) => {
    res.json({message: "Hello from Server!"})
})

// registration
app.post('/register', (request, response) => {
    const {firstname, lastname, username, password} = request.body;
    const db = dbService.getDbServiceInstance();
    const result = db.signIn(firstname, lastname, username, password);
    result
    .then(data => response.json({success: true}))
    .catch(err => console.log(err));
});

// login
app.post('/login', (request, response) => {
    const {username, password} = request.body;
    const db = dbService.getDbServiceInstance();
    const result = db.checkLogin(username, password);
    result
    .then(data => {
        if (data.recordset.length === 1) {
            response.json({
                success: true,
                firstname: data.recordset[0].firstname,
                lastname: data.recordset[0].lastname,
            })
        } else {
            response.json({
                success: false
            })
        }
    })
    .catch(err => console.log(err));
});

app.listen(5500, () => console.log('server is running...'));


