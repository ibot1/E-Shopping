import React, { Component } from "react";
import axios from "axios";
import Item from "./Item.js";

export default class Checkout extends Component {
  state = {
    carts: {}
  };
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

  populateShoppingCart() {
    var arr = Object.entries(this.state.carts),
      res = [];
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][1] > 0) {
        res.push(
          <div key={arr[i][0]} className="chk">
            <img src={require("../images/" + arr[i][0])} alt="deal" />
            <Item id={arr[i][0]} key={arr[i][0]} value={Number(arr[i][1])} />
            <br />
          </div>
        );
      }
    }
    return res;
  }

  render() {
    //console.log(this.populateShoppingCart());
    return (
      <div className="Home">
        <h1 className="Checkout-header">You are checking out</h1>
        <br />
        {this.populateShoppingCart()}
      </div>
    );
  }
}
