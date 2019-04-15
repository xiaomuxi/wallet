<template>
  <div>
    <div class="login-header">
      <div class="header-space"></div>
      <div class="header-container">
        <text class="header-text">创建你的</text>
        <text class="header-text">第一个数字身份</text>
      </div>
    </div>
    <div class="login-body">
      <div class="body-container">
        <div class="body-btn">
          <wxc-button class="btn-create-identity" :btnStyle="createBtnStyle" text="创建身份" @wxcButtonClicked="createIdentity"></wxc-button>
        </div>
        <div class="body-btn">
          <wxc-button class="btn-restore-identity" :textStyle="restoreBtnTextStyle" :btnStyle="restoreBtnStyle" text="恢复身份" @wxcButtonClicked="restoreIdentity"></wxc-button>
        </div>
        <div class="body-import">
          <text class="body-import-des">从其他平台导入和使用您的钱包,  请先阅读指南</text>
          <text @click="jumpImportGuide" class="body-import-guide">如何导入钱包？</text>
        </div>
      </div>
      <div class="body-bottom"></div>
    </div>
  </div>
</template>

<style>
  .login-header {
    flex: 1;
    flex-direction: column;
    background-color: #1488CE;
  }
  .header-space {
    flex: 1;
  }
  .header-container {
    flex: 2;
    justify-content: center;
    align-items: center;
  }
  .header-text {
    margin-top: 15;
    font-size: 55;
    color: white;
    text-align: center;
  }
  .login-body {
    flex: 2.5;
    flex-direction: column;
    background-color: #ffffff;
  }
  .body-container {
    flex: 1;
    align-items: center;
    justify-content: center;
  }
  .body-btn {
    flex-direction: row;
    margin-left: 150;
    margin-right: 150;
  }
  .btn-create-identity {
    background-color: #108CE6;
  }
  .btn-restore-identity {
    margin-top: 50;
  }
  .body-bottom {
    flex: 1;
  }
  .body-import {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .body-import-des {
    margin-top: 80;
    color: #9C9BA1;
    font-size: 25;
  }
  .body-import-guide {
    margin-top: 15;
    color: #108CE6;
    font-size: 25;
  }
</style>


<script>
  import { WxcButton } from 'weex-ui'
  export default {
    components: { WxcButton },
    data() {
      return {
        createBtnStyle: {
          "margin-left": 350,
          "margin-right": 350,
          "borderWidth": 1,
          "borderColor": "#108CE6",
          "border-radius": 45,
          "backgroundColor": "#108CE6"
        },
        restoreBtnStyle: {
          "borderWidth": 1,
          "borderColor": "#F2F3F5",
          "border-radius": 45,
          "backgroundColor": "#F2F3F5"
        },
        restoreBtnTextStyle: {
          color: "#108CE6"
        }
      }
    },
    created() {
      weex.requireModule('globalEvent').addEventListener('androidback', (event) => {
        if (this.$route.path == '/Login' || this.$route.path == '/Main') {
          weex.requireModule('UIManager').closeApp();
        } else {
          this.$router.back()
        }
      })
    },
    methods: {
      createIdentity: function () {
        this.jumpInter('CreateIdentity')
      },
      restoreIdentity: function () {
        this.jumpInter('RecoverIdentity')
      },
      jumpImportGuide: function () {
        console.log('jumpImportGuide------>')
      }
    }
  }
</script>
