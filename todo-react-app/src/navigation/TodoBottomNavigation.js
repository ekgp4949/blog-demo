import * as React from 'react';
import Box from '@mui/material/Box';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import { DoneOutline, HowToReg } from '@material-ui/icons';
import { ViewList } from '@mui/icons-material';
import { Link } from 'react-router-dom';
import { Paper } from '@material-ui/core';

export default function TodoBottomNavigation() {
  const [value, setValue] = React.useState(0);

  return (
    <Paper 
      variant="outlined" 
      sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} 
      square
    >
      <Box sx={{ width: '100%' }}>
        <BottomNavigation
          showLabels
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
          }}
        >
          <BottomNavigationAction to={"/today"} LinkComponent={Link} value={"/today"} label="Today's" icon={<DoneOutline />} />
          <BottomNavigationAction to={"/daily"} LinkComponent={Link} value={"/daily"} label="Plans" icon={<ViewList />} />
          <BottomNavigationAction to={"/about"} LinkComponent={Link} value={"/about"} label="WhoAmI" icon={<HowToReg />} />
        </BottomNavigation>
      </Box>
    </Paper>
  );
}