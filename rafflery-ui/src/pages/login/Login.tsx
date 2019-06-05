import React, {Component} from 'react';
import './Login.css';
import AuthService from '../../auth/AuthService';
import {RouteComponentProps, withRouter} from 'react-router';

interface LoginState {
    authService: AuthService;
}

class Login extends Component<{} & RouteComponentProps<{}>, LoginState> {

    constructor(props: any) {
        super(props);

        this.state = { authService: new AuthService() };
        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
    }

    public async componentDidMount() {
        await this.state.authService.handleAuthentication();
    }

    private login() {
        this.state.authService.login();
    }

    private logout() {
        this.state.authService.logout();
    }

    render() {
        return (
            <div className="login">
                <h2>Login</h2>
                <br/>
                <button onClick={this.login}>Login</button>
                <button onClick={this.logout}>Logout</button>
                <br/>
                <span>{this.state.authService.authenticated.toString()}</span>
            </div>
        );
    }
}

export default withRouter(Login);
