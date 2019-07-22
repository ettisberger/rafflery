import * as React from 'react';
import './Impersonation.css';
import { hmacAuth } from '../../auth/HmacAuth';

const impersonationUsers = ['Andy', 'Nick', 'Guest'];

export interface ImpersonationProps {
  loggedInUser: string;
  onLoggedIn: (args: any) => any;
}

const Impersonation: React.FC<ImpersonationProps> = ({
  loggedInUser,
  onLoggedIn,
}) => {
  const onSelectUser = (user: string) => {
    user === 'Guest' ? hmacAuth.clearToken() : hmacAuth.setToken(user);

    onLoggedIn(user);
  };

  return (
    <div className="impersonation">
      <h1>Act as</h1>
      <div className="mock-users">
        {impersonationUsers.map(user => (
          <div
            key={user}
            onClick={() => onSelectUser(user)}
            className={loggedInUser === user ? 'selected' : ''}
          >
            {user}
          </div>
        ))}
      </div>
      <p>{loggedInUser && `You are logged in as ${loggedInUser}`}</p>
    </div>
  );
};

export default Impersonation;
