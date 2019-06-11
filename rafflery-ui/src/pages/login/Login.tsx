import React, {Component, Dispatch} from 'react';
import './Login.css';
import AuthService from '../../auth/AuthService';
import {connect} from 'react-redux';
import {loggedIn} from '../../actions/actions';
import {ApplicationState} from '../../reducers/root.reducer';

interface AuthProps {
    email: string;
    authService: AuthService;
    loggedIn: any;
};

const mapStateToProps = (state: ApplicationState) => {
    return {
        authService: state.authState.auth,
        email: state.authState.email,
    };
};

const mapDispatchToProps = (dispatch: Dispatch<any>) => ({
    loggedIn: (email: string) => dispatch(loggedIn(email)),
});

class Login extends Component<AuthProps> {
    constructor(props: any) {
        super(props);

        console.log(props);

        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
    }

    public async componentDidMount() {
        // this should be on the route later with a callback component
        await this.props.authService.handleAuthentication();

        this.props.loggedIn(localStorage.getItem('email'));

    }

    private login() {
        this.props.authService.login();
    }

    private logout() {
        this.props.authService.logout();
    }

    render() {

        const authenticated = this.props.authService.authenticated.toString();

        return (
            <div className="login">
                <h2>Login</h2>
                <br/>
                <button onClick={this.login}>Login</button>
                <button onClick={this.logout}>Logout</button>
                <br/>
                <span>{authenticated}</span>
            </div>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
