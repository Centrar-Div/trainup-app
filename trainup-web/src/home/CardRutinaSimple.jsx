import { faPenToSquare, faStairs, faStar, faStarAndCrescent, faStarHalfAlt, faStarOfDavid, faStarOfLife, faTrash } from '@fortawesome/free-solid-svg-icons';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { agregarRutinaFavorita, eliminarRutina } from '../api/Api';
import { notification } from 'antd';
import Modal from 'antd/es/modal/Modal';
import { useLogin } from '../context/LoginContext';

const CardRutinaSimple = ({ rutina }) => {
  const { user, actualizarPerfilUsuarioTemp } = useLogin();
  const [isOpen, setIsOpen] = useState(false);
  const [isFavorite, setIsFavorite] = useState(user?.rutinasFavoritas?.some(rut => rut.id === rutina.id));
  const navigate = useNavigate();


  const actualizacionInstantaneaFavoritos = (rutina) => {
    const listaActualizada = user.rutinasFavoritas.find( rut => rut.id === rutina.id) ? user.rutinasFavoritas.filter( rut => rut.id !== rutina.id) : [...user.rutinasFavoritas, rutina];
    const usuarioActualizado = { ...user, rutinasFavoritas: listaActualizada };
    actualizarPerfilUsuarioTemp(usuarioActualizado);
  }

  const handlerClick = (rutina) => {
    navigate('/es/home/rutina', { state: { rutinaID: rutina.id, ejercicios: rutina.ejercicios, nombre: rutina.nombre } });
  };

  const handlerEdit = (rutinaEdit) => {
    const rutinaId = rutinaEdit.id;
    const rutinaNombre = rutinaEdit.nombre;
    const rutinaDescripcion = rutinaEdit.descripcion;
    const rutinaCategoria = rutinaEdit.categoria;
    const rutinaDificultad = rutina.dificultad;

    navigate('/es/home/rutina/editar', {
      state: {
        rutinaId,
        rutinaNombre,
        rutinaDescripcion,
        rutinaCategoria,
        rutinaDificultad
      },
    });
  };

  const handlerDelete = (rutina) => {
    setIsOpen(false);
    eliminarRutina(rutina.id)
      .then(({ data }) => {
        notification.success({
          message: 'Rutina eliminada',
          description: 'La rutina ha sido eliminada correctamente.',
          placement: 'topRight',
        });
      })
      .catch((error) => {
        notification.error({
          message: 'No se puede eliminar',
          description: 'La rutina no se puede eliminar porque ya ha sido eliminada.',
          placement: 'topRight',
        });
      });
  };

  const handlerFavorite = (rutina) => {
    setIsFavorite(!isFavorite);
    actualizacionInstantaneaFavoritos(rutina)

    agregarRutinaFavorita(user.id, rutina.id).then(() => {
      if (isFavorite) {
        notification.success({
          message: 'Rutina eliminada de favoritos',
          description: 'La rutina ha sido eliminada de favoritos.',
          placement: 'topRight',
        });
        return;
      } else {
        notification.success({
          message: 'Rutina agregada a favoritos',
          description: 'La rutina ha sido agregada a favoritos.',
          placement: 'topRight',
        });
      }
    })
  }

  // console.log(rutina.id);

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
        <p className={'category ' + `${rutina.dificultad.toLowerCase()}`}>{rutina.dificultad}</p>
        <p>{rutina.fechaDeCreacion}</p>
      </div>


      <div className='card-btns'>
          <button className='none-style-btn' onClick={() => handlerFavorite(rutina)}>
            <FontAwesomeIcon icon={faStar} className={`icon ${isFavorite ? `favorite` : ``}`}/>
          </button>
      {user?.esAdmin && (
        <>
          <button className='none-style-btn' onClick={() => handlerEdit(rutina)}>
            <FontAwesomeIcon icon={faPenToSquare} className="icon" />
          </button>
          <button className='none-style-btn' onClick={() => setIsOpen(true)}>
            <FontAwesomeIcon icon={faTrash} className="icon" />
          </button>
        </>
      )}
      </div>

      <Modal
        title="Confirmar acción"
        open={isOpen}
        onOk={() => handlerDelete(rutina)}
        onCancel={() => setIsOpen(false)}
        okText="Eliminar"
        cancelText="Cancelar"
      >
        <p>¿Estás seguro de que deseas eliminar la rutina?</p>
      </Modal>
    </div>
  );
};

export default CardRutinaSimple;
