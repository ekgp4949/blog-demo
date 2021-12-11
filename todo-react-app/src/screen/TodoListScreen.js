import { Link } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import TodoList from "../components/TodoList";

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

  loadTodoListCallback = () => {
    alert("call")
  }
  
  render() {
    return (
      <Box>
        <TodoList todoDate={getNow()} />
        <Box>
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