// pages/goods/goods.js
Page({
  //页面的初始数据
  data: {
    goods:{}
  },

  addCart(){
      //1.购物车的数据结构 数组
      //2.购物车数据存储在storage共享
      let cart = wx.getStorageSync('cart')
      //判断是否存在购物车
      if (cart =='') {
          cart = []
          //加入购物车之前，给商品数据扩充一个数量属性
          //展开复制给新对象
          let item = {...this.data.goods}
          item['quantity'] = 1
          //加入购物车
          cart.push(item)
      } else {
          //判断购物车中，是否存在该商品
          //循环遍历数组
          let targetIndex = -1
          for (let index = 0; index < cart.length; index++) {
              const element = cart[index];
              if (element.goodsId == this.data.goods.goodsId) {
                  targetIndex = index
                  break
              }
          }
          if (targetIndex == -1) {
             let item = {...this.data.goods}
             item['quantity'] = 1
             //加入购物车
             cart.push(item)
          } else {
              cart[targetIndex].quantity += 1
          }
      }
      //将最新的购物车数据，存储到storage里
      wx.setStorageSync('cart', cart)
      //弹出提示框
      wx.showToast({
        title: '加入成功！',
        //时间间隔ms
        duration: 1500
      })
  },

  toCart(){
      //跳转到购物车页面
      wx.switchTab({
        url: '/pages/cart/cart',
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
    this.setData({
        goods:wx.getStorageSync('goods')
    })
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