import { List, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import { call } from "../service/ApiService";
import AddTodo from "./AddTodo"
import Todo from "./Todo";

class DailyTodoList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { items:[], loading: false, dayOfWeek: props.dayOfWeek };
  }
  

  add = (item) => {
    call("/todo", "POST", item).then((response) => 
      console.log(response.data)
      //this.setState({ items: response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  delete = (item) => {
    call("/todo", "DELETE", item).then((response) => 
      this.setState({ items : response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  update = (item) => {
    console.log(item)
    call("/todo", "PUT", item).then((response) => 
      this.setState({ items: response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  }

  componentDidMount() {
    call("/todo/"+this.state.dayOfWeek, "GET", null).then((response) => {
      this.setState({ items: response.data, loading: false })
    }, (error) => {
      console.log(error.error)
    });
  };

  render() {
    if(this.state.items.length === 0) {
      return (
        <Box>
          <Typography mb={2} variant="body1" sx={{ fontWeight: 900, color: "#c7c7c7" }}>
            할 일이 없군요 . . .
          </Typography>
          <AddTodo add={this.add} dayOfWeek={this.state.dayOfWeek} />
        </Box>
      )
    }

    return (
      <Box>
        <List>
          { this.state.items.map(item => 
            (<Todo 
              item={item}
              delete={this.delete}
              update={this.update}
            />)
          ) }
        </List>
        <AddTodo add={this.add} dayOfWeek={this.state.dayOfWeek} />
      </Box>
    );
  }


}

export default DailyTodoList;