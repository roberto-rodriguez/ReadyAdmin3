import helloWorld from './hello-world.js'
import addImage from './add-image.js'
import HelloWorldButton from './components/hello-world-button/hello-world-button.js'
import Heading from './components/heading/Heading.js'
import _ from 'lodash'
import HOST from './ENV'
import 'bootstrap';
import './index.scss'
import 'bootstrap/dist/css/bootstrap.min.css'

const heading = new Heading();

addImage();

 var userName = _.upperFirst('roberto');
heading.render(userName);

const helloWorldButton = new HelloWorldButton();
helloWorldButton.render();



if(process.env.NODE_ENV === 'production'){
  console.log('Production Mode');
  window.HOST = '/';
}else{
  console.log('Development Mode');
   window.HOST = HOST;
}
