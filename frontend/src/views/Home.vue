<template>
  <div class="home">
    <h1 class="header-1">Hello, World!</h1>
    <img alt="Vue with Spring logo" src="../assets/spring-boot-vuejs-logo.png">
    <HelloSpringWorld hellomsg="Welcome to your Vue.js powered Spring Boot App"/>
  </div>
</template>

<script lang="js">
import { defineComponent } from 'vue';
import HelloSpringWorld from '@/components/HelloSpringWorld.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'Home',
  components: {
    HelloSpringWorld,
  },
});
</script>

<style>
@import '../../static/css/home_style.css';
</style>
