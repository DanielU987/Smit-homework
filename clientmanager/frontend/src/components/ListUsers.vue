<template>
    <div class="list row">
      <div class="col-md-8">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="Search by name"
            v-model="name"/>
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button"
              @click="searchUser"
            >
              Search
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <h4>Users List</h4>
        <ul class="list-group">
          <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(user, index) in users"
            :key="index"
            @click="setActiveUser(user, index)"
          >
            {{ user.name }}
          </li>
        </ul>
      </div>
      <div class="col-md-6">
        <div v-if="currentUser">
          <h4>User</h4>
          <div>
            <label><strong>User:</strong></label> {{ currentUser.name }}
          </div>
          <div>
            <label><strong>Secret Code Name:</strong></label> {{ currentUser.secretCodeName }}
          </div>
          <div>
            <label><strong>Phone Number:</strong></label> {{ currentUser.phoneNumber}}
          </div>
  
          <a class="badge badge-warning"
            :href="'/users/' + currentUser.id"
          >
            Edit
          </a>
        </div>
        <div v-else>
          <br />
          <p>Please click on a User...</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import UserDataService from "../services/UserDataService";
  
  export default {
    name: "users-list",
    data() {
      return {
        users: [],
        currentUser: null,
        currentIndex: -1,
        name: ""
      };
    },
    methods: {
      retrieveUsers() {
        UserDataService.getAll()
          .then(response => {
            this.users = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      refreshList() {
        this.retrieveUsers();
        this.currentUser = null;
        this.currentIndex = -1;
      },
  
      setActiveUser(user, index) {
        this.currentUser = user;
        this.currentIndex = index;
      },
      
      searchUser() {
        UserDataService.findByUser(this.name)
          .then(response => {
            this.users = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    mounted() {
      this.retrieveUsers();
    }
  };
  </script>
  
  <style>
  .list {
    text-align: left;
    max-width: 750px;
    margin: auto;
  }
  </style>