import React from 'react';
import './Navigation.css';
import { Link } from 'react-router-dom';

interface NavigationProps {
  environment: string;
  user: string;
}

const Navigation: React.FC<NavigationProps> = ({ environment, user }) => {
  return (
    <nav className="navigation">
      <ul>
        <li><Link to="">Home</Link></li>
        {
          environment === 'prod' &&
          <li><Link to="login">Login</Link></li>
        }
        {
          environment === 'dev' &&
          <li><Link to="impersonation">Impersonation</Link></li>
        }
        <li>Raffles</li>
        {
          user && user !== 'Guest' &&
          <li>Logged in as {user}</li>
        }

      </ul>
    </nav>
  );
};

export default Navigation;
