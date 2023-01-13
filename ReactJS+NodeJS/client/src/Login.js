import React from "react";
import { Link } from "react-router-dom"

function Login() {
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");

    const parseData = (data) => {
        if (data['success']) {
            let firstname = data['firstname'];
            let lastname = data['lastname'];
            alert('Welcome! '+firstname + ' ' + lastname);
        } else {
            alert('Login failed.');
        }
    }

    const handleSumbit = async e => {
        fetch("/login", {
            headers: {
                'Content-type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then(response => response.json())
        .then(data => parseData(data));
    }

    return (
        <div class="container">
            <div class="row col-md-5 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">
                        <h1>LOGIN</h1>
                    </div>
                    <div class="panel-body">
                        <form onSubmit={handleSumbit}>
                            <div class="form-group">
                                <label> Username </label>
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    value={username}
                                    onChange={(e) => {setUsername(e.target.value)}}/>
                            </div>
                            <div class="form-group">
                                <label> Password </label>
                                <input 
                                    type="password" 
                                    class="form-control"
                                    value={password}
                                    onChange={(e) => {setPassword(e.target.value)}}/>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" value="login"/>
                                <Link to="register" class="btn btn-secondary">Click to Sign up</Link>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;