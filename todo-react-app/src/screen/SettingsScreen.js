import { SentimentNeutral, SentimentVerySatisfied } from "@mui/icons-material";
import { Typography, Box, Stack, Avatar, Paper } from "@mui/material";
import React from "react";
import Loading from "../Loading";
import { call } from "../service/ApiService";
import StampModal from "../components/StampModal"
import { API_STAMP_IMG_URL } from "../app-config";

class SettingsScreen extends React.Component {

  constructor(props) {
    super(props);

    this.state = ({ stamps: { goodStampSrc: null, badStampSrc: null, goodStamp: null, badStamp: null }, loading: true, 
      modalOpen: false, stampSrc: null, stampType: null, stamp: null });
  }

  componentDidMount() {
    call("/stamps", "GET", null).then((response) => {
      this.setState(
        { stamps: 
          { 
            goodStamp: response.goodStamp,
            badStamp: response.badStamp,
            goodStampSrc: API_STAMP_IMG_URL + response.goodStamp, 
            badStampSrc: API_STAMP_IMG_URL + response.badStamp,
          }, 
          loading: false }
      );
    }).catch((error) => {
      console.log(error.error)
    });
  }

  showStampModal = () => {
    return this.state.modalOpen ? 
      <StampModal open={ this.state.modalOpen } handleClose={ this.handleClose } type={ this.state.stampType } 
        stampSrc={ this.state.stampSrc } stamp={ this.state.stamp } /> : null;
  }

  changeGoodStampImg = () => {
    this.setState({ modalOpen: true, stampType: "good", stampSrc: this.state.stamps.goodStampSrc, 
      stamp: this.state.stamps.goodStamp });
  }

  changeBadStampImg = () => {
    this.setState({ modalOpen: true, stampType: "bad", stampSrc: this.state.stamps.badStampSrc,
      stamp: this.state.stamps.badStamp });
  }

  handleClose = () => {
    this.setState({ modalOpen: false })
  }

  render() {
    if(this.state.loading) {
      return <Loading />;
    } else {
      const goodStamp = this.state.stamps.goodStamp ? 
          <Avatar sx={{ bgcolor: "green" }} alt="Good Stamp" src={ this.state.stamps.goodStampSrc } />
          : <SentimentVerySatisfied sx={{ color: "green" }} />;
      const badStamp = this.state.stamps.badStamp ? 
          <Avatar sx={{ bgcolor: "red" }} alt="Bad Stamp" src={ this.state.stamps.badStampSrc  } />
          : <SentimentNeutral sx={{ color: "red" }} />;

      return (
        <Box padding={3}>
          <Typography variant="header6">도장 설정</Typography>
          <Stack direction="row" mt={2} spacing={1} justifyContent="center" alignItems="center">
            <Paper onClick={this.changeGoodStampImg} sx={{ width: "100px", height: "80px", cursor: "pointer" }}>
              <Box style={{ justifyContent: "center", display: "flex", alignItems: "center", height:"70%" }}>
                { goodStamp }
              </Box>
              <Typography variant="caption" pb={0}>참 잘했어요</Typography>
            </Paper>
            <Paper onClick={this.changeBadStampImg} sx={{ width: "100px", height: "80px", cursor: "pointer" }}>
              <Box style={{ justifyContent: "center", display: "flex", alignItems: "center", height:"70%" }}>
                { badStamp }
              </Box>
              <Typography variant="caption">더 노력해요</Typography>
            </Paper>
          </Stack>
          { this.showStampModal() }
        </Box>
      );
    }

  }
}

export default SettingsScreen;