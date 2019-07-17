import React from 'react';
import './Navigation.css';
import { Link } from 'react-router-dom';


const Navigation: React.FC<{ environment: string }> = ({ environment }) => {
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
      </ul>
    </nav>
  );
};

export default Navigation;
