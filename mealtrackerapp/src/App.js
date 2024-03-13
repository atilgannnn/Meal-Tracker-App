import './App.css';
import React from 'react';
import Mealtracker from './components/mealtracker/Mealtracker';
import Desktop from './components/desktop/Desktop';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <div className='App'>
      <Router>
        <Routes>
          <Route path='/' element={<Mealtracker />} />
          <Route path='/desktop' element={<Desktop />} />{' '}
        </Routes>
      </Router>
    </div>
  );
}

export default App;
