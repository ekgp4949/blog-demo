import { List, ListItem, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import Todo from "./Todo";
import { call } from "../service/ApiService"
import Loading from '../Loading'
import AddTodo from "./AddTodo";

class TodoList extends React.Component {

  constructor(props) {
    super(props)

    this.state = { todoDate: props.todoDate, items: [], loading: true }
  }

  componentDidMount() {
    call("/todoHistory/"+this.state.todoDate, "GET", null).then((response) => {
      this.setState({ items: response.data, loading: false })
    }, (error) => {
      console.log(error.error)
    });
  };

  add = (item) => {
    call("/todoHistory", "POST", item).then((response) => 
      this.setState({ items: response.data, loading: false })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  delete = (item) => {
    call("/todoHistory", "DELETE", item).then((response) => 
      this.setState({ items : response.data, loading: false })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  update = (item) => {
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
      <Box>
        <List
          dense
          >
          <ListItem>
            <Typography variant="header1" sx={{ fontWeight: 400, color: "#1976d2" }}>
                { this.state.todoDate }
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