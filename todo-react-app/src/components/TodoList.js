import { List, ListItem, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import Todo from "./Todo";
import { call } from "../service/ApiService"
import Loading from '../Loading'
import AddTodo from "./AddTodo";

function getDate(date) {
  if(!date) date = new Date();
  const now = date;
  const year = now.getFullYear();
  let month = now.getMonth()+1;
  if(month < 10) {
    month = "0" + month;
  }
  let day = now.getDate();
  if(day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day;
}

class TodoList extends React.Component {

  constructor(props) {
    super(props)

    this.state = { todoDate: props.todoDate, dayOfWeekStr: props.dayOfWeekStr, items: [], loading: true }
  }

  componentDidMount() {
    call("/todoHistory/"+this.state.todoDate, "GET", null).then((response) => {
      this.setState({ items: response.data, loading: false })
    }, (error) => {
      console.log(error.error)
    });
  };

  add = (item) => {
    if(this.state.todoDate !== getDate()) {
      alert("Cannot update: 오늘 자 데이터가 아닙니다.");
      return;
    }

    call("/todoHistory", "POST", item).then((response) => 
      this.setState({ items: response.data, loading: false })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  delete = (item) => {
    if(this.state.todoDate !== getDate()) {
      alert("Cannot update: 오늘 자 데이터가 아닙니다.");
      return;
    }

    call("/todoHistory", "DELETE", item).then((response) => 
      this.setState({ items : response.data, loading: false })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  update = (item) => {
    if(this.state.todoDate !== getDate()) {
      alert("Cannot update: 오늘 자 데이터가 아닙니다.");
      return;
    }
    
    call("/todoHistory", "PUT", item).then((response) => 
      this.setState({ items: response.data, loading: false })
    ).catch((error) => {
      console.log(error.error)
    });
  }

  render() {
    let content = (
      <Box>
        <Typography mt={2} mb={2} variant="body1" sx={{ fontWeight: 900, color: "#1976d2" }}>
          한가한 하루군요 . . .
        </Typography>
      </Box>
    );
    
    if(this.state.items.length !== 0) {
      content = ( this.state.items.map(item => 
        (<Todo
          key={item.id}
          item={item}
          delete={this.delete}
          update={this.update}
        />)
      ) );
    }

    return (
      <Box ml={1} mr={1}>
        <List
          dense
          >
          <ListItem>
            <Typography variant="header1" sx={{ fontWeight: 400, color: "#1976d2" }}>
                { this.state.todoDate+'('+this.state.dayOfWeekStr+')' }
            </Typography>
          </ListItem>
          { this.state.loading ? <Loading /> : content }
        </List>
        {
          new Date(this.state.todoDate).toDateString() === new Date().toDateString() ? 
          (<Box ml={2} mr={2} mb={3}><AddTodo add={this.add} /></Box>) : false
        }
      </Box>
    )
  }
}

export default TodoList;