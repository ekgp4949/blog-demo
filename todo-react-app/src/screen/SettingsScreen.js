import { SentimentNeutral, SentimentVerySatisfied } from "@mui/icons-material";
import { Typography, Box, Stack, Avatar, Paper } from "@mui/material";
import React from "react";

class SettingsScreen extends React.Component {


  changeImg = () => {
    alert("hi1")
  }

  render() {
    return (
      <Box padding={3}>
        <Typography variant="header6">도장 설정</Typography>
        <Stack direction="row" mt={2} spacing={1} justifyContent="center" alignItems="center">
          <Paper onClick={this.changeImg} sx={{ width: "100px", height: "80px", cursor: "pointer" }}>
            <Box style={{ justifyContent: "center", display: "flex", alignItems: "center", height:"70%" }}>
              <Avatar sx={{ bgcolor: "green" }}>
                  <SentimentVerySatisfied />
              </Avatar>
            </Box>
            <Typography variant="caption" pb={0}>참 잘했어요</Typography>
          </Paper>
          <Paper onClick={this.changeImg} sx={{ width: "100px", height: "80px", cursor: "pointer" }}>
            <Box style={{ justifyContent: "center", display: "flex", alignItems: "center", height:"70%" }}>
              <Avatar sx={{ bgcolor: "red" }}>
                <SentimentNeutral />
              </Avatar>
            </Box>
            <Typography variant="caption">더 노력해요</Typography>
          </Paper>
        </Stack>
      </Box>
    );
  }
}

export default SettingsScreen;