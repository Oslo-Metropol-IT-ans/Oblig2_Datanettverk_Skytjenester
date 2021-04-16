import React from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
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
        <Switch>
          <Route path="/" exact>
            <Frontpage />
          </Route>
          <Route path="/register" exact>
            <Register />
          </Route>
          <Route path="/login" exact>
            <Login />
          </Route>
          <Route path="/rooms/">
            <Rooms />
          </Route>
          <Route path="/room/:id/">
            <Room />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
