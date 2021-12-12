import { Link } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import TodoList from "../components/TodoList";

function getDate(date) {
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

var loadDate = getDate(new Date());

class TodoListScreen extends React.Component {
  
  constructor(props) {
    super(props);
    
    // loadDate init
    loadDate = getDate(new Date());
    
    this.state = { todoListArr: [ 
      <TodoList key={ loadDate } todoDate={ loadDate } /> 
    ] };
  }


  loadTodoListCallback = (e) => {
    let arr = this.state.todoListArr;
    for(let i = 0; i < 7; i++) {
      let date = new Date(loadDate);
      date.setDate(date.getDate()-1);
      loadDate = getDate(date);
      
      arr.push(<TodoList key={ loadDate } todoDate={ loadDate } />);
    }
    this.setState({ todoListArr: arr });
  }
  
  render() {
    return (
      <Box>
        { this.state.todoListArr.map(item => item) }
        <Box mb={3} mt={4}>
          <Link
            component="button"
            variant="body2"
            onClick={ this.loadTodoListCallback }
          >
            이전 내역 더 보기
          </Link>
        </Box>
      </Box>
    );
  }
}

export default TodoListScreen;