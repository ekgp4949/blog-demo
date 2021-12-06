import React from 'react';
import './App.css';
import Loading from './Loading'
import { call } from './service/ApiService'
import TodoBottomNavigation from './navigation/TodoBottomNavigation';
import NavigationBar from './navigation/NavigationBar';
import ScreenRoutes from './screen/ScreenRoute';
import { Box } from '@mui/system';


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

    var todoListPage = (
      <Box height="100%">
        <NavigationBar />
        <ScreenRoutes />
        <TodoBottomNavigation />
      </Box>
    );

    return (
      <Box className="App" height="100%">
        { this.state.loading ? <Loading/> : todoListPage  }
      </Box>
    );
  };

}

export default App;
