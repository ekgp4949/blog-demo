import { Button, Container, Grid, TextField, Typography, Link } from "@mui/material";
import React from "react";
import { signin } from "./service/ApiService";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    // event.target 은 <form> node가 옴
    const data = new FormData(event.target);
    let email = data.get("email");
    let password = data.get("password");
    if(event.nativeEvent.submitter.id === "testAccountLogin") {
      email = "a";
      password = "a";
    }
    signin({ email: email, password: password });
  }

  render() {
    return (
      <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Typography component="h1" variant="h5">
              로그인
            </Typography>
          </Grid>
        </Grid>
        <form noValidate onSubmit={this.handleSubmit}>
          {" "}
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField 
                variant="outlined"
                required
                fullWidth
                name="email"
                label="이메일 주소"
                type="email"
                id="email"
                autoComplete="email"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="패스워드"
                type="password"
                id="password"
                autoComplete="current-password"
              />
            </Grid>
            <Grid item xs={12}>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
              >
                로그인
              </Button>
            </Grid>
            <Grid item xs={12}>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                id="testAccountLogin"
              >
                테스트 계정 경험하기
              </Button>
            </Grid>
            <Grid item>
              <Link href="/signup" variant="body2">
                새 계정 만들기
              </Link>
            </Grid>
          </Grid>
        </form>
      </Container>
    );
  }

}

export default Login;