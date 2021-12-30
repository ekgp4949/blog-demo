import * as React from 'react';
import Box from '@mui/material/Box';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import { ViewList, DoneOutline, HowToReg } from '@mui/icons-material';
import { Link } from 'react-router-dom';
import { Paper, Typography } from '@mui/material';

function Copyright() {
  return (
    <Typography color="textSecondary" align="center" variant="body2">
      {"Copyright Ⓒ "} ekgp4949, {new Date().getFullYear()} {"."}
    </Typography>
  );
}

class TodoBottomNavigation extends React.Component {
  constructor(props) {
    super(props);
    const path = window.location.pathname.split("/")[1];
    this.state = { value: path };
  }

  render() {
    return (
      <Box>
        <Paper 
          variant="outlined" 
          square
          >
          <Box>
            <BottomNavigation
              showLabels
              value={this.state.value}
              onChange={(event, newValue) => {
                this.setState({ value: newValue });
              }}
              >
              <BottomNavigationAction to={"/today"} LinkComponent={Link} value={"today"} label="Today's" icon={<DoneOutline />} />
              <BottomNavigationAction to={"/weekly"} LinkComponent={Link} value={"weekly"} label="Weekly Plan" icon={<ViewList />} />
              <BottomNavigationAction to={"/settings"} LinkComponent={Link} value={"settings"} label="Settings" icon={<HowToReg />} />
            </BottomNavigation>
          </Box>
        </Paper>
        <Copyright />
      </Box>
    );
  }
}

export default TodoBottomNavigation;