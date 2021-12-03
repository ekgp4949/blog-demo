import * as React from 'react'
import TodoListScreen from './TodoListScreen';
import DailyPlanScreen from './DailyPlanScreen';
import AboutScreen from './AboutScreen';
import { Route } from 'react-router';
import { Paper } from '@mui/material';

class ScreenRoutes extends React.Component {
  render() {
    return (
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
    );
  }
}

export default ScreenRoutes;