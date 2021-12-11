import { Divider, List, ListItem, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import Todo from "./Todo";
import { call } from "../service/ApiService"
import Loading from '../Loading'

class TodoList extends React.Component {

  constructor(props) {
    super(props)

    this.state = { todoDate: props.todoDate, items: [], loading: false }
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
      this.setState({ items: response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  delete = (item) => {
    call("/todoHistory", "DELETE", item).then((response) => 
      this.setState({ items : response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  update = (item) => {
    call("/todoHistory", "PUT", item).then((response) => 
      this.setState({ items: response.data })
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
        <Divider />
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
      <List
          dense
          className="todoList"
        >
          <ListItem>
            <Typography variant="header1" sx={{ fontWeight: 400, color: "#1976d2" }}>
                { this.state.todoDate }
            </Typography>
          </ListItem>
          { this.state.loading ? <Loading /> : content }
        </List>
    )
  }
}

export default TodoList;