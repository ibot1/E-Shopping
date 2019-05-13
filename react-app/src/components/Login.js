import React, { Component } from "react";
import Axios from "axios";
import "../App.css";
import { Redirect } from "react-router-dom";

export default class Login extends Component {
  constructor() {
    super();
    this.Login = this.Login.bind(this);
    this.formatPassword = this.formatPassword.bind(this);
    this.SignUp = this.SignUp.bind(this);
  }

  formatPassword(password) {
    var newPassword = "";

    for (var i = 0; i < password.length; i++) {
      if (
        (password[i] >= "a" && password[i] <= "z") ||
        (password[i] >= "A" && password[i] <= "Z") ||
        (password[i] >= "0" && password[i] <= "9")
      )
        newPassword += password[i];
      else
        newPassword +=
          String("/".charCodeAt()) +
          String("/".charCodeAt()) +
          password[i].charCodeAt() +
          String("\\".charCodeAt()) +
          String("\\".charCodeAt());
    }
    return newPassword;
  }

  Login() {
    const body = {
      email:
        document.getElementById("email") === null
          ? ""
          : document.getElementById("email").value,
      password:
        document.getElementById("password") === null
          ? ""
          : document.getElementById("password").value
    };

    //Axios.post(`http://localhost:8080/login/${email}/${password}`);

    Axios.post("http://localhost:8080/login", body)
      .then(response => {
        if (response.status === 200) {
          sessionStorage.setItem("authentication", body.email);
          this.props.history.push("/Dashboard");
        }
      })
      .catch(() => {
        document.getElementById("error").innerHTML = "Invalid Credentials!!!";
      });

    //pass timer and the password and username when performing an activity
  }

  SignUp() {
    const body = {
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    };

    //Axios.post(`http://localhost:8080/signup/${email}/${password}`);
    Axios.post("http://localhost:8080/signup", body)
      .then(response => {
        if (response.status === 200) {
          sessionStorage.setItem("authentication", body.email);
          this.props.history.push(`/PersonalInformation/`);
        }
      })
      .catch(err => {
        document.getElementById("error").innerHTML =
          "Email Already Registered and/or Password doesnt meet the minimum requirements!!!";
      });
    //validate email and password requirements
    //pass timer and the password and username when performing an activity
  }

  render() {
    if (sessionStorage.getItem("authentication") !== null)
      return <Redirect to="/Dashboard" />;

    return (
      <div className="Login">
        <br />
        <br />
        <input
          type="text"
          id="email"
          placeholder="Email"
          style={{ width: "auto", height: "5vh" }}
          autoComplete="off"
        />
        <br />
        <br />
        <input
          type="text"
          id="password"
          placeholder="Password"
          style={{ width: "auto", height: "5vh" }}
          autoComplete="off"
        />
        <br />
        <br />
        <input type="submit" value="Sign Up" onClick={this.SignUp} />
        <input type="submit" value="Login" onClick={this.Login} />
        <br />
        <br />
        <p id="error" style={{ color: "red" }}>
          {" "}
        </p>
      </div>
    );
  }
}
