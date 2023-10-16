<template>
  <div>
    <div class="recommend-list">
      <ul>
        <li v-for="item in recommendList" :key="item.id">
          <h1 class="title"><a :href="'/article/details?id=' + item.id" v-text="item.title"></a></h1>
          <div>
            <a :href="item.link">
              <el-image class="image" :src="item.image" :alt="item.title"></el-image>
            </a>
          </div>
          <el-divider/>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    recommendList: Array
  }
}
</script>

<style scoped>
.recommend-list {
  border-radius: 5px;
  background: #fff;
  padding: 20px;
}
/*.recommend-list li{*/
/*  height: 250px;*/
/*}*/

.recommend-list .title {
  font-weight: normal;
  font-size: 16px;
  margin-bottom: 10px;
}

.recommend-list .title a {
  color: #0D0D0D;
}

.recommend-list .title a:hover {
  color: #666;
}

.recommend-list .image {
  width: 260px;
  height: 155px;
  border-radius: 5px;
}
</style>