import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';

import './App.css';
import Header from './layout/header/Header';
import Home from './home/Home';
import Footer from './layout/footer/Footer';
import Login from './login/Login';

const App: React.FC = () => {
  return (
      <Router>
        <div className="App">
          <Header/>
          <main className="main">
              <Route name="home" exact={true} path="/" component={Home} />
              <Route name="login" exact={true} path="/login" component={Login} />
              <Footer/>
          </main>
        </div>
      </Router>
  );
};

export default App;
