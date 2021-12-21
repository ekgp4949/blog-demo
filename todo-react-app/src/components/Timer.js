import { Typography } from '@mui/material';
import React from 'react';

const padNumber = (num, length) => {
  return String(num).padStart(length, '0');
};

class Timer extends React.Component {
  constructor(props) {
    super(props);
    
    this.state = this.getLeftTime();
  }

  refreshTodayPage = () => {
    if(window.location.pathname === "/today" ) {
      window.location.reload();
    }
  }

  getLeftTime = () => {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();

    return { 
      leftHour: padNumber(23 - hours, 2), 
      leftMin: padNumber(59 - minutes, 2), 
      leftSec: padNumber(59 - seconds, 2) 
    };

  };
  
  componentDidMount() {
    this.interval = setInterval(() => {
      const now = this.getLeftTime();
      this.setState(now); 
      
      const hours = now.leftHour;
      const minutes = now.leftMin;
      const seconds = now.leftSec;
      if(hours === "00" && minutes === "00" && seconds === "00") {
        this.refreshTodayPage();
      }
    }, 1000);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }
  
  render() {
    return (
      <Typography variant="h6" color="black">
        { this.state.leftHour + " : "+ this.state.leftMin + " : " + this.state.leftSec }
      </Typography>
    );
  }
}

export default Timer;