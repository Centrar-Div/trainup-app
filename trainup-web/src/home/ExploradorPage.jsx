import React from 'react';
import { obtenerRutinas } from '../api/Api';
import CardRutinaSimple from './CardRutinaSimple';
import NotRutins from '../utils/NotRutins';
import DataLoader from '../utils/DataLoader';

const ExploradorPage = () => {
  return (
    <div className='temp-content-body'>
      <DataLoader fetchData={obtenerRutinas}>
        {(rutinas) =>
          rutinas.length > 0 ? (
            rutinas.map((rutina, index) => (
              <CardRutinaSimple key={index} rutina={rutina} />
            ))
          ) : (
            <NotRutins
              titulo="No existen rutinas todavia"
              mensaje="Nadie ha creado rutinas todavia, se el primero en crear una"
              showButton={false}
            />
          )
        }
      </DataLoader>
    </div>
  );
};

export default ExploradorPage;