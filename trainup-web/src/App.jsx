import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import './styles/buttons.css'
import './styles/default.css'
import './styles/textbar.css'
import LandingPage from './landingPage/LandingPage'
import Login from './login/Login'
import { LoginProvider } from './context/LoginContext'
import Navbar from './navbar/Navbar'

function App() {

  // return (
  //   <div className='trainUp-center'>
  //     <h1>TRAIN UP</h1>
  //     <h3>TU MEJOR APLICACION DE ENTRENAMIENTO</h3>
  //     <div className='flx '>
  //       <button className='default-btn primary-btn'>Iniciar sesion</button>
  //       <button className='default-btn primary-btn'>Registrarse</button>
  //     </div>
  //   </div>
  // )
  return (
    <BrowserRouter>
    <LoginProvider>
      <Navbar/>
      <Routes>
        <Route path='/' element={<Navigate to='/init'/>}/>
        <Route path='/init' element={<LandingPage/>} />
        <Route path='/home' element={<h1>Home</h1>} />
        <Route path='/login' element={<Login/>} />
        <Route path='*' element={<Navigate to='/init'/>}/>
      </Routes>
      </LoginProvider>
    </BrowserRouter>
  )
}

export default App
