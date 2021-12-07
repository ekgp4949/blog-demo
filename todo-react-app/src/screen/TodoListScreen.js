import React from "react";
import { call } from "../service/ApiService"

function getNow() {
  const now = new Date();
  const year = now.getFullYear();
  let month = now.getMonth();
  if(month < 10) {
    month = "0" + month;
  }
  let day = now.getDate();
  if(day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day;
}

class TodoListScreen extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      items: [],
      loading: true
    };
  }

  componentDidMount() {
    call("/todoHistory/"+getNow(), "GET", null).then((response) => {
      this.setState({ items: response.data, loading: false })
    }, (error) => {
      console.log(error.error)
    });
  };
  

  render() {
    return (
      this.state.items
    );
  }
}

export default TodoListScreen;