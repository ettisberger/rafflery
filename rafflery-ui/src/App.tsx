import React from 'react';
import {Router, Route} from 'react-router-dom';
import history from './history/History';
import './App.css';
import Header from './layout/header/Header';
import Home from './pages/home/Home';
import Footer from './layout/footer/Footer';
import Login from './pages/login/Login';

const App: React.FC = () => {
  return (
      <Router history={history}>
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
