import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';
import './scss/Root.scss';

import Login from './components/Login';
import Signup from './components/Signup';
import Home from './components/Home';
import { setAuthToken } from './helpers/setAuthToken';
import Navbar from './components/Navbar';

import { history } from './helpers/history';

function App() {

  const token = localStorage.getItem("token");
  if (token) {
    setAuthToken(token);
  }


  return (
    <BrowserRouter history={history}>

      <div className="App">
        <Navbar />

      </div>

      <Routes>
        <Route path='\' exact  />
        <Route path="/login" element={<Login />} />
        <Route path="/signUp" element={<Signup />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
