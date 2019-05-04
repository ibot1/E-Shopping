import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Login from "./components/Login";
import ShoppingPage from "./components/Shopping";
import CheckoutPage from "./components/Checkout";
import DashboardPage from "./components/Dashboard";
import AdvancedSettings from "./components/search/AdvancedSettings";
import Item from "./components/Item";

export default class App extends Component {
  render() {
    return (
      <div>
        <Router>
          <Route exact path="/" component={Login} />
          <Route exact path="/home" component={Login} />
          <Route exact path="/Shopping" component={ShoppingPage} />
          <Route exact path="/Dashboard" component={DashboardPage} />
          <Route exact path="/Checkout" component={CheckoutPage} />
          <Route exact path="/ASettings" component={AdvancedSettings} />
          <Route exact path="/Item" component={Item} />
        </Router>
      </div>
    );
  }
}
