import React from "react";
import { Accordion, AccordionDetails, AccordionSummary, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import DailyTodoList from "../components/DailyTodoList";

const days = [null, "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"];

class DailyPlanScreen extends React.Component {

  render() {

    return (
      days.map((value, index) => {
        if(index === 0) return null;
        return (
          <Accordion 
            disableGutters
            square
            key={index}
            sx={{ boxShadow: "none", border: 0.7, borderColor: "#f4f4f4", 
              '&:not(:last-child)': { borderBottom: 0 } 
            }}
          >
            <AccordionSummary
              expandIcon={<ExpandMore />}
              sx={{ backgroundColor: "#f9f9f9" }}
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