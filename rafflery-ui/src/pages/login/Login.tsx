import React, {Component, Dispatch} from 'react';
import './Login.css';
import {connect} from 'react-redux';
import {loggedIn} from '../../actions/actions';
import {ApplicationState} from '../../reducers/root.reducer';
import auth0Service from '../../auth/AuthService';
import LoginForm from './LoginForm';

interface AuthProps {
    email: string;
}

const mapStateToProps = (state: ApplicationState) => {
    return {
        email: state.authState.email,
    };
};

const mapDispatchToProps = (dispatch: Dispatch<any>) => ({
    loggedIn: (email: string) => dispatch(loggedIn(email)),
});

class Login extends Component<AuthProps> {
    constructor(props: any) {
        super(props);

        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
    }

    public render() {

        const isAuthenticated = auth0Service.authenticated.toString();

        return (
            <div className="login">
                <h2>Login</h2>
                <br/>
                <LoginForm
                    handleLogin={(values: any) => {
                        // handle login
                    }}
                    handleLoginAuth0={(values: any) => {
                        this.login();
                    }}
                    handleLogout={() => auth0Service.logout()}
                />
                {/*<button onClick={this.login}>Login Auth0 Popup</button>*/}
                {/*<button onClick={this.logout}>Logout</button>*/}
                <br/>
                <span>{isAuthenticated}</span>
            </div>
        );
    }

    private login() {
        auth0Service.login();
    }

    private logout() {
        auth0Service.logout();
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
