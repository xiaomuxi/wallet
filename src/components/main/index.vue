<template>
  <div class="container">
    <wxc-tab-bar :tab-titles="tabTitles"
      :tab-styles="tabStyles"
      title-type="icon"
      @wxcTabBarCurrentTabSelected="wxcTabBarCurrentTabSelected">
      <wallet-component class="item-container" :style="contentStyle"/>
      <market-component class="item-container" :style="contentStyle"/>
      <glance-component class="item-container" :style="contentStyle"/>
      <mine-component class="item-container" :style="contentStyle"/>
    </wxc-tab-bar>
  </div>
</template>

<style scoped>
  .container {
    background-color: #fff;
  }
  .item-container {
    width: 750px;
    background-color: #f2f3f4;
  }
</style>

<script>
  import { WxcTabBar, Utils } from 'weex-ui'
  import GlanceComponent from '../glance'
  import MarketComponent from '../market'
  import MineComponent from '../mine'
  import WalletComponent from '../wallet'
  import Config from '../../config'

  export default {
    components: { WxcTabBar, GlanceComponent, MarketComponent, MineComponent, WalletComponent},
    name: 'Main',
    data () {
      return {
        tabStyles: Config.tabStyles,
        statusBarHeight: 0,
        tabTitles: [
          {
            title: '钱包',
            icon: 'local:///images/tab_money_n.png',
            activeIcon: 'local:///images/tab_money_o.png'
          },
          {
            title: '市场',
            icon: 'local:///images/tab_market_n.png',
            activeIcon: 'local:///images/tab_market_o.png'
          },
          {
            title: '浏览',
            icon: 'local:///images/tab_browse_n.png',
            activeIcon: 'local:///images/tab_browse_o.png'
          },
          {
            title: '我的',
            icon: 'local:///images/tab_me_n.png',
            activeIcon: 'local:///images/tab_me_o.png'
          }
        ]
      }
    },
    created () {
      const tabPageHeight = Utils.env.getPageHeight();
      // 如果页面没有导航栏，可以用下面这个计算高度的方法
      // const tabPageHeight = env.deviceHeight / env.deviceWidth * 750;
      const { tabStyles } = this;
      this.contentStyle = { height: (tabPageHeight + tabStyles.height) + 'px' };
      weex.requireModule('UIManager').getStatusBarHeight((height) => {
        this.statusBarHeight = height;
      })
      
      weex.requireModule('globalEvent').addEventListener('androidback', (event) => {
        if (this.$route.path == '/Login' || this.$route.path == '/Main') {
          weex.requireModule('UIManager').closeApp();
        } else {
          this.$router.back()
        }
      })

      this.tabTitles[0].icon = this.get_img_path('tab_money_n.png')
      this.tabTitles[0].activeIcon = this.get_img_path('tab_money_o.png')
      this.tabTitles[1].icon = this.get_img_path('tab_market_n.png')
      this.tabTitles[1].activeIcon = this.get_img_path('tab_market_o.png')
      this.tabTitles[2].icon = this.get_img_path('tab_browse_n.png')
      this.tabTitles[2].activeIcon = this.get_img_path('tab_browse_o.png')
      this.tabTitles[3].icon = this.get_img_path('tab_me_n.png')
      this.tabTitles[3].activeIcon = this.get_img_path('tab_me_o.png')
    },
    methods: {
      wxcTabBarCurrentTabSelected (e) {
        const index = e.page;
        console.log(index);
      }
    }
  }
</script>
