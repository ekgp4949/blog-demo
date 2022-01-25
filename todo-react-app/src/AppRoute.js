import React from "react";
import App from "./App";
import Login from "./Login";
import { Box, Container } from "@mui/material";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import SignUp from "./SignUp"
import NotFoundScreen from "./screen/NotFoundScreen";

class AppRouter extends React.Component {

  render() {
    return (
      <Container disableGutters={true} maxWidth="sm" sx={{ minWidth: "380px" }}>
        <Box height="100vh">
          <Router>
            <Switch>
              <Route exact path="/login">
                <Login />
              </Route>
              <Route exact path="/signup">
                <SignUp />
              </Route>
              <Route exact path="/today">
                <App value="/today"/>
              </Route>
              <Route exact path="/weekly">
                <App value="/weekly"/>
              </Route>
              <Route exact path="/settings">
                <App value="/settings"/>
              </Route>
              <Route path='*' component={ NotFoundScreen } />
            </Switch>
          </Router>
        </Box>
      </Container>
    );
  }
}

export default AppRouter;