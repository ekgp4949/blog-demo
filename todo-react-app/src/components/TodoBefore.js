import React from "react";
import { ListItem, ListItemText, InputBase, ListItemIcon, Avatar } from "@mui/material"
import { SentimentNeutral, SentimentVerySatisfied } from "@mui/icons-material";

class TodoBefore extends React.Component {
    constructor(props) {
        super(props);

        this.state = { item: props.item, readOnly: true, stamps: props.stamps };
    }

    render() {
        const item = this.state.item;
        const goodStamp = this.state.stamps.goodStampSrc ? 
            <Avatar sx={{ bgcolor: "green" }} alt="Good Stamp" src={ this.state.stamps.goodStampSrc } />
            : <SentimentVerySatisfied sx={{ color: "#D4AF37" }} />;
        const badStamp = this.state.stamps.badStampSrc ? 
            <Avatar sx={{ bgcolor: "red" }} alt="Bad Stamp" src={ this.state.stamps.badStampSrc  } />
            : <SentimentNeutral />;
        return (
            <ListItem
                divider
                dense
            >   
                <ListItemIcon>
                {
                    item.done? goodStamp : badStamp
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