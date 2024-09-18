import React, { useState } from 'react';
import '../styles/rutinas.css';
import { notification, Modal } from 'antd';
import { useNavigate } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';
import { completarRutina } from '../api/Api';
import NotRutins from '../utils/NotRutins';

const ListRutinas = ({ rutinas, esCompletada }) => {
  const navigate = useNavigate();
  const { user } = useLogin();
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [rutinaIdToComplete, setRutinaIdToComplete] = useState(null);

  const handlerClick = (rutina) => {
    navigate('/es/home/rutina', { state: { ejercicios: rutina.ejercicios, nombre: rutina.nombre } });
  };

  const showModal = (rutinaId) => {
    setRutinaIdToComplete(rutinaId);
    setIsModalVisible(true);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
    setRutinaIdToComplete(null);
  };

  const marcarComoCompletada = async () => {
    try {
      await completarRutina(user.id, rutinaIdToComplete);
      notification.success({
        message: '¡Éxito!',
        description: 'Se ha completado la rutina correctamente.',
        placement: 'topRight',
      });
      setIsModalVisible(false); 
      window.location.reload();
    } catch (error) {
      notification.error({
        message: 'Error',
        description: 'Hubo un problema al completar la rutina. Inténtalo de nuevo.',
        placement: 'topRight',
      });
    }
  };

  return (
    <div className="container-boxinfo">
      {
        rutinas && rutinas.length > 0 ? (
          rutinas.map(rutina => (
            <div
              key={rutina.id}
              onClick={() => handlerClick(rutina)}
              className="boxinfo"
            >
              <div className="card-header">
                <h2>{rutina.nombre}</h2>
              </div>

              <div className="card-body">
                <p>{rutina.descripcion}</p>
              </div>

              <div className="card-footer">
                <p className="category">{rutina.categoria}</p>
                <p className={`status ${esCompletada ? 'completed' : 'incomplete'}`}>
                  {esCompletada ? 'Completada' : 'No Completada'}
                </p>
                <p>{rutina.fechaDeCreacion}</p>
              </div>

              {!esCompletada && (
                <button
                  className={`complete-btn ${rutina.completada ? 'completed' : ''}`}
                  onClick={(e) => {
                    e.stopPropagation();
                    showModal(rutina.id);
                  }}
                >
                  {rutina.completada ? '✓' : 'O'}
                </button>
              )}
            </div>
          ))
        ) : (
          <NotRutins />
        )
      }
      <Modal
        title="Confirmar acción"
        open={isModalVisible}
        onOk={marcarComoCompletada}
        onCancel={handleCancel}
        okText="Sí, completar"
        cancelText="Cancelar"
      >
        <p>¿Estás seguro de que quieres marcar esta rutina como completada?</p>
      </Modal>
    </div>
  );
};

export default ListRutinas;
