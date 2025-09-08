// pages/login/login.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        telId:'',
        password:''
    },
    //获取用户输入的数据
    inphone(event){
        this.setData({
            telId: event.detail.value
        })
    },
  
    inpswd(event){
        this.setData({
            password: event.detail.value
        })
    },
  
    login(){
        const {telId,password} = this.data
        // 验证手机号格式
        let phoneReg = /^1[2-9]\d{9}$/;
        if (!phoneReg.test(telId)) {
            wx.showToast({
            title: '手机号格式错误',
            duration: 1500
          })
          return
        }
        // 验证密码长度（这里假设密码长度至少为 3 位）
        if (password.length < 3) {
            wx.showToast({
            title: '密码长度不能少于 3 位',
            duration: 1500
          })
          return
        }
        //调用后端接口验证
        wx.request({
          url: 'http://127.0.0.1:3000/login',
          method:'get',
          data:{
              telId,
              password
          },
          success:(res)=>{
              let {code,message} = res.data
              if (code === 200) {
                  wx.showToast({
                    title: message,
                    icon: 'success',
                    duration: 1500,
                    success:()=>{
                        wx.setStorageSync('isLogged', true)
                        wx.setStorageSync('userId', {telId})
                        setTimeout(()=>{
                            //跳转到主页
                            wx.switchTab({
                            url: '/pages/index/index',
                          })
                        },1500)
                    }
                  })
              } else {
                  wx.showToast({
                    title: message,
                    icon:'none',
                    duration: 1500
                  })
              }
  
              
          }
        })
      },
  
    toReg(){
        wx.navigateTo({
            url: '/pages/register/register',
          })
    },
  
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
  
    },
  
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
  
    },
  
    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
  
    },
  
    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {
  
    },
  
    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {
  
    },
  
    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
  
    },
  
    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {
  
    },
  
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {
  
    }
  })