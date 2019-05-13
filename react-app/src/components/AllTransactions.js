import React, { Component } from "react";
import Axios from "axios";

export default class AllTransactions extends Component {
  constructor() {
    super();
    this.state = { rows: [] };
    this.retrieveTransaction = this.retrieveTransaction.bind(this);
  }

  componentWillMount() {
    this.retrieveTransaction();
  }

  retrieveTransaction() {
    var email = sessionStorage.getItem("authentication");
    Axios.get(`http://localhost:8080/transaction/all/${email}`)
      .then(response => {
        var arr = [];
        var i = 0;
        if (response.status === 200) {
          for (let req in response.data) {
            var tmp = (
              <tr key={i}>
                <td className="Table"> {response.data[req]["itemName"]} </td>
                <td className="Table"> ${response.data[req]["price"]}</td>
                <td className="Table"> {response.data[req]["quantity"]}</td>
                <td className="Table">{response.data[req]["datePurchased"]}</td>
              </tr>
            );
            arr.push(tmp);
            i++;
          }
        }

        this.setState({
          rows: arr
        });
      })
      .catch(err => {
        console.log(err);
      });
    return "mm";
  }

  render() {
    return (
      <div className="Home">
        <h1 className="Checkout-header"> All Transactions</h1>
        <div className="Checkout-header">
          <table className="Table">
            <tbody>
              <tr>
                <td className="Table"> Name</td>
                <td className="Table"> Price</td>
                <td className="Table"> Quantity</td>
                <td className="Table"> Date</td>
              </tr>
              {this.state.rows}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
