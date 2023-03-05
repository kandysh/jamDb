import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Signup from './components/Signup';
import Grid from './components/Grid';
import Home from './components/Home';
import MediaCard from './components/MediaCard';
import MediaCarousel from './components/MediaCarousel.jsx';

function App() {
  return (
    <BrowserRouter>

      <div className="App">
        <header className="App-header">
          <h2>JamDB</h2>
        </header>
        <MediaCarousel/>
        {/* <MediaCard/> */}
        {/* <Grid /> */}
        <div className="main-container">
          <div className="left">
            {/* <Login /> */}
          </div>
          <div className="right">
            <Signup />
          </div>
        </div>
      </div>

      <Routes>
        <Route path='\' exact element={<Home/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
