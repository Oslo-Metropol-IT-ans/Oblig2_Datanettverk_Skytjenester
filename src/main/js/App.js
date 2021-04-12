import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import './App.css';
import Frontpage from './Components/Frontpage'
import Register from './Components/Register'
import Login from './Components/Login';
import Rooms from './Components/Rooms';
import Room from './Components/Room';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Route path="/" exact>
          <h1>Hello react</h1>
          <Frontpage />
        </Route>
        <Route path="/register" exact>
          <Register />
        </Route>
        <Route path="/login" exact>
          <Login />
        </Route>
        <Route path="/rooms/:id">
          <Rooms />
        </Route>
        <Route path="/room/:id/:userId">
          <Room />
        </Route>
      </div>
    </Router>
  );
}

export default App;
