
import './heading.scss'

class Heading{

  render(user){
  const body = document.querySelector('body');

  const alert = document.createElement('div');
  alert.classList.add('alert');
  alert.classList.add('alert-primary');
  alert.setAttribute('role', 'alert');
  alert.innerHTML = 'This is a primary alert-check it out!';

    const span = document.createElement('span');
    span.classList.add('loading');
    span.classList.add('fa');
    span.classList.add('fa-spinner');
    span.classList.add('fa-spin');
    alert.appendChild(span);

  body.appendChild(alert);

  const h1 = document.createElement('h1');
  h1.innerHTML = 'Webpack course taken by ' + user;
  body.appendChild(h1);

  }
}

export default Heading;
