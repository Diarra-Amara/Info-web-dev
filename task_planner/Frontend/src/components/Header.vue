<script setup lang="ts"></script>

<template>
  <header class="px-20 d-flex flex-row align-items-center">
    <a href="#" class="d-flex flex-row align-items-center mr-20">
      <img src="/src/assets/logo.svg" alt="logo" />
      <span class="logo">Task Planner</span>
    </a>

    <ul class="d-flex flex-row flex-fill">
      <li class="mr-10">
        <a href="#">Plan page</a>
      </li>
      <li>
        <a href="#">Settings</a>
      </li>
    </ul>
  </header>
</template>

<style scoped lang="scss">
header {
  background-color: var(--primary-1);
  a {
    color: var(--text-primary-color);
    img {
      width: 20px;
      margin-right: 5px;
    }
    .logo {
      font-weight: 700;
      font-size: 20px;
    }
  }
}
</style>
