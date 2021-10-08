import React from 'react';
import { Button, Grid, Paper, TextField } from "@material-ui/core";

class AddTodo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: { title: "" } };
        this.add = props.add;
    }

    onInputChanged = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
    };

    onButtonClick = (e) => {
        const thisItem = this.state.item;
        this.add(thisItem);
        this.setState({ item: { title: "" } });
    };

    enterKeyEventHandler = (e) => {
        if(e.key === 'Enter') {
            this.onButtonClick();
        }
    };

    render() {
        return (
            <Paper style={{ margin: 16, padding: 16 }}>
                <Grid container>
                    <Grid xs={10} md={11} item style={{ paddingRight: 16 }}>
                        <TextField 
                        placeholder="Add Todo Here" 
                        fullWidth
                        onChange={this.onInputChanged}
                        onKeyPress={this.enterKeyEventHandler}
                        value={this.state.item.title}
                        />
                    </Grid>
                    <Grid xs={2} md={1} item>
                        <Button 
                        fullWidth 
                        color="secondary" 
                        variant="outlined"
                        onClick={this.onButtonClick}
                        >
                            +
                        </Button>
                    </Grid>
                </Grid>                
            </Paper>
        );
    }
}

export default AddTodo;