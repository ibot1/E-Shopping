import React, { Component } from "react";

export default class FileUpload extends Component {
  upload() {
    var m = document.getElementById("file");
    var image = document.createElement("img");
    var n = m.files[0];
    for (var nn in n) {
      console.log(nn);
    }

    image.src = window.URL.createObjectURL(n);
    document.getElementById("rend").appendChild(image);
  }
  render() {
    return (
      <div>
        <form>
          <input type="file" id="file" />
          <br />
          <input type="button" onClick={this.upload} value="upload" />
          <p id="rend"> </p>
        </form>
      </div>
    );
  }
}
