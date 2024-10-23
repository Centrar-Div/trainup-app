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

const crearRutina = (body) => axios.post(`/rutinas`, body)
const obtenerRutinas = () => axios.get(`/rutinas`)
const actualizarRutina = (body) => axios.put(`/rutinas`, body)
const eliminarRutina = (id) => axios.delete(`/rutinas/${id}`)
const obtenerRutinaPorId = (id) => axios.get(`/rutinas/${id}`);
const obtenerCategorias = () => axios.get(`rutinas/categorias`)
const obtenerRutinasPorCategoria = (categoria) => axios.get(`rutinas/categoria/${categoria}`)
const buscarRutina = (nombre, dificultad) => axios.get(`/rutinas/buscar`, { params: { nombre: nombre, dificultad: dificultad || undefined } })
const agregarRutinaFavorita = (usuarioID, rutinaID) => axios.put(`/usuario/${usuarioID}/favorita/${rutinaID}`)


/* 
* Funciones relacionadas con "Ejercicios"
*/

const crearEjercicio = (body) => axios.post(`/ejercicios`, body);
const agregarEjercicioARutina = (rutinaID, ejercicio) => axios.post(`/rutinas/${rutinaID}/ejercicios`, ejercicio);
const completarONoEjercicio = (usuarioID, rutinaID, ejercicioID) => axios.put(`/usuario/${usuarioID}/completarONoEjercicio/${rutinaID}/${ejercicioID}`);

const eliminarEjercicioDeRutina = (rutinaID, ejercicioID) => axios.delete(`/rutinas/${rutinaID}/ejercicios/${ejercicioID}`);



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

const actualizarEjercicio = (ejercicio) => axios.put(`/ejercicios/actualizar`, ejercicio)
const actualizarEjercicioEnRutina = (id, ejercicio) => axios.put(`/rutinas/${id}/ejercicio/actualizar`, ejercicio)

const eliminarEjercicio = (ejercicioID) => {
  return axios.delete(`/ejercicios/${ejercicioID}`);
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

const obtenerUsuarioPorID = (id) => axios.get(`usuario/id/${id}`)

const obtenerUsuarios = async () => {
  try {
    const response = await axios.get(`/usuario`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

const actualizarUsuario = (usuarioDTO) => axios.put(`/usuario`, usuarioDTO);

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

const completarRutina = (userId, rutinaId) => {
  axios.post(`/usuario/completarRutina/${userId}/${rutinaId}`).then((response) => response.data).catch(handleError);
};

export const isFollowing = (rutinaID) => {
  return axios.get(`/usuario/isFollowing/${localStorage.getItem('id')}/${rutinaID}`);
}

export const seguirRutina = (rutinaID) => {
  return axios.put(`/usuario/follow/${localStorage.getItem('id')}/${rutinaID}`);
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
  eliminarUsuario,
  completarRutina,
  obtenerUsuarioPorID,
  agregarEjercicioARutina,
  eliminarEjercicioDeRutina,
  actualizarEjercicioEnRutina,
  completarONoEjercicio,
  obtenerCategorias,
  obtenerRutinasPorCategoria,
  buscarRutina,
  agregarRutinaFavorita
};
