import React from "react";
import { ListItem, ListItemText, InputBase, ListItemIcon } from "@mui/material"
import { SentimentNeutral, SentimentVerySatisfied } from "@mui/icons-material";

class TodoBefore extends React.Component {
    constructor(props) {
        super(props);

        this.state = { item: props.item, readOnly: true };
    }

    render() {
        const item = this.state.item;
        return (
            <ListItem
                divider
                dense
            >   
                <ListItemIcon>
                {
                    item.done? <SentimentVerySatisfied sx={{ color: "#c7c7c7" }} /> : <SentimentNeutral />
                }
                </ListItemIcon>
                <ListItemText>
                    <InputBase 
                        inputProps={{ 
                            "aria-label" : "naked",
                            readOnly: this.state.readOnly 
                        }}
                        readOnly
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                    />
                </ListItemText>
            </ListItem>
        );
    }
}

export default TodoBefore;