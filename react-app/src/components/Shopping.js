import React, { Component } from "react";
import "../App.css";
import TrendingView from "./TrendingView";
import AllDeals from "./AllDeals";
import { Link } from "react-router-dom";
//import axios from "axios";

export default class Shopping extends Component {
  constructor() {
    super();
    this.state = {
      query: "*"
    };
    this.lookup = this.lookup.bind(this);
  }

  view(option) {
    //when option is entered the view is updated
  }

  lookup = () => {
    this.setState({ query: document.getElementById("search").value });
  };

  getAddedItems() {}

  render() {
    //console.log(this.state.items);
    return (
      <div className="Home">
        <br />
        <header className="Home-header">
          <div>
            <input
              type="text"
              name="search"
              placeholder="Search for your Items here"
              size="65"
              id="search"
            />
            <button onClick={this.lookup}> Search </button>
            {/*          Update UI of categories*/}
            <select name="categories" onChange={this.view(this.value)}>
              <option value="all">All Categories</option>
              <option value="phone">Phone</option>
              <option value="tv">TV</option>
              <option value="computer">Computer</option>
            </select>
            <Link to="/ASettings">
              <button> Advanced </button>
            </Link>
            <Link to="/Checkout/">
              <button> Checkout </button>
            </Link>
            {/* Dont forget to align the checkout button to the right of the page */}
            {/*<button onClick={this.checkout()}> Checkout </button>*/}
          </div>
        </header>
        <br />
        <TrendingView />
        <br />
        <AllDeals query={this.state.query} />
      </div>
    );
  }
}
