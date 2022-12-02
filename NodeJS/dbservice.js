const mssql = require('mssql');
let instance = null;

const sqlConfig = {
    user: 'sa',
    password: 'DS440@Demo',
    database: 'DemoDB',
    server: 'localhost',
    options: {
        trustServerCertificate: true
    }
}


const pool = new mssql.ConnectionPool(sqlConfig);

pool.connect(err => {
    if (err) {
        alert(err);
    } else {
        console.log("Connected!");
    }
});


class DbService {
    static getDbServiceInstance() {
        return instance ? instance : new DbService();
    }

    async signIn(firstname, lastname, username, passowrd) {
        try {
            const response = await new Promise((resolve, reject) => {
                const request = pool.request();
                request.input('FirstName', mssql.NVarChar(50), firstname);
                request.input('LastName', mssql.NVarChar(50), lastname);
                request.input('UserName', mssql.NVarChar(50), username);
                request.input('Password', mssql.NVarChar(50), passowrd);
                request.query('INSERT INTO Demo.Users (username, password, firstname, lastname) VALUES (@UserName,@Password,@FirstName,@LastName);',
                (err, result) => {
                    if (err) reject(err.message);
                    resolve(result);
                })
            });
            return response;
        } catch (error) {
            console.log(error);
        }
    }

    async checkLogin(username, password) {
        try {
            const response = await new Promise((resolve, reject) => {
                const request = pool.request();
                request.input('UserName', mssql.NVarChar(50), username);
                request.input('Password', mssql.NVarChar(50), password);
                request.query('SELECT * FROM Demo.Users WHERE username = @UserName AND password = @Password;',
                (err, result) => {
                    if (err) reject(err.message);
                    resolve(result);
                })
            });
            return response;
        } catch (error) {
            console.log(error);            
        }
    }
}

module.exports = DbService;