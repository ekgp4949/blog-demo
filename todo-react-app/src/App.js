import { Paper, List, AppBar, Toolbar, Typography, Grid, Button } from '@material-ui/core';
import React from 'react';
import './App.css';
import Todo from './Todo'
import Loading from './Loading'
import { call, signout } from './service/ApiService'
import TodoBottomNavigation from './TodoBottomNavigation';
import TodoListScreen from './TodoListScreen';
import DailyPlanScreen from './DailyPlanScreen';
import AboutScreen from './AboutScreen';
import { Route } from 'react-router';


class App extends React.Component {
  
  constructor(props) {
    super(props);

    this.state = {
      items: [],
      loading: true
    }
  }

  // 수정일: 2021-11-29 나중에 수정할 것
  componentDidMount() {
    this.setState({ items: [{ title : "test1", id: "1" }], loading: false })
    // call("/todo", "GET", null).then((response) => {
    //   this.setState({ items: response.data, loading: false })
    // }, (error) => {
    //   console.log(error.error)
    // });
  };

  add = (item) => {
    call("/todo", "POST", item).then((response) => 
      this.setState({ items: response.data })
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

  checkItems = () => {
    console.log(this.state.items)
  };

  render() {

    var todoItems = this.state.items.length > 0 && (
        <List>
          {
            this.state.items.map((item, index) => (
              <Todo 
                checkItems={this.checkItems} 
                item={item} 
                key={item.id} 
                delete={this.delete} 
                update={this.update}/>
            ))
          }
        </List>
    );

    var navigationBar = (
      <AppBar position="static">
        <Toolbar>
          <Grid container justifyContent="space-between">
            <Grid item>
              <Typography variant="h6">
                오늘의 할 일
              </Typography>
            </Grid>
            <Grid>
              <Button color="inherit" onClick={signout}>
                로그아웃
              </Button>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
    );

    var todoListPage = (
      <div>
        {navigationBar}
        <Paper
          variant="outlined"
          square
        >
          <Route path="/today">
            <TodoListScreen />
          </Route>
          <Route path="/daily">
            <DailyPlanScreen />
          </Route>
          <Route path="/about">
            <AboutScreen />
          </Route>
        </Paper>

        <Paper 
          variant="outlined" 
          sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} 
          square
        >
          <TodoBottomNavigation/>
        </Paper>
      </div>
    );

    var content = todoListPage;
    if(this.state.loading) {
      content = <Loading/>;
    }

    return (
      <div className="App">
        {content}
      </div>
    );
  };

}

export default App;
