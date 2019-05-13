import React, { Component } from "react";
import Axios from "axios";

export default class PersonalInformation extends Component {
  constructor() {
    super();
    this.state = {
      fname: "",
      lname: "",
      email: "",
      phoneNum: "",
      address1: "",
      city: "",
      zipcode: "",
      state: "",
      country: ""
    };
    this.handleChange = this.handleChange.bind(this);
    this.sendInformation = this.sendInformation.bind(this);
  }

  componentWillMount() {
    var email = sessionStorage.getItem("authentication");
    Axios.get(`http://localhost:8080/ProfileInformation/${email}`).then(
      data => {
        this.setState(data.data);
        //console.log(data.data);
      }
    );
  }

  sendInformation() {
    var info = {
      fname: document.getElementById("fname").value,
      lname: document.getElementById("lname").value,
      email: document.getElementById("email").value,
      phoneNum: document.getElementById("phoneNum").value,
      address1: document.getElementById("address1").value,
      city: document.getElementById("city").value,
      zipcode: document.getElementById("zipcode").value,
      state: document.getElementById("state").value,
      country: document.getElementById("country").value
    };

    Axios.put("http://localhost:8080/UpdateProfile/", info, {
      headers: { "Content-Type": "application/json" }
    })
      .then(response => {
        if (response.status === 200) {
          this.props.history.push("/Dashboard");
        }
      })
      .catch(err => {
        console.log(err, "Invalid Submission");
      });
  }

  handleChange(event) {
    this.setState({ [event.target.id]: event.target.value });
  }

  render() {
    return (
      <div className="Login">
        <h1>Personal Information </h1>
        <br />
        <input
          type="text"
          id="fname"
          placeholder="First Name"
          className="InputField"
          value={this.state.fname === "#" ? "" : this.state.fname}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="lname"
          placeholder="Last Name"
          className="InputField"
          value={this.state.lname === "#" ? "" : this.state.lname}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="email"
          placeholder="Email"
          className="InputField"
          value={this.state.email === "#" ? "" : this.state.email}
          disabled={true}
        />
        <br />
        <br />
        <input
          type="text"
          className="InputField"
          placeholder="123-456-7890"
          id="phoneNum"
          value={this.state.phoneNum === "#" ? "" : this.state.phoneNum}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="address1"
          placeholder="Address1"
          className="InputField"
          value={this.state.address1 === "#" ? "" : this.state.address1}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="city"
          placeholder="City"
          className="InputField"
          value={this.state.city === "#" ? "" : this.state.city}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="state"
          placeholder="State"
          className="InputField"
          value={this.state.state === "#" ? "" : this.state.state}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="country"
          placeholder="Country"
          className="InputField"
          value={this.state.country === "#" ? "" : this.state.country}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          id="zipcode"
          placeholder="Zip-Code"
          className="InputField"
          value={this.state.zipcode === "#" ? "" : this.state.zipcode}
          onChange={this.handleChange}
        />
        <br />
        <br />
        <input type="submit" onClick={this.sendInformation} value="Submit" />
      </div>
    );
  }
}
