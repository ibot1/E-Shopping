import React, { Component } from "react";
import Item from "./Item";
import Axios from "axios";
import "../App.css";

export default class AllDeals1 extends Component {
  //dont forget to automate the path discovery
  state = {
    path1: { allowed: {}, notallowed: {} }, //still not sure if using a dictionary as opose to a list is better
    cart: [[], [], []],
    items: {}
  };

  constructor() {
    super();
    this.onUpdate = this.onUpdate.bind(this);
    this.updateAllowed = this.updateAllowed.bind(this);
    //this.populateCart
  }
  componentDidMount() {
    this.onMount();
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

  populateNotAllowed(allowed, notAllowed) {
    for (var key in allowed) notAllowed[key] = allowed[key];
    delete notAllowed[key];
    return notAllowed;
  }

  updateAllowed(props) {
    //try-catch and autocomplete
    var arr = [];
    var allowed = {};
    allowed[props] = this.state.path1["allowed"][props];
    var notAllowed = this.populateNotAllowed(
      this.state.path1["allowed"],
      this.state.path1["notallowed"]
    );
    notAllowed[props] = props;
    arr.push(allowed);
    arr.push(notAllowed);
    return arr;
  }

  onMount() {
    Axios.get("http://localhost:8080/checkout").then(data => {
      const res = this.stringToJson(data.data);
      Axios.get("http://localhost:8080/filenames")
        .then(data =>
          this.setState({
            path1: {
              allowed: this.convertStringToDic(data.data),
              notallowed: {}
            },
            cart: this.state.cart,
            items: res
          })
        )
        .then(data =>
          this.setState({
            path1: this.state.path1,
            cart: this.populateCart(res),
            items: this.state.items
          })
        );
      //console.log(res);
    });
  }

  identifyWhatColumn(tmp) {
    if (tmp.substring(0, 4) === "col1") return 1;
    if (tmp.substring(0, 4) === "col2") return 2;
    if (tmp.substring(0, 4) === "col3") return 3;
    //in the filename include the price and the column they belong to
  }

  findMaxColSize(col1, col2, col3) {
    var m = col1.length;
    if (m < col2.length) m = col2.length;
    if (m < col3.length) m = col3.length;
    return m;
  }

  normalizer(col1, col2, col3) {
    var maxLen = this.findMaxColSize(col1, col2, col3);
    const blank1 = (
      <div className="box1" key="box1">
        <br />
      </div>
    );
    const blank2 = (
      <div className="box2" key="box2">
        <br />
      </div>
    );
    const blank3 = (
      <div className="box3" key="box3">
        <br />
      </div>
    );
    var container = [];

    for (let i = 0; i < maxLen; i++) {
      if (col1.length < maxLen) col1.push(blank1);
      if (col2.length < maxLen) col2.push(blank2);
      if (col3.length < maxLen) col3.push(blank3);
      if (col1.length === col2.length && col1.length === col3.length) break;
    }
    container.push(col1);
    container.push(col2);
    container.push(col3);

    //console.log(container);
    return container;
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

  populateCart(res, path1) {
    var phoneColumn = [],
      tvColumn = [],
      computerColumn = [];

    path1 = typeof path1 === "undefined" ? this.state.path1["allowed"] : path1;

    for (var key in path1) {
      if (this.identifyWhatColumn(key) === 1) {
        phoneColumn.push(
          <div key={key}>
            <img src={require("../images/" + key)} alt="deal" />
            <div className="chk">${this.extractPriceAndQuantiy(key)[2]}</div>
            <Item
              id={key}
              key={key}
              value={typeof res[key] === "undefined" ? 0 : Number(res[key])}
            />
            <br />
          </div>
        );
      } else if (this.identifyWhatColumn(key) === 2) {
        tvColumn.push(
          <div key={key}>
            <img src={require("../images/" + key)} alt="deal" />
            <div className="chk">${this.extractPriceAndQuantiy(key)[2]}</div>
            <Item
              id={key}
              key={key}
              value={typeof res[key] === "undefined" ? 0 : Number(res[key])}
            />
            <br />
          </div>
        );
      } else if (this.identifyWhatColumn(key) === 3) {
        computerColumn.push(
          <div key={key}>
            <img src={require("../images/" + key)} alt="deal" />
            <div className="chk">${this.extractPriceAndQuantiy(key)[2]}</div>
            <Item
              id={key}
              key={key}
              value={typeof res[key] === "undefined" ? 0 : Number(res[key])}
            />
            <br />
          </div>
        );
      }
    }
    //return this.normalizer(phoneColumn, tvColumn, computerColumn);
    return [phoneColumn, tvColumn, computerColumn];
  }

  convertStringToDic(s) {
    var m = {},
      tmp = "";

    for (let i = 0; i < s.length; i++) {
      if (s[i] === " ") continue;
      if (s[i] !== ",") {
        tmp += s[i];
      } else {
        m[tmp] = tmp;
        tmp = "";
      }
    }
    m[tmp] = tmp;
    //console.log(m);
    return m;
  }

  componentWillReceiveProps(nextProps) {
    this.onUpdate(nextProps.query);
  }

  onUpdate(props) {
    if (
      typeof props === "undefined" ||
      props.trim() === "*" ||
      props.trim() === ""
    ) {
      //console.log("undefined");
      this.onMount();
      //console.log("done");
    } else {
      //console.log("fined");
      Axios.get("http://localhost:8080/checkout").then(data => {
        const res = this.stringToJson(data.data);
        var arr = this.updateAllowed(props);
        //console.log(arr);
        var carts = this.populateCart(res, arr[0]);
        //console.log(carts);
        this.setState({
          path1: {
            allowed: arr[0],
            notallowed: arr[1]
          },
          cart: carts,
          items: res
        });
      });
      //console.log(this.state.cart);
    }
  }

  render() {
    //console.log(this.state.path1);
    return (
      <div>
        <h1 align="center"> All Deals</h1>
        <div />

        <div className="AllDeals">
          <div className="box1">
            <h2 className="chk"> Phone</h2>
            <br />
            {this.state.cart[0]}
          </div>
          <div className="box2">
            <h2 className="chk"> Computer</h2>
            <br />
            {this.state.cart[1]}
          </div>
          <div className="box3">
            <h2 className="chk"> TV</h2>
            <br />
            {this.state.cart[2]}
          </div>
        </div>
      </div>
    );
  }
}
