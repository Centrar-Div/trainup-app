import React, { useState, useEffect } from 'react';
import Loader from './Loader'; 

const DataLoader = ({ children, fetchData }) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadData = async () => {
      try {
        const result = await fetchData();
        setData(result);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    loadData();
  }, [fetchData]);

  if (loading) return <Loader />;
  if (error) return <div>Error: {error.message}</div>;
  return children(data);
};

export default DataLoader;
