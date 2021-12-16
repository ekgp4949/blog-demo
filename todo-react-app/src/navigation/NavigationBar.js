import * as React from 'react';
import { AppBar, Toolbar, Typography, Grid, Button } from '@mui/material';
import { signout } from '../service/ApiService'
import { Box } from '@mui/system';
import Timer from '../components/Timer';

class NavigationBar extends React.Component {

  render() {
    return (
      <AppBar position="static">
          <Toolbar>
            <Grid container justifyContent="space-between">
              <Grid item>
                <Typography variant="h6">
                  오늘의 할 일
                </Typography>
              </Grid>
              <Grid item xs={3}>
                <Box borderRadius={1} 
                  sx={{ boxShadow: "inset 0 0 2px 2px #888", backgroundColor: "whitesmoke" }}>
                  <Timer />
                </Box>
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
  }
}

export default NavigationBar;