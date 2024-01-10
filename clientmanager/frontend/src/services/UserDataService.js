import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/users");
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
}

export default new TutorialDataService();