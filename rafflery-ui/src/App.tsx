import React from 'react';
import {Route, Router} from 'react-router-dom';
import history from './history/History';
import './App.css';
import Header from './layout/header/Header';
import Home from './pages/home/Home';
import Footer from './layout/footer/Footer';
import Login from './pages/login/Login';
import Callback from './pages/login/Callback';

const App: React.FC = () => {

    return (
        <Router history={history}>
            <div className="App">
                <Header/>
                <main className="main">
                    <Route name="home" exact={true} path="/" component={Home}/>
                    <Route name="login" exact={true} path="/login" component={Login}/>
                    <Route name="callback" exact={true} path="/callback" component={Callback}/>
                    <Footer/>
                </main>
            </div>
        </Router>
    );
};

export default App;
