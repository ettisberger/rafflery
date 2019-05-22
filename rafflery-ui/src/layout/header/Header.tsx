import React from 'react';
import './Header.css';
import Navigation from './Navigation';

const Header: React.FC = () => {
    return (
        <div className="header">
            <Navigation/>
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
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100" preserveAspectRatio="none">
                <polygon fill="white" points="0,100 100,40 100,100"/>
            </svg>
        </div>
    );
};

export default Header;
