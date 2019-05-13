import React, { Component } from "react";

export default class Dashboard extends Component {
  //ensure that a valid user is logged-in

  constructor() {
    super();
    this.login = this.login.bind(this);
  }
  login() {
    sessionStorage.removeItem("authentication");
    this.props.history.push("/");
  }
  render() {
    return (
      <div className="Home">
        <div className="Checkout-header">
          <div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span style={{ fontSize: "6vh" }}> Dashboard </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="logout" onClick={this.login} />
          </div>
          <br />
          <br />
          <div>
            <input
              type="button"
              className="DashButtons"
              style={{ float: "left" }}
              value="Personal Information"
              onClick={() => {
                this.props.history.push("/PersonalInformation");
              }}
            />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input
              type="button"
              className="DashButtons"
              style={{ float: "right" }}
              value="Transactions"
              onClick={() => {
                this.props.history.push("/AllTransactions");
              }}
            />
          </div>
          <br />
          <div>
            <input
              type="button"
              className="CircleButton"
              style={{ float: "right" }}
              value="Shopping"
              onClick={() => {
                this.props.history.push("/Shopping");
              }}
            />
          </div>
          <br />
          <div>
            <input
              type="button"
              className="DashButtons"
              style={{ float: "left" }}
              value="Login & Security"
              onClick={() => {
                this.props.history.push("/LoginSecurity");
              }}
            />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input
              type="button"
              className="DashButtons"
              style={{ float: "right" }}
              value="Payment Options"
              onClick={() => {
                this.props.history.push("/PaymentOptions");
              }}
            />
          </div>
          <br />
        </div>
      </div>
    );
  }
}
