import { faPenToSquare, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react'
import { useNavigate } from 'react-router-dom';
import { eliminarRutina } from '../api/Api';
import { notification } from 'antd';

const CardRutinaSimple = ({rutina}) => {

  const navigate = useNavigate()

  const handlerClick = (rutina) => {
    navigate('/es/home/rutina', { state: { ejercicios: rutina.ejercicios, nombre: rutina.nombre } });
  };

  const handlerEdit = (rutinaEdit) => {
    const rutinaId = rutinaEdit.id;
    const rutinaNombre = rutinaEdit.nombre;
    const rutinaDescripcion = rutinaEdit.descripcion;
    const rutinaCategoria = rutinaEdit.categoria;
    
    navigate('/es/home/rutina/editar', { 
      state: { 
        rutinaId, 
        rutinaNombre,
        rutinaDescripcion,
        rutinaCategoria
       } 
    })
  }

  const handlerDelete = (rutina) => {
    eliminarRutina(rutina.id).then(({data}) => {
      notification.success({
        message: 'Rutina eliminada',
        description: 'La rutina ha sido eliminada correctamente.',
        placement: 'topRight',
      });
    }).catch((error) => {
      notification.error({
        message: 'No se puede eliminar',
        description: 'La rutina no se puede eliminar porque ya ha sido eliminada.',
        placement: 'topRight',
      });
    })
  }

  return (
    <div key={rutina.id} className="boxinfo">
      <div className="height-100" onClick={() => handlerClick(rutina)}>
        <div className="card-header">
            <h2>{rutina.nombre}</h2>
        </div>

        <div className="card-body">
            <p>{rutina.descripcion}</p>
        </div>
      </div>
      <div className="card-footer">
          <p className="category">{rutina.categoria}</p>
          <p>{rutina.fechaDeCreacion}</p>
      </div>
      <div className='card-btns'>
          <button className='none-style-btn' onClick={() => handlerEdit(rutina)}>
            <FontAwesomeIcon icon={faPenToSquare} className="icon" />
          </button>
          <button className='none-style-btn' onClick={() => handlerDelete(rutina)}>
            <FontAwesomeIcon icon={faTrash} className="icon" />
          </button>
      </div>  
    </div>
  )
}

export default CardRutinaSimple