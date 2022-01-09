import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { Avatar, Button, Grid } from '@mui/material';
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
    .then((response) => {

    }).catch((error) => {
      alert("에러가 발생했습니다.")
    });
  };

  render() {
    const style = {
      position: 'absolute',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)',
      width: 400,
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
            <Box mt={4}>
              <Grid container>
                <Grid item sm={4}>
                  { stamp }
                </Grid>
                <Grid item sm={4}>
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
                <Grid item sm={4}>
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
      </div>
    );
  }
}

export default StampModal;