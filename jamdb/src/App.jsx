import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import './scss/Root.scss';
import Login from './components/Login';
import Signup from './components/Signup';
import { setAuthToken } from './helpers/setAuthToken';
import Navbar from './components/Navbar';
import { history } from './helpers/history';
import Grid from './components/Grid';
import MediaPage from './pages/MediaPage';

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
        <Route path='/' exact element={<Grid/>}  />
        <Route path="/login" element={<Login />} />
        <Route path="/signUp" element={<Signup />} />
        <Route path="/media/:contentId" element={<MediaPage/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
