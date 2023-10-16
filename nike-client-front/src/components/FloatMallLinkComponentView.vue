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
          text: '推薦',
          link: '/mall#recommend',
          sort: 1
        },
        {
          id: 2,
          text: 'Jordan',
          link: '/mall/search?keywords=Jordan',
          sort: 2
        },
        {
          id: 3,
          text: '跑步',
          link: '/mall/search?keywords=跑步',
          sort: 3
        },
        {
          id: 4,
          text: '籃球',
          link: '/mall/search?keywords=籃球',
          sort: 4
        },
        {
          id: 5,
          text: '健身',
          link: '/mall/search?keywords=健身',
          sort: 5
        },
        {
          id: 6,
          text: '涼鞋',
          link: '/mall/search?keywords=涼鞋',
          sort: 6
        },
        {
          id: 7,
          text: '男鞋',
          link: '/mall/search?keywords=男鞋',
          sort: 7
        },
        {
          id: 8,
          text: '女鞋',
          link: '/mall/search?keywords=女鞋',
          sort: 8
        },
        {
          id: 9,
          text: '童鞋',
          link: '/mall/search?keywords=童鞋',
          sort: 9
        },
        {
          id: 10,
          text: '底筒',
          link: '/mall/search?keywords=底筒',
          sort: 10
        },
        {
          id: 11,
          text: '中筒',
          link: '/mall/search?keywords=中筒',
          sort: 11
        },
        {
          id: 12,
          text: '高筒',
          link: '/mall/search?keywords=高筒',
          sort: 12
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
  text-align: center;
  font-size: 12px;
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
  display: flex;
  flex-flow: wrap;
  padding: 8px 4px;
}

.nav-item {
}

.nav-item a {
  width: 40px;
  line-height: 25px;
  margin: 3px;
  background: #f5f5f5;
}

.back-top {
  margin-top: 5px;
  background: #fff;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}

.back-top a {
  width: 100px;
  line-height: 35px;
}

.back-top a:hover {
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>