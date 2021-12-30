import * as React from 'react'
import TodoListScreen from './TodoListScreen';
import WeeklyPlanScreen from './WeeklyPlanScreen';
import SettingsScreen from './SettingsScreen';
import { Route } from 'react-router';
import { Paper, Box } from '@mui/material';

class ScreenRoutes extends React.Component {
  render() {
    return (
      <Box height="80%">
        <Paper
          variant="outlined"
          square
          sx={{ height: "100%", overflowY: "scroll" }}
        >
          <Route path="/today">
            <TodoListScreen />
          </Route>
          <Route path="/weekly">
            <WeeklyPlanScreen />
          </Route>
          <Route path="/settings">
            <SettingsScreen />
          </Route>
        </Paper>
      </Box>
    );
  }
}

export default ScreenRoutes;