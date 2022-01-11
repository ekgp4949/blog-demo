import { Link } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import { API_STAMP_IMG_URL } from "../app-config";
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

const dayOfWeek = ['일', '월', '화', '수', '목', '금', '토'];

class TodoListScreen extends React.Component {
  
  constructor(props) {
    super(props);
    
    const loadDate = getDate(new Date());

    this.changeNavigationValue = props.changeNavigationValue;
    this.changeNavigationValue("today");

    this.state = { 
      todoListArr: [ 
        <TodoList key={ loadDate } todoDate={ loadDate } dayOfWeekStr={ dayOfWeek[new Date(loadDate).getDay()] }/> 
      ], 
      stamps: { goodStampSrc: null, badStampSrc: null },
      loadedDate: new Date() 
    };
  }

  componentDidMount() {
    call("/stamps", "GET", null).then((response) => {
      this.setState({ 
        todoListArr: this.state.todoListArr, 
        stamps: { goodStampSrc: API_STAMP_IMG_URL + response.goodStamp, badStampSrc: API_STAMP_IMG_URL + response.badStamp },
        loadedDate: this.state.loadedDate 
      });
    }).catch((error) => {
      console.log(error.error)
    });
  }

  loadTodoListCallback = (e) => {
    let arr = this.state.todoListArr;
    const loadedDate = getDate(this.state.loadedDate);
    let date = new Date(loadedDate);
    for(let i = 1; i <= 7; i++) {
      date.setDate(date.getDate()-1);
      
      const dateStr = getDate(date);

      arr.push(
        <TodoListBefore 
          key={ dateStr } 
          todoDate={ dateStr } 
          dayOfWeekStr={ dayOfWeek[date.getDay()] } 
          stamps={ this.state.stamps }  
        />
      );
    }
    this.setState({ todoListArr: arr, loadedDate: date });
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