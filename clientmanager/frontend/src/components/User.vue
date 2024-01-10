<template>
    <div v-if="currentUser" class="edit-form">
      <h4>User</h4>
      <form>
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" id="name"
            v-model="currentUser.name"
          />
        </div>
        <div class="form-group">
          <label for="secretCodeName">Secret Code Number</label>
          <input type="text" class="form-control" id="secretCodeName"
            v-model="currentUser.secretCodeName"
          />
        </div>
  
        <div class="form-group">
          <label for="phoneNumber">Phone Number</label>
          <input type="text" class="form-control" id="phoneNumber"
            v-model="currentUser.phoneNumber"
          />
        </div>
      </form>
      <p>{{ message }}</p>
    </div>
  
    <div v-else>
      <br />
      <p>Please click on a User...</p>
    </div>
  </template>
  
  <script>
  import UserDataService from "../services/UserDataService";
  
  export default {
    name: "contact-user",
    data() {
      return {
        currentUser: null,
        message: ''
      };
    },
    methods: {
      getUser(id) {
        UserDataService.get(id)
          .then(response => {
            this.currentUser = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
    },
    mounted() {
      this.message = '';
      this.getUser(this.$route.params.id);
    }
  };
  </script>
  
  <style>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>