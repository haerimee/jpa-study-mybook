import React from 'react';

import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './pages/Home';
import New from './pages/New';
import Book from "./pages/Book";
import Review from "./pages/Review";
import Edit from "./pages/Edit";

function App() {
    return (
        <BrowserRouter>
            <div className="App">
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/review' element={<Review />} />
                    <Route path='/new' element={<New />} />
                    <Route path='/edit/:id' element={<Edit />} />
                    <Route path='/book/:id' element={<Book />} />
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
