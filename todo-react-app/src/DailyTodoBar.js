import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';

export default function DailyTodoBar() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Box sx={{ maxWidth: '100%', bgcolor: 'background.paper'}}>
      <Tabs
        value={value}
        onChange={handleChange}
        variant="scrollable"
        scrollButtons
        allowScrollButtonsMobile
        aria-label="scrollable force tabs example"
        centered
      >
        <Tab label="월요일" />
        <Tab label="화요일" />
        <Tab label="수요일" />
        <Tab label="목요일" />
        <Tab label="금요일" />
        <Tab label="토요일" />
        <Tab label="일요일" />
      </Tabs>
    </Box>
  );
}
