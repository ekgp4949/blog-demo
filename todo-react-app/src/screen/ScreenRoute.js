import * as React from 'react'
import TodoListScreen from './TodoListScreen';
import WeeklyPlanScreen from './WeeklyPlanScreen';
import SettingsScreen from './SettingsScreen';
import { Route } from 'react-router';
import { Paper, Box } from '@mui/material';

class ScreenRoutes extends React.Component {
  constructor(props) {
    super(props);
    this.changeNavigationValue = props.changeNavigationValue;
  }

  render() {
    return (
      <Box height="80%">
        <Paper
          variant="outlined"
          square
          sx={{ height: "100%", overflowY: "scroll" }}
        >
          <Route path="/today">
            <TodoListScreen changeNavigationValue={this.changeNavigationValue}/>
          </Route>
          <Route path="/weekly">
            <WeeklyPlanScreen changeNavigationValue={this.changeNavigationValue}/>
          </Route>
          <Route path="/settings">
            <SettingsScreen changeNavigationValue={this.changeNavigationValue}/>
          </Route>
        </Paper>
      </Box>
    );
  }
}

export default ScreenRoutes;