<template>
  <div id="app">
    <!-- 當前視圖是默認加載到index.html的 -->
    <!-- 當前視圖不負責內容的顯示，將由其它視圖來顯示 -->
    <router-view/>
  </div>
</template>

<style>
* {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#app {
  color: #2c3e50;
}
</style>
