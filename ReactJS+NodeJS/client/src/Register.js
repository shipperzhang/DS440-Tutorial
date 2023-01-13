import React from "react";
import { Link } from "react-router-dom"

function Register() {
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [firstname, setFirstname] = React.useState("");
    const [lastname, setLastname] = React.useState("");

    const handleSubmit = async e => {
        fetch("/register", {
            headers: {
                'Content-type' : 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                firstname: firstname,
                lastname: lastname,
                username: username,
                password: password
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data['success']) {
                alert('Registration Succeed!');
            }
        });
    }


    return (
        <div class="container">
            <div class="row col-md-5 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">
                        <h1>REGISTRATION</h1>
                    </div>
                    <div class="panel-body">
                        <form onSubmit={handleSubmit}>
                            <div class="form-group">
                                <label> First Name </label>
                                <input 
                                    type="text" 
                                    class="form-control"
                                    value={firstname}
                                    onChange={(e)=>{setFirstname(e.target.value)}}/>
                            </div>
                            <div class="form-group">
                                <label> Last Name </label>
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    value={lastname}
                                    onChange={(e)=>{setLastname(e.target.value)}}/>
                            </div>
                            <div class="form-group">
                                <label> Username </label>
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    value={username}
                                    onChange={(e)=>{setUsername(e.target.value)}}/>
                            </div>
                            <div class="form-group">
                                <label> Password </label>
                                <input 
                                    type="password" 
                                    class="form-control" 
                                    value={password}
                                    onChange={(e)=>{setPassword(e.target.value)}}/>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" value="sign up"/>
                                <Link to="/" class="btn btn-secondary">Click to Login</Link>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Register;