import { List, ListItem, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import TodoBefore from "./TodoBefore";
import { call } from "../service/ApiService"
import Loading from '../Loading'

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

  render() {
    let content = (
      <Box>
        <Typography mt={2} mb={2} variant="body1" sx={{ fontWeight: 900, color: "#1976d2" }}>
          한가한 하루였군요 . . .
        </Typography>
      </Box>
    );
    
    if(this.state.items.length !== 0) {
      content = ( this.state.items.map(item => 
        (<TodoBefore
          key={item.id}
          item={item}
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
                { this.state.todoDate }
            </Typography>
          </ListItem>
          { this.state.loading ? <Loading /> : content }
        </List>
      </Box>
    )
  }
}

export default TodoList;