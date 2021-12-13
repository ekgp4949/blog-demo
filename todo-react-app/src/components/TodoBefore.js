import React from "react";
import { ListItem, ListItemText, InputBase, ListItemAvatar, Avatar } from "@mui/material"
import { Clear, Done } from "@mui/icons-material";

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
                <ListItemAvatar>
                    <Avatar>
                    {
                        item.done? <Done /> : <Clear />
                    }
                    </Avatar>
                </ListItemAvatar>
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