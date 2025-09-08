// pages/cart/cart.js
Page({
  //页面的初始数据
  data: {
      cartList:[],
      num: 0,
      money: 0
  },

  toPay(){
      wx.navigateTo({
        url: '/pages/pay/pay',
      })
  },

  delete(event){
      //获取商品信息
      let index = event.target.dataset.index
      let cartList = this.data.cartList
      const item = cartList[index]
      //判断数量是否是1
      if (item.quantity > 1) {
          //数量-1        
          item.quantity--
          cartList[index] = item
        } else {
          //为1删除物品
          cartList.splice(index,1)
        }
      
      //将最新的购物车数据，存储到storage里
      //更新数据
      this.setData({cartList})
      this.getTotal(cartList)
      wx.setStorageSync('cart', cartList)
      //弹出提示框
      wx.showToast({
        title: '删除成功！',
        //时间间隔ms
        duration: 1500
      })
    },

  //生命周期函数--监听页面加载
  onLoad(options) {
    const cartList=wx.getStorageSync('cart')
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

  //生命周期函数--监听页面初次渲染完成
  onReady() {

  },

  //生命周期函数--监听页面显示
  onShow() {

  },

  //生命周期函数--监听页面隐藏
  onHide() {

  },

  //生命周期函数--监听页面卸载
  onUnload() {

  },

  //页面相关事件处理函数--监听用户下拉动作
  onPullDownRefresh() {

  },

  //页面上拉触底事件的处理函数
  onReachBottom() {

  },

  //用户点击右上角分享
  onShareAppMessage() {

  }
})