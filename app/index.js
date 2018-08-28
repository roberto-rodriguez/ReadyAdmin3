
  import React from 'react';
  import ReactDOM from 'react-dom';
//
// Needed for redux-saga es6 generator support
import 'babel-polyfill';
import App from './App';

import HelloWorldButton from './components/hello-world-button/hello-world-button.js'
import Heading from './components/heading/Heading.js'
import _ from 'lodash'
import HOST from './ENV'
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css'


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

/*

const heading = new Heading();


 var userName = _.upperFirst('roberto');
heading.render(userName);

const helloWorldButton = new HelloWorldButton();
helloWorldButton.render();

*/
