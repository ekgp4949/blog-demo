import React from "react";
import App from "./App";
import Login from "./Login";
import { Box, Container } from "@mui/material";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUp from "./SignUp"

class AppRouter extends React.Component {

  render() {
    return (
      <Container disableGutters={true} maxWidth="sm">
        <Box height="100vh">
          <Router>
            <Switch>
              <Route path="/login">
                <Login />
              </Route>
              <Route path="/signup">
                <SignUp />
              </Route>
              <Route path="/">
                <App />
              </Route>
            </Switch>
          </Router>
        </Box>
      </Container>
    );
  }
}

export default AppRouter;