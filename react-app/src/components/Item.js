import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.css";
import axios from "axios";

export default class Counter extends Component {
  state = { number: 0 };

  constructor() {
    super();
    this.addToCart = this.addToCart.bind(this);
    this.buttonChange = this.buttonChange.bind(this);
    this.removeItem = this.removeItem.bind(this);
  }

  componentWillMount() {
    typeof this.props.value === "undefined"
      ? this.setState({ number: 0 })
      : this.setState({ number: this.props.value });
  }

  addToCart() {
    this.setState({ number: this.state.number + 1 });
    this.postData(this, 1);
  }

  buttonChange() {
    return this.state.number === 0
      ? "btn btn-danger btn-sm"
      : "btn btn-primary btn-sm";
  }

  removeItem() {
    this.state.number !== 0
      ? this.setState({ number: this.state.number - 1 })
      : this.setState({ number: 0 });
    this.state.number === 0 ? this.postData(this, 0) : this.postData(this, -1);
  }

  postData(data, num) {
    var m = {};
    m[data.props.id] = data.state.number + num;
    const path = JSON.stringify(m);
    //console.log(path);
    axios.post("http://localhost:8080/posts/" + path);
  }

  render() {
    return (
      <div className="Item">
        <div>
          <button
            type="button"
            className={this.buttonChange()}
            onClick={this.removeItem}
          >
            Remove
          </button>{" "}
          {this.state.number}{" "}
          <button
            type="button"
            className={this.buttonChange()}
            onClick={this.addToCart}
          >
            Add
          </button>
        </div>
      </div>
    );
  }
}
/*
Dont forget to include multi-result search
*/
