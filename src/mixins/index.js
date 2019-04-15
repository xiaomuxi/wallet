
export default {
    methods: {
        reset(to) {
            if(WXEnvironment.platform === 'Web') {
                if (this.$router) {
                    this.$router.replace(to)
                }
            } else {
                if (this.$router) {
                    this.$router.replace(to)
                }
            }
        },
        getNavigator() {
            return weex.requireModule('navigator')
        },
        toBack() {
            if(WXEnvironment.platform === 'Web') {
                this.$router.back()
            }  else {
                this.getNavigator().pop({animated: "true"})
            }
        },
        getBaseUrl() {
            let bundleUrl = weex.config.bundleUrl;
            bundleUrl = String(bundleUrl);
            let nativeBase;
            let isAndroidAssets = bundleUrl.indexOf('file://assets/') >= 0;

            let isiOSAssets = bundleUrl.indexOf('file:///') >= 0 && bundleUrl.indexOf('WeexDemo.app') > 0;
            if (isAndroidAssets) {
                nativeBase = 'file://assets/dist/';
            }
            else if (isiOSAssets) {
                nativeBase = bundleUrl.substring(0, bundleUrl.lastIndexOf('/') + 1);
            } else {
                let host = 'localhost:8080';
                let matches = /\/\/([^\/]+?)\//.exec(bundleUrl);
                if (matches && matches.length >= 2) {
                    host = matches[1];
                }
                nativeBase = 'http://' + host + '/';
            }
            return nativeBase;
        },
        jumpInter(to) {
            if (this.$router) {
                this.$router.push(to)
            }
        },
        jumpWithParams(to, params) {
            if(WXEnvironment.platform === 'Web') {
                if (this.$router) {
                    this.$router.push({name: to, params: params})
                }
            } else {
                let path = this.getBaseUrl();
                let q = this.createQuery(params)
                this.getNavigator().push({
                    url: path + to + '.js' + q,
                    animated: "true"
                }, event => {
                    //modal.toast({ message: 'callback: ' + event })
                })
            }
        },
        // object 转 URL 参数
        createQuery(obj) {
            if(obj === null || obj === "" || obj.length === 0) {
                return ""
            }
            let url = '?';
            for (let key in obj) {
                if (obj[key] !== null) {
                    url += (key + '=' + encodeURIComponent(obj[key]) + '&');
                }
            }
            return url.substring(0, url.lastIndexOf('&'));
        },
        // 'xxx.js?name=aa' 转 {name: 'aa'}
        getQueryData(url) {
            url = url.substring(url.indexOf('.js?') + 3);
            let result = {};
            if (url.indexOf("?") != -1) {
                let str = url.substr(1);
                let strs = str.split("&");
                for (let i = 0; i < strs.length; i++) {
                    result[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
                }
            }
            return result;
        },
        getQuery() {
            if (WXEnvironment.platform === 'Web') {
                return this.$route.params;
            } else {
                return this.getQueryData(weex.config.bundleUrl);
            }
        },
        getUserInfo() {
            return this.$store.state.user.userInfo
        },
        // 获取图片在三端上不同的路径
        // e.g. 图片文件名是 test.jpg, 转换得到的图片地址为
        // - H5      : images/test.jpg        images和所在html路径同级
        // - Android : local:///test          local代表项目各dpi目录,一般放在hdpi里一张即可
        // - iOS     : local///test.jpg       local代表从项目中全局扫描 test.jpg可放至项目中任何目录
        get_img_path: function(img_name) {
            let platform = weex.config.env.platform
            let img_path = ''
            if (platform == 'Web') {
                img_path = `images/${img_name}`
            } else if (platform == 'android') {
                // android 不需要后缀
                img_name = img_name.substr(0, img_name.lastIndexOf('.'));
                img_path = `local:///${img_name}`
            } else {
                // img_path = `../images/${img_name}`
                img_path = `local:///images/${img_name}`
            }

            return img_path
        },
    }
}