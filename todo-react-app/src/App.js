import { Paper, List } from '@material-ui/core';
import React from 'react';
import './App.css';
import Todo from './Todo'
import AddTodo from './AddTodo'
import { call } from './service/ApiService'

class App extends React.Component {
  
  constructor(props) {
    super(props);

    this.state = {
      items: []
    }
  }

  componentDidMount() {
    call("/todo", "GET", null).then((response) => {
      console.log(response)
      this.setState({ items: response.data })
    }, (error) => {
      console.log(error.error)
    });
  };

  add = (item) => {
    call("/todo", "POST", item).then((response) => 
      this.setState({ items: response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  delete = (item) => {
    call("/todo", "DELETE", item).then((response) => 
      this.setState({ items : response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  };

  update = (item) => {
    console.log(item)
    call("/todo", "PUT", item).then((response) => 
      this.setState({ items: response.data })
    ).catch((error) => {
      console.log(error.error)
    });
  }

  checkItems = () => {
    console.log(this.state.items)
  };

  render() {

    var todoItems = this.state.items.length > 0 && (<Paper>
        <List>
          {
            this.state.items.map((item, index) => (
              <Todo 
                checkItems={this.checkItems} 
                item={item} 
                key={item.id} 
                delete={this.delete} 
                update={this.update}/>
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
  };

}

export default App;
