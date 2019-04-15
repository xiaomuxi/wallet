<template>
  <div class="container">
    <div class="status-bar" :style="{height: statusBarHeight}"></div>
    <div class="header">
      <image class="header-back" :src="get_img_path('back_arrow_black.png')" @click="back"></image>
    </div>
    <scroller>
      <div class="content">
        <div class="content-header">
          <image class="wallet-img" :src="get_img_path('wallet.png')"></image>
          <text class="create-identity-text">创建身份</text>
        </div>
      </div>
      <am-list header="">
        <am-list-input v-model="name" @change="onTextChange" placeholder="身份名"></am-list-input>
        <am-list-input v-model="password" title="" @change="onTextChange" placeholder="密码" type="password"></am-list-input>
        <am-list-input v-model="confirmPwd" title="" @change="onTextChange" placeholder="重复输入密码" type="password"></am-list-input>
        <am-list-input v-model="tipTxt" title="" @change="onTextChange" placeholder="密码提示信息"></am-list-input>
      </am-list>
      <div style="flex: 1;"></div>
      <am-button class="btn-submit" text="创建" @click="submit"></am-button>
      <wxc-dialog :title="dialogTitle"
                  :content="dialogContent"
                  :show="isDialogShow"
                  single="true"
                  main-btn-color="#108CE6"
                  @wxcDialogConfirmBtnClicked="tipConfirm">
      </wxc-dialog>
      <wxc-part-loading width="60" height="60" :show="isLoadingShow"></wxc-part-loading>
    </scroller>
  </div>
</template>

<style>
  .container {
    opacity: 0.8;
    background-color: #ffffff;
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
  .content-header {
    justify-content: center;
    align-items: center;
    margin-top: 40;
    margin-bottom: 30;
  }
  .wallet-img {
    width: 60;
    height: 60;
  }
  .create-identity-text {
    font-size: 35;
    margin-top: 15;
  }
  .btn-submit {
    margin-bottom: 70;
    margin-left: 30;
    margin-right: 30;
  }
  .loading {
    position: absolute;
    padding-top: 20px;
    padding-bottom: 20px;
    align-items: center;
    height: 80px;
    justify-content: center;
  }
</style>

<script>
  import { AmButton, AmList, AmListInput } from 'weex-amui'
  import { WxcDialog, WxcPartLoading } from 'weex-ui';
  import Loading from '../../common/components/loading'

  const checkInput = function (name, passowrd, confirmPwd, tipText) {

  }

  export default {
    name: 'GatherMoney',
    components: { AmButton, AmList, AmListInput, WxcDialog, WxcPartLoading, Loading },
    data () {
      return {
        statusBarHeight: 0,
        submitBtnDisabled: true,
        dialogTitle: '',
        dialogContent: '',
        isDialogShow: false,
        isLoadingShow: false,
        name: '',
        password: '',
        confirmPwd: '',
        tipTxt: '',
      }
    },
    created () {
      weex.requireModule('UIManager').getStatusBarHeight((height) => {
        this.statusBarHeight = height;
      })
    },
    destroyed () {
      clearTimeout(this.timerId);
    },
    methods: {
      back: function() {
        this.$router.back();
      },
      onTextChange: function() {
        // console.log('-------')
        // console.log(this.submitBtnDisabled);
        // this.submitBtnDisabled = false;
        // this.submitBtnDisabled = !(this.name.trim() && this.password && this.confirmPwd && this.tipTxt.trim());
      },
      submit: function() {
        if (!this.name.trim()) {
          this.dialogTitle = "提示";
          this.dialogContent = "请输入身份名";
          this.isDialogShow = true;
          return;
        }
        if (!this.password) {
          this.dialogTitle = "提示";
          this.dialogContent = "请输入密码";
          this.isDialogShow = true;
          return;
        }
        if (!this.confirmPwd) {
          this.dialogTitle = "提示";
          this.dialogContent = "请输入确认密码";
          this.isDialogShow = true;
          return;
        }
        if (this.password !== this.confirmPwd) {
          this.dialogTitle = "提示";
          this.dialogContent = "两次输入的密码不一致，请检查";
          this.isDialogShow = true;
          return;
        }
        if (!this.tipTxt.trim()) {
          this.dialogTitle = "提示";
          this.dialogContent = "请输入提示信息";
          this.isDialogShow = true;
          return;
        }
        // this.isLoadingShow = true;
        this.timerId = setTimeout(() => {
          // this.isLoadingShow = false;
          this.reset('Main');
        }, 3000)

      },
      tipConfirm: function() {
        this.isDialogShow = false;
      }
    }
  }
</script>
