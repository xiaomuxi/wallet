<!-- Created by shayneChen on 2018/08/09. -->
<!--全局加载提示组件. -->
<template>
  <div :class="[showLoading && needMask && 'loading-need-mask']"
       @click="maskClicked"
       :style="maskStyle">
    <div class="wxc-loading" :style="[centerPosition]" v-if="showLoading">
      <div :class="['loading-box',mBackgroundcolor?'':'transparent']" :style="[conStyle]" :aria-hidden="true">
        <image :src="loading.url"
               :style="{width:mImgwidth,height:mImgheight}"
               resize="contain"
               quality="original"></image>
        <text v-if="loadingText" :style="{color:mTextcolor}" class="loading-text">{{loadingText}}</text>
      </div>
    </div>
  </div>
</template>

<style scoped>
  .loading-need-mask {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.3);
  }
  .wxc-loading {
    position: fixed;
    z-index: 9999;
  }
  .loading-box {
    align-items: center;
    justify-content: center;
    border-radius: 20px;
  }
  .transparent {
    background-color: rgba(0, 0, 0, .2); 
  }
  .loading-trip-image {
    height: 166px;
    width: 199px;
  }
  .loading-text {
    font-size: 30px;
    line-height: 46px;
    height: 46px;
    margin-top: 8px;
    text-overflow: ellipsis;
    padding-left: 12px;
    padding-right:12px;    
    margin-bottom: 8px;
    text-align: center;
  }
</style>

<script>

  export default {
    props: {
      show: {
        type: Boolean,
        default: false
      },
      loadingText: {
        type: String,
        default: ''
      },
      type: {
        type: String,
        default: 'default'
      },
      interval: {
        type: [Number, String],
        default: 0
      },
      needMask: {
        type: Boolean,
        default: false
      },
      maskStyle: {
        type: Object,
        default: () => ({})
      },
      mHeight: {
        type: String,
        default: '175'
      },
      mWidth: {
        type: String,
        default: '175'
      },
      mBackgroundcolor: {
        type:String,
        default: ''
      },
      mUrl: {
        type:String
      },
      mTextcolor: {
        type:String,
        default:'#ffffff'
      },
      mImgwidth: {
        type:String,
        default:'200'
      },
      mImgheight:{
        type:String,
        default:'200'
      },
      mTop: {
        type:String,
        default:''
      }
    },
    data: () => ({
      showLoading: false,
      tid: 0
    }),
    watch: {
      show () {
        this.setShow();
      }
    },
    computed: {
      //整个容器的尺寸和背景样式
      conStyle() {
        let style = {};
        style["width"] = `${this.mWidth}px`;
        style["height"] = `${this.mHeight}px`;
        if(this.mBackgroundcolor) {
          style["backgroundColor"] = this.mBackgroundcolor
        }
        return style;
      },
      //根据自定义的高宽来居中定位
      centerPosition() {
        let style = {};
        style["top"] = `${this.mTop ? this.mTop : this.topPosition}px`;
        style["left"] = `${(750-this.mWidth)/2}px`;
        return style;
      },
      topPosition () {
        return (this.getPageHeight() - this.mHeight) / 2;
      },
      loading () {
        let loading = {};
        switch (this.type) {
          case 'custom':
            loading = {
              url: this.mUrl
            };
            break;
          //不指定type为cunstom则用官网默认gif图
          default:
            loading = {
              url: 'https://img.alicdn.com/tfs/TB1Ep_9NVXXXXb8XVXXXXXXXXXX-74-74.gif'
            }
        }
        return loading;
      },
    },
    created () {
      this.setShow();
    },
    methods: {
     /**
     * 获取weex屏幕真实的设置高度，需要减去导航栏高度
     * @returns {Number}
     */
      getPageHeight () {
        const { env } = weex.config;
        // const navHeight = Utils.env.isWeb() ? 0 : (Utils.env.isIPhoneX() ? 176 : 132);
        return env.deviceHeight / env.deviceWidth * 750 - 132;
      },
      maskClicked () {
        this.needMask && (this.$emit('wxcLoadingMaskClicked', {}));
      },
      setShow () {
        const { interval, show, showLoading } = this;
        const stInterval = parseInt(interval);
        clearTimeout(this.tid);
        if (show) {
          if (showLoading) {
            return;
          }
          if (stInterval === 0) {
            this.showLoading = true;
          } else {
            this.tid = setTimeout(() => {
              this.showLoading = true;
            }, stInterval);
          }
        } else {
          this.showLoading = false;
        }
      }
    }
  };
</script>