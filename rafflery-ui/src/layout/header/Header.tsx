import React from 'react';
import './Header.css';
import Navigation from './Navigation';

interface HeaderProps {
  environment: string;
  user: string;
}

const Header: React.FC<HeaderProps> = ({ environment, user }) => {
  return (
    <div className="header">
      <Navigation environment={environment} user={user} />
      <div className="header__content">
        <div className="header__title">
          <h1>RAFFLERY</h1>
          <div id="header__subtitle__first">
            <p>slots</p>
            <p>prizes</p>
          </div>
          <div id="header__subtitle__second">
            <span>
              <p>chances</p>
            </span>
          </div>
        </div>
      </div>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 100 100"
        preserveAspectRatio="none"
      >
        <polygon fill="white" points="0,100 100,40 100,100" />
      </svg>
    </div>
  );
};

export default Header;
