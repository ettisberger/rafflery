import React, { Component, Dispatch } from 'react';
import './Login.css';
import { connect } from 'react-redux';
import { loggedIn } from '../../actions/actions';
import { ApplicationState } from '../../root.reducer';
import auth0Service from '../../auth/AuthService';
import LoginForm from './LoginForm';

interface AuthProps {
  name: string;
}

interface AuthState {
  userProfile: any;
}

const mapStateToProps = (state: ApplicationState) => {
  return {
    name: state.authState.name,
  };
};

const mapDispatchToProps = (dispatch: Dispatch<any>) => ({
  loggedIn: (email: string) => dispatch(loggedIn(email)),
});

class Login extends Component<AuthProps, AuthState> {
  constructor(props: any) {
    super(props);

    this.login = this.login.bind(this);
    this.logout = this.logout.bind(this);
    this.loginWithAuth0 = this.loginWithAuth0.bind(this);

    this.state = { userProfile: {} };

    if (auth0Service.authenticated) {
      auth0Service.getProfile((err: any, user: any) => {
        if (user) {
          this.setState({ userProfile: user });
        }
      });
    }
  }

  public render() {
    const isAuthenticated = auth0Service.authenticated.toString();

    return (
      <div className="login">
        <h2>Login</h2>
        <br />
        <LoginForm
          handleLogin={(values: any) => {
            this.login(values.username, values.password);
          }}
          handleLoginAuth0={() => {
            this.loginWithAuth0();
          }}
          handleLogout={() => auth0Service.logout()}
        />
        <br />
        <span>Authenticated: {isAuthenticated}</span>
        <br />
        <span>Email: {this.state.userProfile.email}</span>
      </div>
    );
  }

  private loginWithAuth0() {
    auth0Service.loginWithAuth0();
  }

  private login(username: string, password: any) {
    auth0Service.login(username, password);
  }

  private logout() {
    auth0Service.logout();
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login);
