import React from "react";
import "./index.css"
import App from "./App";
import Login from "./Login";
import { Box } from "@material-ui/core";
import { Typography } from "@material-ui/core";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUp from "./SignUp"

function Copyright() {
  return (
    <Typography color="textSecondary" align="center" variant="body2">
      {"Copyright Ⓒ "} ekgp4949, {new Date().getFullYear()} {"."}
    </Typography>
  );
}


class AppRouter extends React.Component {
  render() {
    return (
      <div>
        <Router>
          <div>
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
          </div>
          <Box mt={5}>
            <Copyright />
          </Box>
        </Router>
      </div>
    );
  }
}

export default AppRouter;