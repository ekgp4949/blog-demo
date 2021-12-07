import React from "react";
import { call } from "../service/ApiService"
import Todo from "../components/Todo"
import { Accordion, AccordionDetails, AccordionSummary, List, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";

const days = [null, "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"];

class DailyPlanScreen extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      items: [],
      loading: true
    };
  }

  componentDidMount() {
    call("/todo", "GET", null).then((response) => {
      this.setState({ items: response.data, loading: false })
    }, (error) => {
      console.log(error.error)
    });
  };

  render() {
    return (
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMore />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
          <Typography>Accordion 1</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
            malesuada lacus ex, sit amet blandit leo lobortis eget.
          </Typography>
        </AccordionDetails>
      </Accordion>
      // <List>
      //   { this.state.items.map(i => <Todo item={i}/>) }
      // </List>
    );
  }
}

export default DailyPlanScreen;