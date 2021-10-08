import { Paper, List } from '@material-ui/core';
import React from 'react';
import './App.css';
import Todo from './Todo'
import AddTodo from './AddTodo'

class App extends React.Component {
  
  constructor(props) {
    super(props);

    this.state = {
      items: []
    }
  }

  add = (item) => {
    const thisItems = this.state.items;
    item.id = "ID-" + thisItems.length;
    item.done = false;
    thisItems.push(item);
    this.setState({ items: thisItems });
  };

  delete = (item) => {
    const thisItems = this.state.items;
    const newItems = thisItems.filter(e => e.id !== item.id);
    this.setState({ items : newItems });
  };


  render() {

    var todoItems = this.state.items.length > 0 && (<Paper>
        <List>
          {
            this.state.items.map((item, index) => (
              <Todo item={item} key={item.id} delete={this.delete} />
            ))
          }
        </List>
      </Paper>
    );

    /*
    for(let i = 0; i < this.state.item.length; i++ ) {
      todoItems.push((<Todo item={this.state.item[i]} key={this.state.item[i].id} />));
    }
    */
    return (
      <div className="App">
        <AddTodo add={this.add}/>
        {todoItems}
      </div>
    );
  }

}

export default App;
