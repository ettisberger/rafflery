import React from 'react';
import { Route, Router } from 'react-router-dom';
import history from '../history/History';
import './App.css';
import Header from '../layout/header/Header';
import Home from '../pages/home/Home';
import Footer from '../layout/footer/Footer';
import Login from '../pages/login/Login';
import Callback from '../pages/login/Callback';
import { http } from '../http/http';
import Impersonation from '../pages/impersonation';
import { hmacAuth } from "../auth/HmacAuth";

interface AppState {
  environment: string;
}

export interface AppProps {
  loggedIn: (args: any) => any
}

class App extends React.Component<AppProps, AppState> {

  constructor(props: any) {
    super(props);

    this.state = {
      environment: ''
    }
  }

  async componentDidMount() {
    const { environment } = await http.get('/api/ui-config');
    this.setState({ environment });

    const user = hmacAuth.getLoggedInUser();

    if (user) {
      this.props.loggedIn(user);
    }
  }

  render() {
    return (
      <Router history={history}>
        <div className="App">
          <Header environment={this.state.environment}/>
          <main className="main">
            <Route name="home" exact={true} path="/" component={Home}/>
            <Route name="login" exact={true} path="/login" component={Login}/>
            <Route name="callback" exact={true} path="/callback" component={Callback}/>
            <Route name="impersonation" exact={true} path="/impersonation" component={Impersonation}/>
            <Footer/>
          </main>
        </div>
      </Router>
    );
  }
}

export default App;
