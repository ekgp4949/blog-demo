import * as React from 'react'
import TodoListScreen from './TodoListScreen';
import DailyPlanScreen from './DailyPlanScreen';
import AboutScreen from './AboutScreen';
import { Route } from 'react-router';
import { Paper, Box } from '@mui/material';

class ScreenRoutes extends React.Component {
  render() {
    return (
      <Box height="80%">
        <Paper
          variant="outlined"
          square
          sx={{ height: "100%", overflow: "scroll" }}
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
      </Box>
    );
  }
}

export default ScreenRoutes;