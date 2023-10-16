<template>
  <div>
    <div class="float-link-list">
      <ul class="nav">
        <li class="nav-item" v-for="item in linkList" :key="item.id">
          <a :href="item.link" v-text="item.text"></a>
        </li>
      </ul>
      <div class="back-top">
        <a href="javascript:;" @click="backTop">回頂部</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      linkList: []
    }
  },
  methods: {
    loadLinkList() {
      let linkList = [
        {
          id: 1,
          text: '跑步',
          link: '/run',
          sort: 1
        },
        {
          id: 2,
          text: '足球',
          link: '/soccer',
          sort: 2
        },
        {
          id: 3,
          text: '籃球',
          link: '/basketball',
          sort: 3
        },
        {
          id: 4,
          text: '健身與訓練',
          link: '/workout',
          sort: 4
        },
        {
          id: 5,
          text: '瑜珈',
          link: '/yoga',
          sort: 5
        },
        {
          id: 6,
          text: '滑板',
          link: '/skateboard',
          sort: 6
        },
        {
          id: 7,
          text: '網球',
          link: '/tennis',
          sort: 7
        },
        {
          id: 8,
          text: '商城',
          link: '/mall',
          sort: 8
        }
      ];
      this.linkList = linkList;
    },
    backTop() {
      window.scrollTo(0, 0);
    }
  },
  mounted() {
    this.loadLinkList();
  }
}
</script>

<style scoped>
.float-link-list {
  width: 100px;
}

.float-link-list a {
  display: block;
  line-height: 35px;
  text-align: center;
  font-size: 14px;
  color: #333;
}

.float-link-list a:hover {
  background: #0D0D0D;
  color: #fff;
}

.nav {
  background: #fff;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

.nav-item {
}

.back-top {
  margin-top: 5px;
  background: #fff;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}

.back-top a:hover {
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>