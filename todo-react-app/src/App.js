import React from 'react';
import './App.css';
import Todo from './Todo'

class App extends React.Component {
  
  constructor(props) {
    super(props);

    this.state = {
      item: [
        { id: 0, title: "Hello world 1", done: true },
        { id: 1, title: "Hello world 2", done: false },
        { id: 2, title: "Hello world 3", done: true },
      ]
    }
  }

  render() {

    var todoItems = this.state.item.map((item, index) => (
      <Todo item={item} key={item.id} />
    ));

    /*
    for(let i = 0; i < this.state.item.length; i++ ) {
      todoItems.push((<Todo item={this.state.item[i]} key={this.state.item[i].id} />));
    }
    */
    return (
      <div className="App">
        {todoItems}
      </div>
    );
  }

}

export default App;
