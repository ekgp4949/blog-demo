import React from 'react';
import './App.css';
import TodoBottomNavigation from './navigation/TodoBottomNavigation';
import NavigationBar from './navigation/NavigationBar';
import ScreenRoutes from './screen/ScreenRoute';
import { Box } from '@mui/system';


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { navValue: "today" }
  }


  changeNavigationValue = (value) => {
    this.setState({ navValue: value });
  }

  render() {
    return (
      <Box className="App" height="100%">
        <NavigationBar />
        <ScreenRoutes changeNavigationValue={this.changeNavigationValue}/>
        <TodoBottomNavigation value={this.state.navValue} />
      </Box>
    );
  };

}

export default App;
