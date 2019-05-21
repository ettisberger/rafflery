import React from 'react';
import './Navigation.css';
import {Link} from 'react-router-dom';

const Navigation: React.FC = () => {
    return (
        <nav className="navigation">
            <ul>
                <li><Link to="">Home</Link></li>
                <li><Link to="login">Login</Link></li>
                <li>Raffles</li>
            </ul>
        </nav>
    );
};

export default Navigation;
