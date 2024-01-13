<template>
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="name">Name</label>
          <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="user.name"
            name="name"
          />
        </div>
  
        <div class="form-group">
          <label for="secretCodeName">Secret Code Name</label>
          <input
            class="form-control"
            id="secretCodeName"
            required
            v-model="user.secretCodeName"
            name="secretCodeName"
          />
        </div>

        <div class="form-group">
          <label for="phoneNumber">Phone Number</label>
          <input
            class="form-control"
            id="phoneNumber"
            required
            v-model="user.phoneNumber"
            name="phoneNumber"
          />
        </div>
  
        <button @click="saveUser" class="btn btn-success">Submit</button>
      </div>
  
      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" @click="newUser">Add</button>
        <router-link to="/" class="btn btn-primary">Home</router-link>
      </div>
    </div>
  </template>
  
  <script>
  import UserDataService from "../services/UserDataService";
  
  export default {
    name: "add-user",
    data() {
      return {
        user: {
          id: null,
          name: "",
          secretCodeName: "",
          phoneNumber: ""
        },
        submitted: false
      };
    },
    methods: {
      saveUser() {
        var data = {
          name: this.user.name,
          secretCodeName: this.user.secretCodeName,
          phoneNumber: this.user.phoneNumber
        };
  
        UserDataService.create(data)
          .then(response => {
            this.user.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
      },
      
      newUser() {
        this.submitted = false;
        this.user = {};
      }
    }
  };
  </script>
  
  <style>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>