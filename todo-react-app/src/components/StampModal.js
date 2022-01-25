import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { Alert, Avatar, Button, Grid, Snackbar } from '@mui/material';
import { Save, SentimentNeutral, SentimentVerySatisfied } from '@mui/icons-material';
import { callForUpload } from '../service/ApiService';

class StampModal extends React.Component{
  constructor(props) {
    super(props);
    this.state = { open: props.open, stamp: props.stamp, stampSrc: props.stampSrc, stampType: props.type, uploadedImg: null }
    this.close = props.handleClose;
  }

  handleClose = () => {
    this.close();
    this.setState({ open: false });
    window.location.reload();
  };

  uploadImg = (e) => {
    const uploadedImg = e.target.files[0];
    if(!uploadedImg) return;

    const reader = new FileReader();
    reader.onload = () => {
      this.setState({ stampSrc: reader.result, uploadedImg: uploadedImg, stamp: "temp" });
    };

    reader.readAsDataURL(uploadedImg);
  };

  updateStamp = () => {
    callForUpload("/stamps/"+this.state.stampType, this.state.uploadedImg)
    .then(() => {
      this.handleAlertOpen();
    }).catch((error) => {
      alert(error)
    });
  };

  handleAlertOpen = () => {
    this.setState({ alertOpen: true });
  }

  handleAlertClose = () => {
    this.setState({ alertOpen: false });
  }

  render() {
    const style = {
      position: 'absolute',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)',
      width: "30%",
      bgcolor: 'background.paper',
      border: '2px solid #000',
      boxShadow: 24,
      p: 4,
    };

    let stamp;
    if(this.state.stampType === "good") {
      stamp = this.state.stamp ? 
        <Avatar sx={{ bgcolor: "green" }} alt="Good Stamp" src={ this.state.stampSrc } />
        : <SentimentVerySatisfied sx={{ color: "green" }} />;
    } else {
      stamp = this.state.stamp ? 
        <Avatar sx={{ bgcolor: "red" }} alt="Bad Stamp" src={ this.state.stampSrc  } />
        : <SentimentNeutral sx={{ color: "red" }} />;
    }

    return (
      <div> 
        <Modal
          open={ this.state.open }
          onClose={ this.handleClose }
          aria-labelledby="stampUpdateModal"
        >
          <Box sx={style}>
            <Typography id="stampUpdateModal" variant="h6" component="h2">
              { this.state.stampType }
            </Typography>
            <Box mt={2}>
              <Box style={{ justifyContent: "center", display: "flex" }}>
                { stamp }
              </Box>
              <Grid container style={{ justifyContent: "center", display: "flex" }}>
                <Grid item sm={8} mt={1}>
                  <Button
                    variant="contained"
                    component="label"
                  >
                    이미지 업로드
                    <input
                      accept="image/*"
                      type="file"
                      name="stampImg"
                      hidden
                      onChange={ this.uploadImg }
                    />
                  </Button>
                </Grid>
                <Grid item sm={4} mt={1}>
                  <Button
                    startIcon={ <Save /> }
                    variant="outlined"
                    component="label"
                    onClick={ this.updateStamp }
                  >
                    저장
                  </Button>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Modal>
        <Snackbar
          severity="success"
          open={this.state.alertOpen}
          onClose={this.handleAlertClose}
        >
          <Alert onClose={this.handleAlertClose} severity="success" sx={{ width: '100%' }}>
            저장했습니다.
          </Alert>
        </Snackbar>
      </div>
    );
  }
}

export default StampModal;