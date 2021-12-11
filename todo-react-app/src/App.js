import React from 'react';
import './App.css';
import TodoBottomNavigation from './navigation/TodoBottomNavigation';
import NavigationBar from './navigation/NavigationBar';
import ScreenRoutes from './screen/ScreenRoute';
import { Box } from '@mui/system';


class App extends React.Component {
  
  render() {

    return (
      <Box className="App" height="100%">
        <NavigationBar />
        <ScreenRoutes />
        <TodoBottomNavigation />
      </Box>
    );
  };

}

export default App;
