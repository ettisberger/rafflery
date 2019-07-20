import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './app';
import {Provider} from 'react-redux';
import store from './store';

const root = (
    <Provider store={store}>
        <App/>
    </Provider>
);

ReactDOM.render(root, document.getElementById('root'));
