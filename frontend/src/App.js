import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import Navbar from './layout/Navbar.js';
import Home from './pages/Home.js';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddUser from './users/AddUser.js';
import EditUser from './users/EditUser.js';
import ViewUser from './users/ViewUser.js';

function App() {
  return (
    <div className="App">
    <Router>
      <Navbar/>

      <Routes>
        <Route exact path='/' element={<Home />}/>
         <Route exact path='/addUser' element={<AddUser />}/>
          <Route exact path='/editUser/:id' element={<EditUser />}/>
          <Route exact path="/viewUser/:id" element={<ViewUser />} />
      </Routes>

    </Router>
    </div>
  );
}

export default App;
