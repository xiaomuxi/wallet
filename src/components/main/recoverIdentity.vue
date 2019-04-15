<template>
  <div class="container">
    <div class="status-bar" :style="{height: statusBarHeight}"></div>
    <div class="header">
      <image class="header-back" :src="get_img_path('back_arrow_black.png')" @click="back"></image>
      <div class="space">
        <text class="header-text">恢复身份</text>
      </div>
      <image class="header-scan" :src="get_img_path('scan.png')" @click="goToScan"></image>
    </div>
    <div class="divider"></div>
    <div class="content">
      <div style="background-color: #F9F9F9; padding-top:40; padding-bottom: 40;">
        <text style="font-size: 30; color: #666666; margin-left: 40;">使用助记词导入的同时可以修改钱包密码。</text>
      </div>
      <am-list-textarea class="word-input" rows="4" placeholder="输入助记词，用空格分隔"></am-list-textarea>
      <div style="flex-direction: raw; margin-left: 40; align-items: center;">
        <text style="font-size: 40;">设置密码</text>
        <image style="width: 40; height: 40; margin-left: 10;" @click="showTip" :src="get_img_path('tip.png')"></image>
      </div>
      <div style="margin-left: 10;">
        <am-list-input v-model="inputValue" placeholder="钱包密码" type="password"></am-list-input>
        <am-list-input v-model="inputValue" placeholder="重复输入密码" type="password"></am-list-input>
        <am-list-input v-model="inputValue" placeholder="密码提示信息" ></am-list-input>
      </div>
      <am-button class="btn-submit" text="恢复身份" :disabled="submitBtnDisabled" @click="click"></am-button>
    </div>
    <wxc-dialog title="设置密码说明"
                content="如果要在导入的同时修改钱包密码，请在输入框内重复输入新密码，就密码将在导入钱包后失效。"
                :show="show"
                single="true"
                main-btn-color="#108CE6"
                @wxcDialogConfirmBtnClicked="tipConfirm">
    </wxc-dialog>
  </div>
</template>

<style scoped>
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
    background-color: #ffffff;
  }
  .word-input {
    margin: 40;
    border-width: 1;
    border-color: #999999;
    border-radius: 10;
  }
  .btn-submit {
    margin-top: 70;
    margin-left: 30;
    margin-right: 30;
  }
</style>

<script>
  import { AmList, AmListTextarea, AmListInput, AmButton } from 'weex-amui'
  import { WxcDialog } from 'weex-ui';

  export default {
    name: 'RecoverIdentity',
    components: { AmList, AmListTextarea, AmListInput, WxcDialog, AmButton },
    data () {
      return {
        statusBarHeight: 0,
        show: false,
        submitBtnDisabled: true
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
      goToScan: function () {
        weex.requireModule('UIManager').scan();
      },
      showTip: function() {
        this.show = true;
      },
      tipConfirm: function() {
        this.show = false;
      }
    }
  }
</script>
