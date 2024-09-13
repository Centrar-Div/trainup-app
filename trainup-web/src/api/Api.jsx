import axios from 'axios';
import { notification } from 'antd';
import 'antd/dist/reset.css';

// URL base de la API 
const BASE_URL = 'http://localhost:8080/api';

// Manejador de errores
const handleError = (error) => {
  const errorMessage = error.response?.data?.msg || 'Ocurrió un error inesperado';
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
    const response = await axios.post(`${BASE_URL}/rutinas`, rutinaDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerRutinas = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/rutinas`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerRutinaPorId = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/rutinas/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarRutina = async (rutinaID, rutina) => {
  try {
    const response = await axios.put(`${BASE_URL}/rutinas/${rutinaID}`, rutina);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const eliminarRutina = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/rutinas/${id}`);
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
    const response = await axios.post(`${BASE_URL}/ejercicios`, ejercicioDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerEjercicios = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/ejercicios`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerEjercicioPorId = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/ejercicios/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarEjercicio = async (ejercicioID, ejercicio) => {
  try {
    const response = await axios.put(`${BASE_URL}/ejercicios/${ejercicioID}`, ejercicio);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const eliminarEjercicio = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/ejercicios/${id}`);
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
    const response = await axios.post(`${BASE_URL}/usuario`, usuarioDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerUsuarioPorUsername = async (username) => {
  try {
    const response = await axios.get(`${BASE_URL}/usuario/username/${username}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const obtenerUsuarios = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/usuario`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarUsuario = async (usuarioDTO) => {
  try {
    const response = await axios.put(`${BASE_URL}/usuario/${usuarioDTO.id}`, usuarioDTO);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const eliminarUsuario = async (id) => {
  try {
    await axios.delete(`${BASE_URL}/${id}`);
    return; 
  } catch (error) {
    handleError(error);
  }
};


export {
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
