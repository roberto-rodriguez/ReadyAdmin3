
/**
 *
 * App
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 */

import React from 'react';
// import { Helmet } from 'react-helmet';
// import styled from 'styled-components';
// import { Switch, Route } from 'react-router-dom';
// import Header from 'cmp/header'

// import HomePage from 'containers/HomePage/Loadable';
// import FeaturePage from 'containers/FeaturePage/Loadable';
// import NotFoundPage from 'containers/NotFoundPage/Loadable';
// import Header from 'components/Header';
// import Footer from 'components/Footer';

// const AppWrapper = styled.div`
//   margin: 0 auto;
//   display: flex;
//   min-height: 100%;
//   padding: 0 16px;
//   flex-direction: column;
// `;

class App extends React.Component {
  render() {
    return (
       <div>
         <h1>{'HEADERS'}</h1>
       </div>
    );
  }
}

export default App;

/*
<Switch>
  <Route exact path="/" component={HomePage} />
  <Route path="/features" component={FeaturePage} />
  <Route path="" component={NotFoundPage} />
</Switch>
<Footer />


<AppWrapper>
  <Helmet titleTemplate="%s - Template" defaultTitle="Title">
    <meta name="description" content="A React.js application" />
  </Helmet>

  <Header />

</AppWrapper>
*/
