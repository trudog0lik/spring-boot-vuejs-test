<template>
  <div class="user">
    <h1>Create User</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="user.username" placeholder="username">
    <input type="text" v-model="user.password" placeholder="password">

    <button @click="createNewUser()">Create User</button>

    <div v-if="showResponse"><h6>User created with Id: {{ user.id }}</h6></div>

    <button v-if="showResponse" @click="retrieveUser()">Retrieve user {{user.id}} data from database</button>

    <h4 v-if="showRetrievedUser">Retrieved User {{retrievedUser.firstName}} {{retrievedUser.lastName}}</h4>
  </div>
</template>

<script lang="js">
import { defineComponent } from 'vue';
import BackendService from '../api/backend-api';

class State {
  user;
  retrievedUser;
  showResponse;
  showRetrievedUser;
  errors
}

export default defineComponent({
  name: 'User',

  data() {
    return {
      errors: [],
      user: {
        id: 0,
        firstName: '',
        lastName: ''
      },
      showResponse: false,
      retrievedUser: {
        id: 0,
        firstName: '',
        lastName: ''
      },
      showRetrievedUser: false
    }
  },
  methods: {
    // Fetches posts when the view is created.
    createNewUser () {
      BackendService.createUser(this.user.username, this.user.password).then(response => {
          // JSON responses are automatically parsed.
          this.user.id = response.data;
          console.log('Created new User with Id ' + response.data);
          this.showResponse = true
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    retrieveUser () {
      BackendService.getUser(this.user.id).then(response => {
          // JSON responses are automatically parsed.
          this.retrievedUser = response.data;
          this.showRetrievedUser = true
        })
        .catch((error) => {
          this.errors.push(error)
        })
    }
  }
});
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
