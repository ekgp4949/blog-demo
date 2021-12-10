import { List, ListItem, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import Todo from "../components/Todo";
import { call } from "../service/ApiService"

function getNow() {
  const now = new Date();
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

class TodoListScreen extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      items: [],
      loading: true
    };
  }

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

  componentDidMount() {
    call("/todoHistory/"+getNow(), "GET", null).then((response) => {
      this.setState({ items: response.data, loading: false })
    }, (error) => {
      console.log(error.error)
    });
  };
  

  render() {
    let content = (
      <Box>
        <Typography mt={2} variant="body1" sx={{ fontWeight: 900, color: "#1976d2" }}>
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
                { getNow() }
            </Typography>
          </ListItem>
          { content }
        </List>
      </Box>
    );
  }
}

export default TodoListScreen;