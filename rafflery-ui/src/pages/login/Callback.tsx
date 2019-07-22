import React, { Component } from 'react';
import auth0Service from '../../auth/AuthService';
import { RouteComponentProps, withRouter } from 'react-router';
import history from '../../history/History';

class Callback extends Component<RouteComponentProps, {}> {
  public async componentDidMount() {
    await auth0Service.handleAuthentication();
    history.replace('/');
  }

  public render() {
    return <p>Loading...</p>;
  }
}

export default withRouter(Callback);
