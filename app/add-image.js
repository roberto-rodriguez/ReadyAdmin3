import Agent from './agent.jpg'

function addImage(){
  var img = document.createElement('img');
  img.alt = 'Agent';
  img.width = '300';
  img.src = Agent;

  const body  = document.querySelector('body');
  body.appendChild(img);

}

export default addImage;
