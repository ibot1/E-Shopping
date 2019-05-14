import React, { Component } from "react";
import axios from "axios";
import Item from "./Item.js";

export default class Checkout extends Component {
  state = {
    carts: {}
  };

  constructor() {
    super();
    this.checkout = this.checkout.bind(this);
  }
  //validate for empty cart
  componentDidMount() {
    axios.get("http://localhost:8080/checkout").then(data => {
      const res = this.stringToJson(data.data);
      this.setState({ carts: res });
    });
  }

  stringToJson(s) {
    var ctr = 0,
      dic = {},
      key = "",
      value = "";
    for (let i = 1; i < s.length - 1; i++) {
      if (s[i] === " ") continue;
      if (s[i] === ":" || s[i] === "=") {
        ctr = 1;
        continue;
      }
      if (ctr === 0) key += s[i];
      if (s[i] === ",") {
        ctr = 0;
        dic[key] = value;
        key = "";
        value = "";
      }
      if (ctr === 1) value += s[i];
    }
    dic[key] = value;
    return dic;
  }

  extractPriceAndQuantiy(filename) {
    filename = filename.substring(0, filename.length - 4) + "_";
    var arr = [],
      tmp = "";

    for (let i = 0; i < filename.length; i++) {
      if (filename[i] !== "_") tmp += filename[i];
      else {
        arr.push(tmp);
        tmp = "";
      }
    }
    return arr;
  }

  populateShoppingCart() {
    var arr = Object.entries(this.state.carts),
      res = [];
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][1] > 0) {
        console.log(arr[i]);
        res.push(
          <div key={arr[i][0]} className="chk">
            <img src={require("../images/" + arr[i][0])} alt="deal" />
            <div className="chk">
              ${this.extractPriceAndQuantiy(arr[i][0])[2]}
            </div>
            <Item id={arr[i][0]} key={arr[i][0]} value={Number(arr[i][1])} />
            <br />
          </div>
        );
      }
    }
    return res;
  }

  checkout() {
    var email = sessionStorage.getItem("authentication");
    axios
      .post(`http://localhost:8080/transaction/confirm/${email}`)
      .then(response => {
        if (response.status === 200) {
          this.props.history.push("/Dashboard");
        }
      })
      .catch(err => {
        console.log(err);
      });
  }

  render() {
    //console.log(this.populateShoppingCart());
    return (
      <div className="Home">
        <div>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span style={{ fontSize: "6vh" }}> You are checking out </span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="Checkout" onClick={this.checkout} />
        </div>
        <br />
        {this.populateShoppingCart()}
      </div>
    );
  }
}
