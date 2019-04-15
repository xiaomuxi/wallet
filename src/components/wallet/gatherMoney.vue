
<template>
  <div class="container">
    <div class="status-bar" :style="{height: statusBarHeight}"></div>
    <div class="header">
      <image class="header-back" :src="get_img_path('back_arrow_white.png')" @click="back"></image>
      <div class="space">
        <text class="header-text">收款</text>
      </div>
      <image class="header-scan"></image>
    </div>
    <div class="content">
      <div :style="{'position': 'fixed', 'top': imgTop, left: 325}">
        <image class="wallet-img" :src="get_img_path('eth.png')"></image>
      </div>
      <div class="content-card">
        <div class="card-header">
          <text class="wallet-name">ETH-Wallet</text>
          <text class="wallet-address">0xeAeb822..232332sfe23</text>
        </div>
        <div class="card-bottom">
          <text class="money-address">收款地址</text>
          <qr-image text="测试" style="width: 400; height: 400;">
          </qr-image>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
  .container {
    opacity: 0.8;
    background-color: #333b4b;
  }
  .header {
    flex-direction: row;
    align-items: center;
    padding-left: 25;
    padding-right: 25;
    padding-bottom: 15;
  }
  .header-back {
    width: 45;
    height: 45;
  }
  .header-scan {
    width: 45;
    height: 45;
  }
  .space {
    flex: 1;
  }
  .header-text {
    text-align: center;
    color: #ffffff;
    font-size: 35;
  }
  .content {
    margin-top: 120;
    margin-left: 30;
    margin-right: 30;
  }
  .content-card {
    margin-left: 15;
    margin-right: 15;
    margin-bottom: 15;
    height: 700;
  }
  .card-header {
    padding-top: 70;
    justify-content: flex-end;
    align-items: center;
    padding-bottom: 20;
    border-top-left-radius: 25;
    border-top-right-radius: 25;
    background-color: #1488CE;
  }
  .card-bottom {
    flex: 1;
    background-color:#ffffff;
    align-items: center;
    justify-content: center;
    border-bottom-left-radius: 25;
    border-bottom-right-radius: 25;
  }
  .wallet-img {
    width: 100;
    height: 100;
    border-radius: 5;
    background-color: #ffffff;
  }
  .wallet-name {
    flex: 1;
    font-size: 35;
    color: #ffffff;
  }
  .wallet-address {
    font-size: 25;
    margin-top: 10;
    color: #ffffff;
  }
  .money-address {
    margin-top: 20;
    font-size: 35;
    color: #333333;
  }
  .qrcode-img {
    width: 300;
    height: 300;
  }
</style>

<script>
  export default {
    name: 'GatherMoney',
    data () {
      return {
        statusBarHeight: 0,
        imgTop: 130
      }
    },
    created () {
      weex.requireModule('UIManager').getStatusBarHeight((height) => {
        this.statusBarHeight = height;
        this.imgTop = height + 130;
      })
    },
    methods: {
      back: function() {
        this.$router.back();
      }
    }
  }
</script>
