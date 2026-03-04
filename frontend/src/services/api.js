import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => response.data,
  (error) => {
    const message = error.response?.data?.message || 'An error occurred';
    return Promise.reject(new Error(message));
  }
);

// Pet API
export const petApi = {
  getAll: () => api.get('/pets'),
  getById: (id) => api.get(`/pets/${id}`),
  getAdoptable: () => api.get('/pets/adoptable'),
  getBySpecies: (species) => api.get(`/pets/species/${species}`),
  search: (keyword) => api.get(`/pets/search?keyword=${keyword}`),
  create: (data) => api.post('/pets', data),
  update: (id, data) => api.put(`/pets/${id}`, data),
  delete: (id) => api.delete(`/pets/${id}`),
  markAsAdopted: (id) => api.put(`/pets/${id}/adopt`),
};

// Vaccination API
export const vaccinationApi = {
  getAll: () => api.get('/vaccinations'),
  getById: (id) => api.get(`/vaccinations/${id}`),
  getByPetId: (petId) => api.get(`/pets/${petId}/vaccinations`),
  getUpcoming: () => api.get('/vaccinations/upcoming'),
  create: (petId, data) => api.post(`/pets/${petId}/vaccinations`, data),
  update: (id, data) => api.put(`/vaccinations/${id}`, data),
  delete: (id) => api.delete(`/vaccinations/${id}`),
};

// Feeding Schedule API
export const feedingScheduleApi = {
  getAll: () => api.get('/feeding-schedules'),
  getById: (id) => api.get(`/feeding-schedules/${id}`),
  getByPetId: (petId) => api.get(`/pets/${petId}/feeding-schedules`),
  create: (petId, data) => api.post(`/pets/${petId}/feeding-schedules`, data),
  update: (id, data) => api.put(`/feeding-schedules/${id}`, data),
  delete: (id) => api.delete(`/feeding-schedules/${id}`),
};

// Vet Appointment API
export const vetAppointmentApi = {
  getAll: () => api.get('/appointments'),
  getById: (id) => api.get(`/appointments/${id}`),
  getByPetId: (petId) => api.get(`/pets/${petId}/appointments`),
  getUpcoming: () => api.get('/appointments/upcoming'),
  create: (petId, data) => api.post(`/pets/${petId}/appointments`, data),
  update: (id, data) => api.put(`/appointments/${id}`, data),
  updateStatus: (id, status) => api.put(`/appointments/${id}/status?status=${status}`),
  delete: (id) => api.delete(`/appointments/${id}`),
};

export default api;
