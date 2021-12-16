import { Typography } from '@mui/material';
import React from 'react';

const padNumber = (num, length) => {
  return String(num).padStart(length, '0');
};

class Timer extends React.Component {
  render() {
    return (
      <Typography variant="h6" color="black">
        0 : 0 : 0
      </Typography>
    );
  }
}

export default Timer;