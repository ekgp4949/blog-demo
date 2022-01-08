import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { Avatar, Button, Grid } from '@mui/material';
import { Save, SentimentNeutral, SentimentVerySatisfied } from '@mui/icons-material';

class StampModal extends React.Component{
  constructor(props) {
    super(props);
    this.state = { open: props.open, stampSrc: props.stampSrc, stampType: props.type, uploadedImg: null }
    this.close = props.handleClose;
    console.log(this.state)
  }

  handleClose = () => {
    this.close();
    this.setState({ open: false });
  };

  uploadImg = (e) => {
    const uploadedImg = e.target.files[0];
    this.setImgFile(uploadedImg)
    this.setState({ uploadedImg: uploadedImg });

    const reader = new FileReader();
    reader.onload = () => {
      this.setState({ stampSrc: reader.result });
    };
    reader.readAsDataURL(uploadedImg);
  };

  updateStamp = () => {

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
      stamp = this.state.stampSrc ? 
        <Avatar sx={{ bgcolor: "green" }} alt="Good Stamp" src={ this.state.stampSrc } />
        : <SentimentVerySatisfied sx={{ color: "green" }} />;
    } else {
      stamp = this.state.stampSrc ? 
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