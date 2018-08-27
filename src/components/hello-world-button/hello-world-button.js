import './hello-world-button.scss'

class HelloWorldButton{
  bottonCssClass = 'hello-world-button';

  render(){
    const button = document.createElement('button');

    button.innerHTML = 'Hello World';
    button.classList.add( this.bottonCssClass );
    button.onclick = () => {
      // const p = document.createElement('p');
      // p.innerHTML = 'Hello World';
      // p.classList.add('hello-world-text');
      // body.appendChild( p );

      fetch(window.HOST + 'Front/auth/ping')
      .then(response => {
        debugger;
      });
    }

    const body = document.querySelector('body');

    body.appendChild( button );
  }
}

export default HelloWorldButton;
