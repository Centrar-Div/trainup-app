import axios from 'axios';
import { notification } from 'antd';
import 'antd/dist/reset.css';

// URL base de la API 
// const BASE_URL = 'http://localhost:8080/api';
axios.defaults.baseURL = "http://localhost:8080/api"

const handleError = (error) => {
  let errorMessage = 'Ocurrió un error inesperado';

  if (error.response) {
    errorMessage = error.response.data?.msg || 'Error en la respuesta del servidor';
  } else if (error.request) {
    errorMessage = 'No se pudo conectar al servidor. Inténtelo de nuevo más tarde.';
  }

  notification.error({
    message: 'Error',
    description: errorMessage,
    placement: 'topRight',
  });
  throw new Error(errorMessage);
};

/*
 * Funciones relacionadas con "Rutinas"
*/

const crearRutina = async (rutinaDTO) => {
  try {
    const response = await axios.post(`/rutinas`, rutinaDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerRutinas = async () => {
  try {
    const response = await axios.get(`/rutinas`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerRutinaPorId = async (id) => {
  try {
    const response = await axios.get(`/rutinas/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarRutina = async (rutinaID, rutina) => {
  try {
    const response = await axios.put(`/rutinas/${rutinaID}`, rutina);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const eliminarRutina = async (id) => {
  try {
    const response = await axios.delete(`/rutinas/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

/* 
* Funciones relacionadas con "Ejercicios"
*/

const crearEjercicio = async (ejercicioDTO) => {
  try {
    const response = await axios.post(`/ejercicios`, ejercicioDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerEjercicios = async () => {
  try {
    const response = await axios.get(`/ejercicios`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerEjercicioPorId = async (id) => {
  try {
    const response = await axios.get(`/ejercicios/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarEjercicio = async (ejercicioID, ejercicio) => {
  try {
    const response = await axios.put(`/ejercicios/${ejercicioID}`, ejercicio);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const eliminarEjercicio = async (id) => {
  try {
    const response = await axios.delete(`/ejercicios/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

/*
 * Funciones relacionadas con "Usuarios"
 */

const crearUsuario = async (usuarioDTO) => {
  try {
    const response = await axios.post(`/usuario`, usuarioDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerUsuarioPorUsername = async (username) => {
  try {
    const response = await axios.get(`/usuario/username/${username}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerUsuarios = async () => {
  try {
    const response = await axios.get(`/usuario`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarUsuario = async (usuarioDTO) => {
  try {
    const response = await axios.put('/usuario', usuarioDTO);  
    return response.data;
  } catch (error) {
    console.error('Error en actualizarUsuario:', error.response || error.message);
    handleError(error);
  }
};

const eliminarUsuario = async (id) => {
  try {
    await axios.delete(`/${id}`);
    return; 
  } catch (error) {
    handleError(error);
  }
};

const logearUsuario = (username, password) => {
  return axios.post('/usuario/login', { username, password });
}

export {
  logearUsuario,
  handleError,
  crearRutina,
  obtenerRutinas,
  obtenerRutinaPorId,
  actualizarRutina,
  eliminarRutina,
  crearEjercicio,
  obtenerEjercicios,
  obtenerEjercicioPorId,
  actualizarEjercicio,
  eliminarEjercicio,
  crearUsuario,
  obtenerUsuarioPorUsername,
  obtenerUsuarios,
  actualizarUsuario,
  eliminarUsuario
};
