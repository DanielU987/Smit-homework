import http from "../http-common";

class UserDataService {
  getAll() {
    return http.get("/users");
  }

  getAllSorted(sortOrder) {
    return http.get(`/users?sort=${sortOrder}`);
  }

  get(id) {
    return http.get(`/users/${id}`);
  }

  create(data) {
    return http.post("/users", data);
  }

  findByName(name) {
    return http.get(`/users?name=${name}`);
  }

  update(id, data) {
    return http.put(`/users/${id}`, data);
  }

  delete(id) {
    return http.delete(`/users/${id}`);
  }
}

export default new UserDataService();