import React from "react";
import { Accordion, AccordionDetails, AccordionSummary, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import DailyTodoList from "../components/DailyTodoList";

const days = [null, "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"];

class DailyPlanScreen extends React.Component {
  constructor(props) {
    super(props);

  }

  render() {
    return (
      days.map((value, index) => {
        if(index == 0) return;
        return (
          <Accordion 
            disableGutters={true}
            square={true}
          >
            <AccordionSummary
              expandIcon={<ExpandMore />}
              aria-controls="panel1a-content"
              id="panel1a-header"
            >
              <Typography>{ value }</Typography>
            </AccordionSummary>
            <AccordionDetails>
              <DailyTodoList 
                dayOfWeek={index}
              />
            </AccordionDetails>
          </Accordion>
        )
      })
    );
  }
}

export default DailyPlanScreen;