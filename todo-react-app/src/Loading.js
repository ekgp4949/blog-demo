import { Container, Grid } from "@mui/material";
import React from "react";

class Loading extends React.Component {

  render() {
    return (
      <Container maxWidth="xs" style={{ marginTop: "8%" }}>
        <Grid container>
          <Grid item xs={12}>
            <h2>Loading...</h2>
          </Grid>
          <Grid item xs={12}>
            <h6>잠시 기다려주세요...</h6>
          </Grid>
        </Grid>
      </Container>
    );
  }
}

export default Loading;