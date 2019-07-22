import React, { Component } from 'react';
import './LoginForm.css';

interface LoginFormProps {
  handleLogin: any;
  handleLoginAuth0: any;
  handleLogout: any;
}

interface LoginFormState {
  username: string;
  password: string;
}

const handleSubmit = (e: any, state: LoginFormState, handler: any) => {
  e.preventDefault();
  handler(state);
};

class LoginForm extends Component<LoginFormProps, LoginFormState> {
  constructor(props: LoginFormProps) {
    super(props);

    this.state = { username: 'admin@rafflery.ch', password: 'Test123456' };
  }

  public render() {
    return (
      <div className="login-form-container">
        <form className="login-form" name="login-form">
          <input
            className="login-form__login-name"
            type="text"
            value={this.state.username}
            onChange={e =>
              this.setState({
                username: e.target.value,
              })
            }
            placeholder="username"
          />
          <input
            className="login-form__login-password"
            type="password"
            value={this.state.password}
            onChange={e =>
              this.setState({
                password: e.target.value,
              })
            }
            placeholder="password"
          />
          <div className="login-form__actions">
            <button
              className="login-form__login-button"
              type="submit"
              onClick={e => handleSubmit(e, this.state, this.props.handleLogin)}
            >
              Login
            </button>
            <button
              className="login-form__login-button"
              type="submit"
              onClick={e =>
                handleSubmit(e, this.state, this.props.handleLoginAuth0)
              }
            >
              Login Auth0
            </button>
            <button
              className="login-form__logout-button"
              onClick={e =>
                handleSubmit(e, this.state, this.props.handleLogout)
              }
            >
              Logout
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default LoginForm;
