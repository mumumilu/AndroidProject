// pages/pay/pay.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      cartList:[],
      num:0,
      money:0,
      address:''
  },

  toIndex(){
      wx.switchTab({
        url: '/pages/index/index',
      })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const cartList = wx.getStorageSync('cart')
    this.setData({cartList})
    this.getTotal(cartList)
  },
  getTotal(cartList){
    let num = 0
    let money = 0
    cartList.forEach(item=>{
        num += item.quantity
        money += item.goodsPrice*item.quantity
    })
    this.setData({
        num,  
        money
    })
},

  toPay(){
      let isLogged = wx.getStorageSync('isLogged')
      if (!isLogged) {
          wx.showToast({
            title: '请先登录',
            icon:'none',
            duration:1500,
            success:()=>{
                setTimeout(()=>{
                    wx.switchTab({
                        url: '/pages/login/login',
                      }) 
                },1500)
            }
          })
      } else {
        wx.showToast({
          title: '支付成功',
        })
      }
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