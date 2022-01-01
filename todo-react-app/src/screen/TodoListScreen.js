import { Link } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import TodoList from "../components/TodoList";
import TodoListBefore from "../components/TodoListBefore";
import { call } from "../service/ApiService"

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

const dayOfWeek = ['일', '월', '화', '수', '목', '금', '토'];

class TodoListScreen extends React.Component {
  
  componentDidMount() {
    call("/stamps", "GET", null).then((response) => {
      const loadDate = getDate(new Date());
      this.setState({ todoListArr: [ 
          <TodoList key={ loadDate } todoDate={ loadDate } dayOfWeekStr={ dayOfWeek[new Date(loadDate).getDay()] }/> 
        ], stamps: { goodStampSrc: response.goodStampSrc, badStampSrc: response.badStampSrc } 
      });
    }).catch((error) => {
      console.log(error.error)
    });
  }

  loadTodoListCallback = (e) => {
    let arr = this.state.todoListArr;
    for(let i = 0; i < 7; i++) {
      let date = new Date(loadDate);
      date.setDate(date.getDate()-1);
      loadDate = getDate(date);
      
      arr.push(
        <TodoListBefore 
          key={ loadDate } 
          todoDate={ loadDate } 
          dayOfWeekStr={ dayOfWeek[date.getDay()] } 
          stamps={ this.state.stamps }  
        />
      );
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