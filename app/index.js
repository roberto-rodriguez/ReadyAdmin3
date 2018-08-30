
  import React from 'react';
  import ReactDOM from 'react-dom';

// Needed for redux-saga es6 generator support
import 'babel-polyfill';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css'

import App from './App';
import HOST from './ENV'


if(process.env.NODE_ENV === 'production'){
  console.log('Production Mode');
  window.HOST = '/';
}else{
  console.log('Development Mode');
   window.HOST = HOST;
}


const MOUNT_NODE = document.getElementById('app');

  ReactDOM.render(
    <App/>,
    MOUNT_NODE,
  );
 
