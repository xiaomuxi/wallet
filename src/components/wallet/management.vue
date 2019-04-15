<template>
  <div class="container">
    <div class="status-bar" :style="{height: statusBarHeight}"></div>
    <div class="header">
      <image class="header-back" :src="get_img_path('back_arrow_black.png')" @click="back"></image>
      <div class="space">
        <text class="header-text">管理</text>
      </div>
      <image class="header-scan"></image>
    </div>
    <div class="divider"></div>
    <div class="content">
      <div class="content-header">
        <image class="wallet-img" :src="get_img_path('eth.png')"></image>
        <div style="margin-left: 20; flex: 1;">
          <text class="wallet-name">ETH-Wallet</text>
          <text class="wallet-address">0xeAeb822..232332sfe23</text>
        </div>
        <image style="width: 35; height: 35;" :src="get_img_path('arrow_right_gray.png')"></image>
      </div>
      <am-list>
        <am-list-item style="padding-top: 15;" align="middle" :thumb="get_img_path('eth.png')" title="密码提示信息" @click="click"></am-list-item>
        <am-list-item style="padding-top: 15;" align="middle" :thumb="get_img_path('eth.png')" title="免密支付" @click="click"></am-list-item>
        <am-list-item style="padding-top: 15;" align="middle" :thumb="get_img_path('eth.png')" title="导出助记词" @click="click"></am-list-item>
        <am-list-item style="padding-top: 15;" align="middle" :thumb="get_img_path('eth.png')" title="导出Keystore" @click="click"></am-list-item>
        <am-list-item style="padding-top: 15;" align="middle" :thumb="get_img_path('eth.png')" title="导出私钥" @click="click"></am-list-item>
      </am-list>
    </div>
  </div>
</template>

<style scoped>
  .container {
    background-color: #F2F3F4;
  }
  .status-bar {
    background-color: #ffffff;
  }
  .header {
    flex-direction: row;
    align-items: center;
    padding-left: 25;
    padding-right: 25;
    padding-bottom: 15;
    background-color: #ffffff;
  }
  .header-back {
    width: 45;
    height: 45;
  }
  .header-scan {
    width: 50;
    height: 50;
  }
  .space {
    flex: 1;
  }
  .header-text {
    text-align: center;
    font-size: 35
  }
  .divider {
    height: 1;
    background-color: #999999;
  }
  .content {
    flex: 1;
  }
  .content-header {
    flex-direction: row;
    background-color: #ffffff;
    margin-top: 30;
    margin-bottom: 30;
    padding:30;
    align-items: center;
  }
  .wallet-img {
    width: 100;
    height: 100;
    border-width: 1;
    border-color: #CCCCCC;
    border-radius: 5;
    background-color: #ffffff;
  }
  .wallet-name {
    font-size: 35;
    color: #333333;
  }
  .wallet-address {
    font-size: 25;
    margin-top: 10;
    color: #333333;
  }
</style>

<script>
  import { AmList, AmListItem, AmListInput, AmButton } from 'weex-amui'
  import { WxcDialog } from 'weex-ui';

  export default {
    name: 'Management',
    components: { AmList, AmListItem, AmListInput, WxcDialog, AmButton },
    data () {
      return {
        statusBarHeight: 0,
      }
    },
    created () {
      weex.requireModule('UIManager').getStatusBarHeight((height) => {
        this.statusBarHeight = height;
      })
    },
    methods: {
      back: function() {
        this.$router.back();
      },
    }
  }
</script>
