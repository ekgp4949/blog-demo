import React from "react";
import { ListItem, ListItemText, InputBase, ListItemSecondaryAction, IconButton, ListItemIcon } from "@mui/material"
import { AddTask, DeleteOutlined } from "@mui/icons-material";

class DailyTodo extends React.Component {
    constructor(props) {
        super(props);

        this.state = { item: props.item, readOnly: true };
        this.delete = props.delete;
        this.update = props.update;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item);
    }

    updateEventHandler = () => {
        this.update(this.state.item);
    }

    offReadOnlyMode = () => {
        this.setState({ readOnly: false })
    };

    enterKeyEventHandler = (e) => {
        if(e.key === 'Enter') {
            this.setState({ readOnly: true });
            this.update(this.state.item);
        }
    };

    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
    };

    checkBoxEventHandler = () => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({ item: thisItem });
        this.update(this.state.item);
    };

    render() {
        const item = this.state.item;
        return (
            <ListItem
                divider
                dense
            >
                <ListItemIcon>
                    <AddTask color="blue" />
                </ListItemIcon>
                <ListItemText>
                    <InputBase 
                        inputProps={{ 
                            "aria-label" : "naked",
                            readOnly: this.state.readOnly 
                        }}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        onClick={this.offReadOnlyMode}
                        onKeyPress={this.enterKeyEventHandler}
                        onChange={this.editEventHandler}
                    />
                </ListItemText>
                <ListItemSecondaryAction>
                    <IconButton aria-label="Delete Todo" onClick={this.deleteEventHandler}>
                        <DeleteOutlined/>
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}

export default DailyTodo;